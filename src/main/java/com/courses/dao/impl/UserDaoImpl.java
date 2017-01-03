package com.courses.dao.impl;

import com.courses.dao.UserDao;
import com.courses.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * @author Stepan.Kachan
 */
public class UserDaoImpl implements UserDao {


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

    @SuppressWarnings("unchecked")
    public List<User> list() {
        Session session = this.sessionFactory.openSession();
        List<User> personList = session.createQuery("from com.courses.model.User").list();
        session.close();
        return personList;
    }
}
