package com.courses.service;

import com.courses.model.UserRole;

import java.util.List;

/**
 * @author Stepan.Kachan
 */
public interface UserProfileService {

    UserRole findById(int id);

    UserRole findByType(String type);

    List<UserRole> findAll();
}
