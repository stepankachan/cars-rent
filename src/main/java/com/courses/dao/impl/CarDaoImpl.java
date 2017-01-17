package com.courses.dao.impl;

import com.courses.dao.AbstractDao;
import com.courses.dao.CarDao;
import com.courses.model.Car;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Stepan.Kachan
 */
@Repository("carDao")
public class CarDaoImpl extends AbstractDao<Integer, Car> implements CarDao {

    @SuppressWarnings("unchecked")
    public List<Car> list() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        return (List<Car>) criteria.list();
    }

    @Override
    public void save(Car car) {
        persist(car);
    }

    @Override
    public Car findById(int id) {
        Car car = getByKey(Math.toIntExact(id));
        if(car!=null){
            Hibernate.initialize(car.getRentRequests());
        }
        return car;
    }
}
