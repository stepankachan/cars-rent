package com.courses.service.impl;

import com.courses.dao.RentRequestDao;
import com.courses.dao.UserDao;
import com.courses.model.AppUser;
import com.courses.model.RentRequest;
import com.courses.service.RentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Stepan.Kachan
 */
@Service("rentRequestService")
@Transactional
public class RentRequestServiceImpl implements RentRequestService{

    @Autowired
    private RentRequestDao dao;

    @Override
    public void addRequest(RentRequest rentRequest) {
        dao.addRequest(rentRequest);
    }

    @Override
    public List<RentRequest> getUserRequests(AppUser user) {
        return dao.getUsersRequests(user);
    }

    @Override
    public List<RentRequest> getAllRequests() {
        return dao.getAllRequests();
    }

    @Override
    public RentRequest findRequestById(String id) {
        return dao.findRequestById(id);
    }

    @Override
    public void updateRequest(RentRequest rentRequest) {
        dao.updateRentRequest(rentRequest);
    }
}
