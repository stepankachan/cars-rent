package com.courses.dao;

import com.courses.model.AppUser;

import java.util.List;

/**
 * @author Stepan.Kachan
 */
public interface UserDao {

    AppUser findById(int id);

    AppUser findBySSO(String sso);

    void save(AppUser user);

    void deleteBySSO(String sso);

    List<AppUser> findAllUsers();
}
