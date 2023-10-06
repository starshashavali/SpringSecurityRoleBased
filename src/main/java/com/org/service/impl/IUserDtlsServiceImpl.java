package com.org.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.org.domain.UserDtls;
import com.org.dto.UserRequest;
import com.org.exception.DuplicateEmailException;
import com.org.exception.EmailNotFoundException;
import com.org.exception.UserIdNotFoundException;
import com.org.exception.UserInActiveException;
import com.org.repo.UserRepo;

@Service
public class IUserDtlsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepo userRepo;

	public boolean saveUser(UserRequest request) {
		UserDtls userEmail = userRepo.findByUserEmail(request.getUserEmail());
		if (userEmail != null) {
			throw new DuplicateEmailException("Duplicate Email...");
		}
		UserDtls entity = new UserDtls();

		BeanUtils.copyProperties(request, entity);

		PasswordEncoder encoder = new BCryptPasswordEncoder();
		String pwd = encoder.encode(request.getUserPwd());
		entity.setUserPwd(pwd);

		entity.setActiveUser(true);
		userRepo.save(entity);
		return true;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserDtls userEntity = userRepo.findByUserEmail(email);
		if (userEntity == null) {
			throw new EmailNotFoundException("Email not found...");
		}
		if (!userEntity.isActiveUser()) {
			throw new UserInActiveException("InActive User credentials...");
		}

		return User.builder().username(userEntity.getUserEmail()).password(userEntity.getUserPwd())
				.authorities(userEntity.getUserRole()).build();

	}

	public boolean updateById(Integer id, UserRequest request) {
		Optional<UserDtls> entity = userRepo.findById(id);
		if (entity.isPresent()) {
			UserDtls userDtls = entity.get();
			BeanUtils.copyProperties(request, userDtls);
			userRepo.save(userDtls);
			return true;
		}

		return false;
	}

	public UserRequest getUserById(Integer id) {
		Optional<UserDtls> userEntity = userRepo.findById(id);
		if (userEntity.isPresent()) {
			UserDtls entity = userEntity.get();
			UserRequest dto = new UserRequest();
			BeanUtils.copyProperties(entity, dto);
			// sensitive data
			dto.setUserPwd(null);
			return dto;
		}

		return null;
	}

	public List<UserRequest> getAllUser() {

		return null;
	}

	public String softActiveDeActive(Integer id) {
		Optional<UserDtls> user = userRepo.findById(id);
		if (!user.isPresent()) {
			throw new UserIdNotFoundException("Invalid user id  :" + id);
		}
		UserDtls userDtls = user.get();
		userDtls.setActiveUser(!userDtls.isActiveUser());
		userRepo.save(userDtls);

		return "Success...";
	}

	public boolean deleteUserById(Integer id) {
		Optional<UserDtls> userEntity = userRepo.findById(id);
		if (userEntity.isPresent()) {
			userRepo.deleteById(id);
			return true;
		}
		return false;
	}

}
