package com.org.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.domain.Car;
import com.org.dto.CarRequest;
import com.org.exception.DuplicateCarException;
import com.org.repo.CarRepo;
import com.org.service.ICarService;

@Service
public class CarServiceImpl implements ICarService {
	@Autowired
	private CarRepo carRepo;

	@Override
	public String saveCar(CarRequest request) {
		Car name = carRepo.findByCarName(request.getCarName());
		if (name != null) {
			throw new DuplicateCarException("Already exists...!!!");
		}
		Car car = new Car();
		BeanUtils.copyProperties(request, car);
		carRepo.save(car);
		return "success";
	}

	@Override
	public List<CarRequest> getAllCars() {
		List<Car> listCars = carRepo.findAll();
		List<CarRequest> list = new ArrayList<>();
		for (Car entity : listCars) {
			CarRequest dto = new CarRequest();
			BeanUtils.copyProperties(entity, dto);
			list.add(dto);
		}

		return list;
	}

	@Override
	public CarRequest getCarById(Integer id) {
		Optional<Car> carId = carRepo.findById(id);
		if (carId.isPresent()) {
			Car car = carId.get();
			CarRequest request = new CarRequest();
			BeanUtils.copyProperties(car, request);
			return request;
		}
		return null;

	}

}
