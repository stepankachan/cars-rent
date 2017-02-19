package com.courses.controller;

import com.courses.interceptor.ActivityType;
import com.courses.interceptor.annotation.Loggable;
import com.courses.model.Car;
import com.courses.model.RentRequest;
import com.courses.model.RequestState;
import com.courses.service.CarService;
import com.courses.service.RentRequestService;
import com.courses.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Stepan.Kachan
 */
@Controller
public class RentRequestsController {

    @Autowired
    private RentRequestService requestService;

    @Autowired
    private CarService carService;

    @Loggable(activity = ActivityType.RENT_REQUEST_DISCARDED)
    @RequestMapping(value = {"/discard-request-{id}"}, method = RequestMethod.GET)
    public String discardRequest(@PathVariable String id, @RequestParam(value = "comment") String comment, ModelMap model){
        RentRequest rentRequest = requestService.findRequestById(id);
        rentRequest.setState(RequestState.DISCARDED);
        rentRequest.setDescription("Отклонено администратором " + SessionUtils.getPrincipal() + ", Комментарий : " + comment);
        requestService.updateRequest(rentRequest);
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

    @ModelAttribute("unconfirmedCount")
    public Integer getUnconfirmedRequests(){
        return Math.toIntExact(requestService.getAllRequests().stream()
                .filter(request -> request.getState().equals(RequestState.NOT_REVIEWED)).count());
    }
}
