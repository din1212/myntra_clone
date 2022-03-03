package com.clone.myntra.service;

import com.clone.myntra.repository.entity.User;

public interface UserService {

	User adduser(User user);

	Boolean validateUser(String userName, String password);

}
