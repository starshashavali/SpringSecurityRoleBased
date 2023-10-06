package com.org.service;

import java.util.List;
import java.util.Optional;

import com.org.dto.CarRequest;

public interface ICarService {

	public String saveCar(CarRequest request);

	public List<CarRequest> getAllCars();

	public CarRequest getCarById(Integer id);

}
