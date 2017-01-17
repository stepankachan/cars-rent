package com.courses.service;

import com.courses.model.AppUser;

import java.util.List;

/**
 * @author Stepan.Kachan
 */
public interface UserService {

    AppUser findById(int id);

    AppUser findBySSO(String sso);

    void saveUser(AppUser user);

    void updateUser(AppUser user);

    void deleteUserBySSO(String sso);

    List<AppUser> findAllUsers();

    boolean isUserSSOUnique(Integer id, String sso);
}
