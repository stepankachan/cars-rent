package com.courses.service;

import com.courses.model.AppUser;
import com.courses.model.RentRequest;

import java.util.List;

/**
 * @author Stepan.Kachan
 */
public interface RentRequestService {

    void addRequest(RentRequest rentRequest);

    List<RentRequest> getUserRequests(AppUser user);

    List<RentRequest> getAllRequests();

    RentRequest findRequestById(String id);

    void updateRequest(RentRequest rentRequest);
}
