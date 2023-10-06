package com.org.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "Car_TBL")
public class Car {
	@Id
	@GeneratedValue
	private Integer carId;

	private String carName;

	private Double price;

	private String brand;

}
