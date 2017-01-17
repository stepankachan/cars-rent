package com.courses.service;

import com.courses.model.Car;

import java.util.List;

/**
 * @author Stepan.Kachan
 */
public interface CarService {

    List<Car> findAllCars();

    void saveCar(Car car);

    void updateCar(Car car);

    Car findById(int id);
}
