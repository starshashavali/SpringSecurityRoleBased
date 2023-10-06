package com.org.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Entity
@Data
@Table(name = "User_TBL")
public class UserDtls {
	
		@Id
		@GeneratedValue
		private Integer userId;
		
		private String userName;
		
		private  String userEmail;
		
		private String userPwd;
		
		private boolean activeUser;
		
		private String userRole;

	}

