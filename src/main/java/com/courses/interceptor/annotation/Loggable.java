package com.courses.interceptor.annotation;

import com.courses.interceptor.ActivityType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * @author Stepan.Kachan
 */

/**
 * This interceptor is used for request which will be stored to database events writeActionToLog table
 */

@Retention(RetentionPolicy.RUNTIME )
@Target(ElementType.METHOD )
public @interface Loggable {

    ActivityType activity();
}
