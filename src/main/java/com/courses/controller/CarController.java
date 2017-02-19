package com.courses.controller;

import com.courses.interceptor.ActivityType;
import com.courses.interceptor.annotation.Loggable;
import com.courses.model.AppUser;
import com.courses.model.Car;
import com.courses.model.RentRequest;
import com.courses.model.RequestState;
import com.courses.service.CarService;
import com.courses.service.RentRequestService;
import com.courses.service.UserService;
import com.courses.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * @author Stepan.Kachan
 */
@Controller
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;

    @Autowired
    private RentRequestService requestService;

    @Loggable(activity = ActivityType.CAR_RENT_REQUEST)
    @RequestMapping(value = {"/rent-car-{carId}"}, method = RequestMethod.GET)
    public ModelAndView editCar(@PathVariable String carId, @RequestParam(value = "from-date") Date fromDate,
                                @RequestParam(value = "to-date") Date toDate, ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("pages/rentRequestsPage");
        Car carForRent = carService.findById(Integer.valueOf(carId));
        RentRequest rentRequest = new RentRequest();
        rentRequest.setCar(carForRent);
        AppUser appUser = userService.findBySSO(SessionUtils.getPrincipal());
        rentRequest.setUser(appUser);
        rentRequest.setFromDate(fromDate);
        rentRequest.setToDate(toDate);
        rentRequest.setDescription("Заявка на этапе рассмотрения...");
        requestService.addRequest(rentRequest);
        model.addAttribute("requests", requestService.getAllRequests());
        model.addAttribute("loggedinuser", SessionUtils.getCurrentUser());
        return modelAndView;
    }

    @RequestMapping(value = "cars", method = RequestMethod.GET)
    public String listCars(ModelMap model) {
        List<Car> cars = carService.findAllCars();
        model.addAttribute("loggedinuser", SessionUtils.getCurrentUser());
        model.addAttribute("cars", cars);

        return "pages/carsPage";
    }

    @ModelAttribute("unconfirmedCount")
    public Integer getUnconfirmedRequests(){
        return Math.toIntExact(requestService.getAllRequests().stream()
                .filter(request -> request.getState().equals(RequestState.NOT_REVIEWED)).count());
    }
}
