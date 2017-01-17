package com.courses.dao;

import com.courses.model.Car;

import java.util.List;

/**
 * @author Stepan.Kachan
 */
public interface CarDao {

    List<Car> list();

    void save(Car car);

    Car findById(int id);
}
