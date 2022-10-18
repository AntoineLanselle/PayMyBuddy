package com.payMyBuddy.app.service;

import java.util.Optional;

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

	@Autowired
	public UserRepository userRepository;
	
	public Iterable<User> getUsers() {
		return userRepository.findAll();
	}
	
	public Optional<User> getUserById(Integer id) {
		return userRepository.findById(id);
	}	
	
}
