package com.org.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.domain.Car;

public interface CarRepo  extends JpaRepository<Car, Integer>{
	Car findByCarName(String name);

}
