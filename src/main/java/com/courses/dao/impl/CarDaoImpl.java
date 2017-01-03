package com.courses.dao.impl;

import com.courses.dao.CarDao;
import com.courses.model.Car;
import com.courses.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * @author Stepan.Kachan
 */
public class CarDaoImpl implements CarDao {

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private SessionFactory sessionFactory;


    public User findByUserName(String username) {
        return null;
    }

    public void save(User user) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(user);
        tx.commit();
        session.close();
    }


    public Car findByUserName(Car car) {
        return null;
    }

    public void save(Car car) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(car);
        tx.commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    public List<Car> list() {
        Session session = this.sessionFactory.openSession();
        List<Car> carList = session.createQuery("from com.courses.model.Car").list();
        session.close();
        return carList;
    }
}
