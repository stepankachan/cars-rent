package com.courses.dao.impl;

import com.courses.dao.AbstractDao;
import com.courses.dao.UserProfileDao;
import com.courses.model.UserRole;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Stepan.Kachan
 */
@Repository("userProfileDao")
public class UserProfileDaoImpl extends AbstractDao<Integer, UserRole> implements UserProfileDao {

    public UserRole findById(int id) {
        return getByKey(id);
    }

    public UserRole findByType(String type) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("type", type));
        return (UserRole) crit.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<UserRole> findAll(){
        Criteria crit = createEntityCriteria();
        crit.addOrder(Order.asc("type"));
        return (List<UserRole>)crit.list();
    }

}