package com.courses.dao;

import com.courses.model.User;

import java.util.List;

/**
 * @author Stepan.Kachan
 */
public interface UserDao {

    User findByUserName(String username);

    void save(User user);

    List<User> list();
}
