package com.clone.myntra.service;

import java.util.Optional;

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

	@Override
	public Boolean validateUser(String userName, String password) {
		System.out.println("Validating the userName:"+ userName +" Password::" +password);

		Optional<User> user = userRepository.findByUserName(userName);
		System.out.println("Printing the User details from DB::"+ user.get());
		if(user.isPresent()) {
			return user.get().getPassword().equals(password);
		} else {
			return false;
		}
	}
}
