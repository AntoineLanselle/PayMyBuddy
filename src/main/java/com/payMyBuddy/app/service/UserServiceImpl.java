package com.payMyBuddy.app.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.payMyBuddy.app.DTO.UserRegistrationDTO;
import com.payMyBuddy.app.exception.AlreadyExistException;
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
	public List<User> getUsers() {
		LOGGER.info("Getting all Users");
		return userRepository.findAll();
	}

	/**
	 * 
	 */
	@Override
	public Optional<User> getUserById(Integer id) {
		LOGGER.info("Getting User Id: " + id);
		return userRepository.findById(id);
	}

	/**
	 * 
	 */
	@Override
	public User addUser(User user) throws AlreadyExistException {
		LOGGER.info("Saving User: " + user.getEmail());
		return userRepository.save(user);
	}

	/**
	 * 
	 */
	@Override
	public User saveUser(UserRegistrationDTO userRegistration) throws AlreadyExistException {
		LOGGER.info("Saving User: " + userRegistration.getEmail());
		User user = new User(userRegistration.getEmail(), passwordEncoder.encode(userRegistration.getPassword()));
		return userRepository.save(user);
	}

	/**
	 * 
	 */
	@Override
	public User updateUser(User user) throws RessourceNotFoundException {
		if (getUserById(user.getId()) == null) {
			String error = "User: " + user.getEmail() + " not found";
			LOGGER.error(error);
			throw new RessourceNotFoundException(error);
		}
		LOGGER.info("Updating User Id: " + user.getId());
		return userRepository.save(user);
	}

	/**
	 * 
	 */
	@Override
	public void deleteUser(User user) throws RessourceNotFoundException {
		if (getUserById(user.getId()) == null) {
			String error = "User: " + user.getEmail() + " not found";
			LOGGER.error(error);
			throw new RessourceNotFoundException(error);
		}
		LOGGER.info("Deleting User Id: " + user.getId());
		userRepository.delete(user);
	}

	/**
	 * 
	 */
	@Override
	public void deleteUser(Integer id) throws RessourceNotFoundException {
		if (getUserById(id) == null) {
			String error = "User: " + id + " not found";
			LOGGER.error(error);
			throw new RessourceNotFoundException(error);
		}
		LOGGER.info("Deleting User Id: " + id);
		userRepository.deleteById(id);
	}

	/**
	 * 
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException(username + "not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), null);
	}

}
