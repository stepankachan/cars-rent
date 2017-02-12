package com.courses.util;

import com.courses.model.AppUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Stepan.Kachan
 */
public class SessionUtils {

    private static AppUser currentUser;

    /**
     * This method returns the principal[user-name] of logged-in user.
     */
    public static String getPrincipal() {
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    public static void setLoggedInUser(AppUser user) {
        currentUser = user;
    }

    public static AppUser getCurrentUser() {
        return currentUser;
    }
}
