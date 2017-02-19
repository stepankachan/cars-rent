package com.courses.controller;

import com.courses.model.LogActivity;
import com.courses.model.RequestState;
import com.courses.service.LogActivityService;
import com.courses.service.RentRequestService;
import com.courses.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author Stepan.Kachan
 */
@Controller
public class ActivitiesController {

    @Autowired
    private LogActivityService activityService;

    @Autowired
    private RentRequestService requestService;

    @RequestMapping(value = "activities", method = RequestMethod.GET)
    public String getActivities(ModelMap model) {
        List<LogActivity> activities = activityService.list();

        model.addAttribute("activities",activities);
        model.addAttribute("loggedinuser", SessionUtils.getCurrentUser());

        return "pages/activitiesPage";
    }

    @ModelAttribute("unconfirmedCount")
    public Integer getUnconfirmedRequests(){
        return Math.toIntExact(requestService.getAllRequests().stream()
                .filter(request -> request.getState().equals(RequestState.NOT_REVIEWED)).count());
    }
}
