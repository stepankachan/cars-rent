package com.courses.controller;

import com.courses.interceptor.ActivityType;
import com.courses.interceptor.LoggerInterceptor;
import com.courses.interceptor.annotation.Loggable;
import com.courses.model.AppUser;
import com.courses.model.Car;
import com.courses.model.LogActivity;
import com.courses.model.RentRequest;
import com.courses.model.RequestState;
import com.courses.model.UserRole;
import com.courses.service.CarService;
import com.courses.service.LogActivityService;
import com.courses.service.RentRequestService;
import com.courses.service.UserProfileService;
import com.courses.service.UserService;
import com.courses.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class AppController {

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private RentRequestService requestService;

    @Autowired
    private LogActivityService activityService;

    @Autowired
    LoggerInterceptor interceptor;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

    @Autowired
    private AuthenticationTrustResolver authenticationTrustResolver;

    private List<AppUser> users = new ArrayList<>();

    /**
     * This method will list all existing users.
     */
    @Loggable(activity = ActivityType.LOGIN)
    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {

        List<AppUser> users = userService.findAllUsers();
        this.users = users;
        for(AppUser user : users){
            if(user.getSsoId().equals(SessionUtils.getPrincipal())){
                model.addAttribute("loggedinuser", user);
                SessionUtils.setLoggedInUser(user);
            }
        }
        model.addAttribute("users", users);
        model.addAttribute("loggedinuser", SessionUtils.getCurrentUser());
        model.addAttribute("selecteduser", users.get(0));
        model.addAttribute("userActivities", users.get(0).getUserActivities());
        return "pages/usersPage";
    }

    @RequestMapping(value = "cars", method = RequestMethod.GET)
    public String listCars(ModelMap model) {
        List<Car> cars = carService.findAllCars();
        model.addAttribute("loggedinuser", SessionUtils.getCurrentUser());
        model.addAttribute("cars", cars);

        return "pages/carsPage";
    }

    @Loggable(activity = ActivityType.CAR_RENT_REQUEST)
    @RequestMapping(value = {"/rent-car-{carId}"}, method = RequestMethod.GET)
    public String editCar(@PathVariable String carId, @RequestParam(value = "from-date") Date fromDate,
                          @RequestParam(value = "to-date") Date toDate, ModelMap model) {
        Car carForRent = carService.findById(Integer.valueOf(carId));
        RentRequest rentRequest = new RentRequest();
        rentRequest.setCar(carForRent);
        AppUser appUser = userService.findBySSO(SessionUtils.getPrincipal());
        rentRequest.setUser(appUser);
        rentRequest.setFromDate(fromDate);
        rentRequest.setToDate(toDate);
        rentRequest.setDescription("Заявка на этапе рассмотрения...");
        requestService.addRequest(rentRequest);
        model.clear();
        model.addAttribute("requests",requestService.getAllRequests());
        return "pages/rentRequestsPage";
    }

    @Loggable(activity = ActivityType.RENT_REQUEST_CONFIRMATION)
    @RequestMapping(value = {"/edit-request-{id}"}, method = RequestMethod.GET)
    public String editRequest(@PathVariable String id, ModelMap model){
        RentRequest rentRequest = requestService.findRequestById(id);
        rentRequest.setState(RequestState.CONFIRMED);
        rentRequest.setDescription("Заявка подтверждена администратором " + SessionUtils.getPrincipal());
        requestService.updateRequest(rentRequest);
        model.addAttribute("requests",requestService.getAllRequests());
        return "pages/rentRequestsPage";
    }

    @Loggable(activity = ActivityType.RENT_REQUEST_DISCARDED)
    @RequestMapping(value = {"/discard-request-{id}"}, method = RequestMethod.GET)
    public String discardRequest(@PathVariable String id,@RequestParam(value = "comment") String comment, ModelMap model){
        RentRequest rentRequest = requestService.findRequestById(id);
        rentRequest.setState(RequestState.DISCARDED);
        rentRequest.setDescription("Отклонено администратором " + SessionUtils.getPrincipal() + ", Комментарий : " + comment);
        requestService.updateRequest(rentRequest);
        model.addAttribute("requests",requestService.getAllRequests());
        model.addAttribute("unconfirmedCount", getUnconfirmedRequests());
        return "pages/rentRequestsPage";
    }

    @RequestMapping(value = "requests", method = RequestMethod.GET)
    public String getRequests(ModelMap model) {
        List<RentRequest> requests = requestService.getAllRequests();
        Car car = carService.findAllCars().iterator().next();
        RentRequest request;
        if (!requests.isEmpty()) {
            request = requests.iterator().next();
        } else {
            request = new RentRequest();
        }

        Set<RentRequest> requestset = new HashSet<>();
        requestset.add(request);

        car.setRentRequests(requestset);

        carService.updateCar(car);
        model.addAttribute("requests", requests);
        model.addAttribute("loggedinuser", SessionUtils.getCurrentUser());
        return "pages/rentRequestsPage";
    }

    @RequestMapping(value = "activities", method = RequestMethod.GET)
    public String getActivities(ModelMap model) {
        List<LogActivity> activities = activityService.list();

        model.addAttribute("activities",activities);
        model.addAttribute("loggedinuser", SessionUtils.getCurrentUser());

        return "pages/activitiesPage";
    }

    /**
     * This method will provide the medium to add a new user.
     */
    @RequestMapping(value = {"/newuser"}, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        AppUser user = new AppUser();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", SessionUtils.getCurrentUser());
        model.addAttribute("roles");
        return "pages/registration";
    }

    private String saveOrUpdateUser(AppUser user, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {

            return "pages/login";
        }

      /*
       * Preferred way to achieve uniqueness of field [sso] should be implementing custom @Unique interceptor and applying it on field [sso] of Model class [User].
       *
       * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation framework as well while still using internationalized messages.
       *
       */
        if (!userService.isUserSSOUnique(user.getId(), user.getSsoId())) {
            FieldError ssoError = new FieldError("user", "ssoId", messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
            result.addError(ssoError);
            return "pages/registrationPage";
        }

        userService.saveUser(user);

        model.addAttribute("success", "user " + user.getFirstName() + " " + user.getLastName() + " registered successfully");
        model.addAttribute("loggedinuser", SessionUtils.getCurrentUser());

        return "pages/registrationsuccess";
    }

    /**
     * This method will be called on form submission, handling POST request for saving user in database. It also validates the user input
     */
    @RequestMapping(value = {"/newuser"}, method = RequestMethod.POST)
    public String saveUser(@Valid AppUser user, BindingResult result, ModelMap model) {
        return saveOrUpdateUser(user, result, model);
    }

    @RequestMapping(value = {"/user-details-{ssoId}"}, method = RequestMethod.GET)
    public String getUserDetails(@PathVariable String ssoId, ModelMap model) {
        AppUser user = userService.findBySSO(ssoId);
        List<LogActivity> latestActivities = user.getUserActivities().stream().limit(15).collect(Collectors.toList());
        latestActivities.sort((o1, o2) -> o1.getTime().getTime() > o2.getTime().getTime() ? -1 : 1);
        model.addAttribute("userActivities", latestActivities);
        model.addAttribute("users", users);
        model.addAttribute("selecteduser", user);
        model.addAttribute("loggedinuser", SessionUtils.getCurrentUser());

        return "pages/usersPage";
    }

    /**
     * This method will provide the medium to update an existing user.
     */
    @RequestMapping(value = {"/edit-user-{ssoId}"}, method = RequestMethod.GET)
    public String editUser(@PathVariable String ssoId, ModelMap model) {
        AppUser user = userService.findBySSO(ssoId);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", SessionUtils.getCurrentUser());
        return "pages/registrationPage";
    }

    /**
     * This method will be called on form submission, handling POST request for updating user in database. It also validates the user input
     */
    @RequestMapping(value = {"/edit-user-{ssoId}"}, method = RequestMethod.POST)
    public String updateUser(@Valid AppUser user, BindingResult result, ModelMap model, @PathVariable String ssoId) {

        if (result.hasErrors()) {
            return "pages/registrationPage";
        }

        userService.updateUser(user);

        model.addAttribute("success", "User " + user.getFirstName() + " " + user.getLastName() + " updated successfully");
        model.addAttribute("successToast", true);
        model.addAttribute("loggedinuser", SessionUtils.getCurrentUser());
        return "pages/registrationsuccess";
    }

    /**
     * This method will delete an user by it's SSOID value.
     */
    @RequestMapping(value = {"/delete-user-{ssoId}"}, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String ssoId) {
        userService.deleteUserBySSO(ssoId);
        return "redirect:/list";
    }
    @RequestMapping(value = {"/user-requests-{ssoId}"}, method = RequestMethod.GET)
    public String userRequests(@PathVariable String ssoId, ModelMap model){
        AppUser appUser = userService.findBySSO(ssoId);
        model.addAttribute("requests", appUser.getUserRentRequests());
        return "pages/rentRequestsPage";
    }
    /**
     * This method will provide UserRole list to views
     */
    @ModelAttribute("roles")
    public List<UserRole> initializeProfiles() {
        return userProfileService.findAll();
    }

    @ModelAttribute("unconfirmedCount")
    public Integer getUnconfirmedRequests(){
        return Math.toIntExact(requestService.getAllRequests().stream()
                .filter(request -> request.getState().equals(RequestState.NOT_REVIEWED)).count());
    }

    /**
     * This method handles Access-Denied redirect.
     */
    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("loggedinuser", SessionUtils.getCurrentUser());
        return "pages/accessDenied";
    }

    /**
     * This method handles login GET requests. If users is already logged-in and tries to goto login page again, will be redirected to list page.
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
        return saveOrUpdateUser(user, result, model);
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationPage(ModelMap model) {

        AppUser user = new AppUser();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", SessionUtils.getCurrentUser());
        model.addAttribute("roles");

        return "pages/registrationPage";
    }

    @RequestMapping(value = "/fillBalance", method = RequestMethod.GET)
    public String fillBalance(@RequestParam(value = "amount") BigDecimal amount, ModelMap model) {
        AppUser user = userService.findBySSO(SessionUtils.getPrincipal());
        user.addMoney(amount);
        userService.updateUser(user);
        List<AppUser> users = userService.findAllUsers();

        model.addAttribute("loggedinuser", SessionUtils.getCurrentUser());
        model.addAttribute("users", users);
        model.addAttribute("selecteduser", users.get(0));
        model.addAttribute("userActivities", users.get(0).getUserActivities());

        return "pages/usersPage";
    }

    /**
     * This method handles logout requests. Toggle the handlers if you are RememberMe functionality is useless in your app.
     */
    @Loggable(activity = ActivityType.LOGOUT)
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            persistentTokenBasedRememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/login?logout";
    }

    /**
     * This method returns true if users is already authenticated [logged-in], else false.
     */
    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }

}