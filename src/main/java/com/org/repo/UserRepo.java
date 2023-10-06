package com.org.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.domain.UserDtls;

public interface UserRepo extends JpaRepository<UserDtls,Integer> {
	
	UserDtls findByUserEmail(String userEmail);
	

}
