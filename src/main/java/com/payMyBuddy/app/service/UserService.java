package com.payMyBuddy.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.payMyBuddy.app.DTO.UserRegistrationDTO;
import com.payMyBuddy.app.exception.AlreadyExistException;
import com.payMyBuddy.app.exception.RessourceNotFoundException;
import com.payMyBuddy.app.model.User;

/**
 * 
 * @author Antoine
 */
public interface UserService extends UserDetailsService {

	public List<User> getUsers();

	public Optional<User> getUserById(Integer id);

	public User addUser(User user) throws AlreadyExistException;
	
	public User saveUser(UserRegistrationDTO userRegistration) throws AlreadyExistException;

	public User updateUser(User user) throws RessourceNotFoundException;

	public void deleteUser(User user) throws RessourceNotFoundException;

	public void deleteUser(Integer id) throws RessourceNotFoundException;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
