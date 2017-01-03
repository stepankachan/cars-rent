package com.courses.dao;

import com.courses.model.Car;

import java.util.List;

/**
 * @author Stepan.Kachan
 */
public interface CarDao {

    Car findByUserName(Car car);

    void save(Car car);

    List<Car> list();
}
