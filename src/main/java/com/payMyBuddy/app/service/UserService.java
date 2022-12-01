package com.payMyBuddy.app.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.payMyBuddy.app.DTO.UserRegistrationDTO;
import com.payMyBuddy.app.exception.AlreadyExistException;
import com.payMyBuddy.app.exception.ImpossibleConnectionException;
import com.payMyBuddy.app.exception.RessourceNotFoundException;
import com.payMyBuddy.app.model.User;

/**
 * 
 * @author Antoine
 */
public interface UserService extends UserDetailsService {

	public User addUser(User user) throws AlreadyExistException;
	
	public User saveUser(UserRegistrationDTO userRegistration) throws AlreadyExistException;

	public User updateUser(User user) throws RessourceNotFoundException;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

	public User getCurrentUser();

	public User findByEmail(String email);

	public Boolean existsByEmail(String email);

	public void saveConnection(User user, String email) throws ImpossibleConnectionException, RessourceNotFoundException;

}
