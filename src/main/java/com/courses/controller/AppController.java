package com.courses.controller;

import com.courses.model.AppUser;
import com.courses.model.Car;
import com.courses.model.RentRequest;
import com.courses.model.UserRole;
import com.courses.service.CarService;
import com.courses.service.RentRequestService;
import com.courses.service.UserProfileService;
import com.courses.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class AppController {

	@Autowired
	UserService userService;

	@Autowired
	CarService carService;
	
	@Autowired
	UserProfileService userProfileService;

	@Autowired
	RentRequestService requestService;
	
	@Autowired
    MessageSource messageSource;

	@Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
	
	@Autowired
    AuthenticationTrustResolver authenticationTrustResolver;


	List<AppUser> users = new ArrayList<>();

	/**
	 * This method will list all existing users.
	 */
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {

		List<AppUser> users = userService.findAllUsers();
		this.users = users;
		model.addAttribute("users", users);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("selecteduser",users.get(0));
		model.addAttribute("userRequests",users.get(0).getUserRentRequests());
		return "pages/userslist";
	}

	@RequestMapping(value = "cars", method = RequestMethod.GET)
    public String listCars(ModelMap model){
        List<Car> cars = carService.findAllCars();
        model.addAttribute("cars", cars);

        return "pages/carsPage";
    }
/*1	/static/images/cars/3Honda_Civic-270.jpg	Honda Civic	2017-01-14 21:50:28
2	/static/images/cars/AUDI-A6.jpg	Audi A6	2017-01-14 21:50:56
3	/static/images/cars/Lanos-270.jpg	Lanos	2017-01-14 23:44:24
4	/static/images/cars/Mazda_3_sedan_2011-270.jpg	Mazda	2017-01-17 17:15:07
5	/static/images/cars/range-270x1.jpg	Range Rover	2017-01-17 17:15:10
*/
	@RequestMapping(value = "requests", method = RequestMethod.GET)
	public String getRequests(ModelMap model){
		List<RentRequest> requests = requestService.getAllRequests();
		Car car = carService.findAllCars().iterator().next();
		RentRequest request = requestService.getAllRequests().iterator().next();
		Set<RentRequest> requestset = new HashSet<>();
		requestset.add(request);
		car.setRentRequests(requestset);

		car.setRentRequests(requestset);

		carService.updateCar(car);
		model.addAttribute("requests", requests);

		return "pages/rentRequestsPage";
	}

	/**
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		AppUser user = new AppUser();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("roles");
		return "pages/registration";
	}

	private String saveOrUpdateUser(AppUser user, BindingResult result, ModelMap model){
        if (result.hasErrors()) {

            return "pages/login";
        }

		/*
		 * Preferred way to achieve uniqueness of field [sso] should be implementing custom @Unique annotation
		 * and applying it on field [sso] of Model class [User].
		 *
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 *
		 */
        if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
            FieldError ssoError = new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
            result.addError(ssoError);
            return "pages/registrationPage";
        }

        userService.saveUser(user);

        model.addAttribute("success", "user " + user.getFirstName() + " "+ user.getLastName() + " registered successfully");
        model.addAttribute("loggedinuser", getPrincipal());

        return "pages/registrationsuccess";
    }

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
	public String saveUser(@Valid AppUser user, BindingResult result, ModelMap model) {
        return saveOrUpdateUser(user,result,model);
	}

	@RequestMapping(value = {"/user-details-{ssoId}" }, method = RequestMethod.GET)
	public String  getUserDetails(@PathVariable String ssoId, ModelMap model){
		AppUser user = userService.findBySSO(ssoId);
		List<RentRequest> userRequests = requestService.getUserRequests(user);
		model.addAttribute("userRequests", userRequests);
		model.addAttribute("users", users);
		model.addAttribute("selecteduser", user);
		model.addAttribute("loggedinuser", getPrincipal());

		return "pages/userslist";
	}


	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String ssoId, ModelMap model) {
		AppUser user = userService.findBySSO(ssoId);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "pages/registrationPage";
	}
	
	/**
	 * This method will be called on form submission, handling POST request for
	 * updating user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.POST)
	public String updateUser(@Valid AppUser user, BindingResult result,
                             ModelMap model, @PathVariable String ssoId) {

		if (result.hasErrors()) {
			return "pages/registrationPage";
		}

		userService.updateUser(user);

		model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " updated successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "pages/registrationsuccess";
	}

	
	/**
	 * This method will delete an user by it's SSOID value.
	 */
	@RequestMapping(value = { "/delete-user-{ssoId}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String ssoId) {
		userService.deleteUserBySSO(ssoId);
		return "redirect:/list";
	}
	

	/**
	 * This method will provide UserRole list to views
	 */
	@ModelAttribute("roles")
	public List<UserRole> initializeProfiles() {
		return userProfileService.findAll();
	}
	
	/**
	 * This method handles Access-Denied redirect.
	 */
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		return "pages/accessDenied";
	}

	/**
	 * This method handles login GET requests.
	 * If users is already logged-in and tries to goto login page again, will be redirected to list page.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		if (isCurrentAuthenticationAnonymous()) {
			return "pages/login";
	    } else {
	    	return "redirect:/list";  
	    }
	}
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerUser(@Valid AppUser user, BindingResult result, ModelMap model) {
        return saveOrUpdateUser(user,result,model);
    }

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registrationPage(ModelMap model){

		AppUser user = new AppUser();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("roles");

		return "pages/registrationPage";
	}

	/**
	 * This method handles logout requests.
	 * Toggle the handlers if you are RememberMe functionality is useless in your app.
	 */
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			//new SecurityContextLogoutHandler().logout(request, response, auth);
			persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/login?logout";
	}

	/**
	 * This method returns the principal[user-name] of logged-in user.
	 */
	private String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
	
	/**
	 * This method returns true if users is already authenticated [logged-in], else false.
	 */
	private boolean isCurrentAuthenticationAnonymous() {
	    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    return authenticationTrustResolver.isAnonymous(authentication);
	}

}