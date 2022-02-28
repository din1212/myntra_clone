package com.clone.myntra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clone.myntra.repository.UserRespository;
import com.clone.myntra.repository.entity.User;

@Service
public class DefaultUserService implements UserService {

	
	@Autowired
	private UserRespository userRepository;
	
	@Override
	public User adduser(User user) {
		return userRepository.save(user);
	}

}
