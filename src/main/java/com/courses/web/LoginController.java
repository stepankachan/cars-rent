package com.courses.web;

import com.courses.dao.UserDao;
import com.courses.model.User;
import com.courses.model.UserRole;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author Stepan.Kachan
 */
@Controller
public class LoginController {

    private static final Logger logger = Logger.getLogger(LoginController.class);

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView welcomePage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("loginPage");
        return model;
    }

    @RequestMapping(value = { "/homePage"}, method = RequestMethod.GET)
    public ModelAndView homePage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("homePage");
        return model;
    }

    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public ModelAndView loginPage(@RequestParam(value = "error",required = false) String error,
                                  @RequestParam(value = "logout",	required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Неверный логин или пароль");
        }

        if (logout != null) {
            model.addObject("message", "Ошибка входа в систему");
        }

        model.setViewName("loginPage");
        return model;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registrationPage(){
        ModelAndView model = new ModelAndView();
        model.setViewName("registrationPage");
        return model;
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public ModelAndView registerUser(@RequestParam(value = "first_name",required = true) String first_name,
                                     @RequestParam(value = "last_name", required = false) String last_name,
                                     @RequestParam(value = "login", required = true) String login,
                                     @RequestParam(value = "password",required = true) String password,
                                     @RequestParam(value = "email",required = false) String email,
                                     HttpSession session)
    {
        ModelAndView model = new ModelAndView("homePage");
        logger.error(first_name);
        logger.error(last_name);
        logger.error(login);
        logger.error(password);
        logger.error(email);

        if(!first_name.isEmpty() && !last_name.isEmpty() && !login.isEmpty() && !password.isEmpty() && !email.isEmpty()) {
            User user = new User();
            user.setFirstName(first_name);
            user.setLastName(last_name);
            user.setEmail(email);
            user.setLogin(login);
            user.setRole(UserRole.ROLE_USER);
            user.setCreationDate(new Date());
            user.setPassword(password);
            user.setLastOnlineDate(new Date());
            logger.error("1");
            model.addObject("msg", "Sent");
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

            UserDao userDao = context.getBean(UserDao.class);
            userDao.save(user);
        }
        else{
            model = new ModelAndView("redirect:/registrationPage");
            model.addObject("msg", "Empty fields");
            logger.warn("2");
        }

        model.setViewName("registrationPage");
        return model;
    };

}
