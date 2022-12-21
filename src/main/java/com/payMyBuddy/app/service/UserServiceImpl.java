package com.payMyBuddy.app.service;

import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.payMyBuddy.app.DTO.UserRegistrationDTO;
import com.payMyBuddy.app.exception.AlreadyExistException;
import com.payMyBuddy.app.exception.ImpossibleConnectionException;
import com.payMyBuddy.app.exception.RessourceNotFoundException;
import com.payMyBuddy.app.model.User;
import com.payMyBuddy.app.repository.UserRepository;

/**
 * 
 * @author Antoine
 */
@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	/**
	 * 
	 */
	@Override
	public User addUser(User user) throws AlreadyExistException {
		if (existsByEmail(user.getEmail()) || user.getEmail().equals("")) {
			String error = "User: " + user.getEmail() + " already exist.";
			LOGGER.error(error);
			throw new AlreadyExistException(error);
		} else {
			String info = "Adding in database User: " + user.getEmail();
			LOGGER.info(info);
			return userRepository.save(user);
		}
	}

	/**
	 * 
	 */
	@Override
	public User saveUser(UserRegistrationDTO userRegistration) throws AlreadyExistException {
		User user = new User(userRegistration.getEmail(), passwordEncoder.encode(userRegistration.getPassword()));
		String info = "Trying to save from registration page User: " + user.getEmail();
		LOGGER.info(info);
		return addUser(user);
	}

	/**
	 * 
	 */
	@Override
	public User updateUser(User user) throws RessourceNotFoundException {
		if (!existsByEmail(user.getEmail())) {
			String error = "User: " + user.getEmail() + " not found.";
			LOGGER.error(error);
			throw new RessourceNotFoundException(error);
		} else {
			String info = "Updating in database User: " + user.getEmail();
			LOGGER.info(info);
			return userRepository.save(user);
		}
	}

	/**
	 * 
	 */
	@Override
	public User getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			return userRepository.findByEmail(auth.getName());
		} else {
			return null;
		}
	}

	/**
	 * 
	 */
	@Override
	public Boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	/**
	 * 
	 */
	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	/**
	 * 
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if (user == null) {
			String error = username + "not found";
			throw new UsernameNotFoundException(error);
		} else {
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
					Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
		}
	}

	/**
	 * 
	 */
	@Override
	public User saveConnection(User user, String email)
			throws RessourceNotFoundException, ImpossibleConnectionException {
		User newConnection = findByEmail(email);
		if (newConnection == null || newConnection == user || user.getConnections().contains(newConnection)) {
			String error = "Impossible to make connection between" + user.getEmail() + " and " + email + ".";
			throw new ImpossibleConnectionException(error);
		} else {
			user.getConnections().add(newConnection);
			return updateUser(user);
		}
	}

}
