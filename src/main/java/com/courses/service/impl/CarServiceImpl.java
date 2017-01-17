package com.courses.service.impl;

import com.courses.dao.AbstractDao;
import com.courses.dao.CarDao;
import com.courses.model.Car;
import com.courses.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Stepan.Kachan
 */

@Service("carsService")
@Transactional
public class CarServiceImpl extends AbstractDao<Integer, Car> implements CarService {

    @Autowired
    private CarDao dao;

    @Override
    public List<Car> findAllCars() {
        return dao.list();
    }
}
