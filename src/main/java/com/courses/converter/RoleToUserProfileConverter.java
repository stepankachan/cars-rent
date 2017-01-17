package com.courses.converter;

import com.courses.model.UserRole;
import com.courses.service.UserProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Stepan.Kachan
 */
@Component
public class RoleToUserProfileConverter implements Converter<Object, UserRole> {

    private static final Logger logger = LoggerFactory.getLogger(RoleToUserProfileConverter.class);

    @Autowired
    UserProfileService userProfileService;

    @Override
    public UserRole convert(Object o) {
        Integer id = Integer.parseInt((String)o);
        UserRole profile= userProfileService.findById(id);
        logger.info("Profile : {}",profile);
        return profile;
    }
}