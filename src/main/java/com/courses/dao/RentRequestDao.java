package com.courses.dao;

import com.courses.model.AppUser;
import com.courses.model.RentRequest;

import java.util.List;

/**
 * @author Stepan.Kachan
 */
public interface RentRequestDao {

    void addRequest(RentRequest rentRequest);

    List<RentRequest> getUsersRequests(AppUser user);
}
