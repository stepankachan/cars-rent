package com.courses.main;

import com.courses.dao.CarDao;
import com.courses.dao.UserDao;
import com.courses.model.Car;
import com.courses.model.UserRole;
import com.courses.model.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class SpringHibernateMain {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

		UserDao userDao = context.getBean(UserDao.class);
		CarDao carDao = context.getBean(CarDao.class);


		User user = new User();
		user.setCreationDate(new Date());
		user.setLastOnlineDate(new Date());
		user.setFirstName("Stepan");
		user.setLastName("Kachan");
		user.setEmail("email@.com");
		user.setLogin("stepan");
		user.setPassword("111");
		user.setRole(UserRole.ROLE_ADMIN);

		userDao.save(user);
		for(User s : userDao.list()){
			System.out.println(s.getLogin());
		}

		Car car = new Car();
		car.setName("Honda Accord");
		car.setImageURL("/resources/images/");
		car.setReleaseDate(new Date());
		carDao.save(car);

		for(Car c : carDao.list())
		{
			System.out.println(c.getName());
		}


		context.close();

	}

}
