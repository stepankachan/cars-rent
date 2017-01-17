package com.courses.dao;

import com.courses.model.UserRole;

import java.util.List;

/**
 * @author Stepan.Kachan
 */
public interface UserProfileDao {

    List<UserRole> findAll();

    UserRole findByType(String type);

    UserRole findById(int id);
}
