package com.org.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.dto.CarRequest;
import com.org.service.impl.CarServiceImpl;

@RestController
@Valid
@RequestMapping("/customer")
public class CustomerRestController {

	@Autowired
	CarServiceImpl carServiceImpl;

	@GetMapping("/car/{id}")
	public ResponseEntity<?> getCarById(@Valid @PathVariable Integer id) {
		CarRequest carById = carServiceImpl.getCarById(id);
		return ResponseEntity.status(HttpStatus.OK).body(carById);
	}

	@GetMapping("/getAllCars")
	public ResponseEntity<?> getAllCars() {
		List<CarRequest> cars = carServiceImpl.getAllCars();
		return ResponseEntity.status(HttpStatus.OK).body(cars);
	}

}
