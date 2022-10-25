package com.payMyBuddy.app.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payMyBuddy.app.model.User;
import com.payMyBuddy.app.repository.UserRepository;

/**
 * 
 * 
 * @author Antoine
 */
@Service
public class UserService {

	private static final Logger LOGGER = LogManager.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;

	public List<User> getUsers() {
		LOGGER.info("Getting all Users");
		return userRepository.findAll();
	}

	public Optional<User> getUserById(Integer id) {
		LOGGER.info("Getting User with Id: " + id);
		return userRepository.findById(id);
	}
	
}
