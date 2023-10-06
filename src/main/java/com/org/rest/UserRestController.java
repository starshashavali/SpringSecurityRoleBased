package com.org.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.dto.CarRequest;
import com.org.dto.UserRequest;
import com.org.service.ICarService;
import com.org.service.impl.IUserDtlsServiceImpl;

@RestController
@Validated
@RequestMapping("/admin")
public class UserRestController {

	@Autowired
	IUserDtlsServiceImpl iuserDtlsServiceImpl;

	@Autowired
	ICarService carService;

	@PostMapping("/saveCar")
	public ResponseEntity<?> saveCar(@Valid @RequestBody CarRequest request) {
		String saveCar = carService.saveCar(request);
		return ResponseEntity.status(HttpStatus.OK).body(saveCar);

	}

	// =============================

	@GetMapping("/krishna")
	public String getGod() {
		return "Here ramaa hare ramaa raamaaa ramaa hare hare";
	}

	@PostMapping("/saveUser")
	public ResponseEntity<?> saveUser(@Valid @RequestBody UserRequest request) {
		boolean saveUser = iuserDtlsServiceImpl.saveUser(request);
		if (saveUser) {
			return ResponseEntity.status(HttpStatus.OK).body(saveUser);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(saveUser);

	}

	@GetMapping("/login/{email}")
	public ResponseEntity<?> loginUser(@Valid @PathVariable String email) {
		UserDetails loginUser = iuserDtlsServiceImpl.loadUserByUsername(email);
		return ResponseEntity.status(HttpStatus.OK).body(loginUser);
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<?> getByUser(@Valid @PathVariable Integer id) {
		UserRequest dto = iuserDtlsServiceImpl.getUserById(id);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

	@GetMapping("/active/{id}")
	public ResponseEntity<?> disableEnable(@PathVariable Integer id) {
		String activeStatus = iuserDtlsServiceImpl.softActiveDeActive(id);
		return ResponseEntity.status(HttpStatus.OK).body(activeStatus);

	}

	@DeleteMapping("/deleteUserById/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable Integer id) {
		try {
			boolean deleted = iuserDtlsServiceImpl.deleteUserById(id);
			if (deleted) {
				return ResponseEntity.ok("User with ID " + id + " has been deleted.");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID " + id + " not found.");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while deleting the user.");
		}
	}

}
