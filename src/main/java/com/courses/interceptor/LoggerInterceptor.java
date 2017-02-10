package com.courses.interceptor;

import com.courses.interceptor.annotation.Loggable;
import com.courses.model.AppUser;
import com.courses.model.LogActivity;
import com.courses.service.LogActivityService;
import com.courses.service.UserService;
import com.courses.util.SessionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;


/**
 * @author Stepan.Kachan
 */

public class LoggerInterceptor implements HandlerInterceptor {

    private final LogActivityService logActivityService;
    private final UserService userService;
    private AppUser currentUser;

    public LoggerInterceptor(LogActivityService logActivityService, UserService userService) {
        this.logActivityService = logActivityService;
        this.userService = userService;
        currentUser = null;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod hm = (HandlerMethod) handler;
        Method method = hm.getMethod();
        if (method.getDeclaringClass().isAnnotationPresent(Controller.class)) {
            if (method.isAnnotationPresent(Loggable.class)) {
                System.out.println(method.getAnnotation(Loggable.class).activity());
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerMethod hm = (HandlerMethod) handler;
        Method method = hm.getMethod();
        if (method.getDeclaringClass().isAnnotationPresent(Controller.class)) {
            if (method.isAnnotationPresent(Loggable.class)) {
                System.out.println(method.getAnnotation(Loggable.class).activity());
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerMethod hm = (HandlerMethod) handler;
        Method method = hm.getMethod();
        if (method.isAnnotationPresent(Loggable.class)) {
            LogActivity log = new LogActivity();
            log.setActivity(method.getAnnotation(Loggable.class).activity().getActivity());
            log.setTime(new Date());
            if(currentUser != null){
                log.setUser(currentUser);
            }
            else{
                currentUser = userService.findBySSO(SessionUtils.getPrincipal());
            }
            log.setUser(currentUser);
            logActivityService.save(log);
            if(method.getAnnotation(Loggable.class).activity() == ActivityType.LOGOUT){
                currentUser = null;
            }
        }
    }
}

