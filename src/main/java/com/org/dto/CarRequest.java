package com.org.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CarRequest {

	    @NotBlank(message = "Car name is required")
	    @Size(min = 2, max = 50, message = "Car name must be between 2 and 50 characters")
	    private String carName;

	    @NotNull(message = "Price is required")
	    @Positive(message = "Price must be a positive value")
	    private Double price;

	    @NotBlank(message = "Brand is required")
	    @Size(min = 2, max = 50, message = "Brand must be between 2 and 50 characters")
	    private String brand;
	}
