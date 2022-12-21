package com.payMyBuddy.app.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.payMyBuddy.app.DTO.UserRegistrationDTO;
import com.payMyBuddy.app.exception.AlreadyExistException;
import com.payMyBuddy.app.exception.ImpossibleConnectionException;
import com.payMyBuddy.app.exception.RessourceNotFoundException;
import com.payMyBuddy.app.model.User;
import com.payMyBuddy.app.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

	@Mock
	private UserRepository userRepository;
	@Mock
	private BCryptPasswordEncoder passwordEncoder;
	@InjectMocks
	private UserServiceImpl userServiceImpl;

	@Test
	public void addUser_ShouldReturnUser() throws AlreadyExistException {
		// GIVEN
		User userSixe = new User("userSixe@gmail.com", "userSixe");
		when(userRepository.save(userSixe)).thenReturn(userSixe);

		// WHEN
		User result = userServiceImpl.addUser(userSixe);

		// THEN
		assertEquals(userSixe, result);
	}

	@Test
	public void addUser_ShouldthrowAlreadyExistException() throws AlreadyExistException {
		// GIVEN
		User userSixe = new User("userSixe@gmail.com", "userSixe");
		when(userServiceImpl.existsByEmail(userSixe.getEmail()) || userSixe.getEmail().equals("")).thenReturn(true);

		// WHEN // THEN
		assertThrows(AlreadyExistException.class, () -> {
			userServiceImpl.addUser(userSixe);
		});
	}

	@Test
	public void saveUser_ShouldthrowAlreadyExistException() throws AlreadyExistException {
		// GIVEN
		UserRegistrationDTO userRegistration = new UserRegistrationDTO("userSixe@gmail.com", "userSixe");
		when(userServiceImpl.existsByEmail("userSixe@gmail.com")).thenReturn(true);

		// WHEN // THEN
		assertThrows(AlreadyExistException.class, () -> {
			userServiceImpl.saveUser(userRegistration);
		});
	}

	@Test
	public void updateUser_ShouldReturnUserInParameter() throws RessourceNotFoundException {
		// GIVEN
		User userSixe = new User("userSixe@gmail.com", "newUserSixe");
		when(!userServiceImpl.existsByEmail(userSixe.getEmail())).thenReturn(true);
		when(userRepository.save(userSixe)).thenReturn(userSixe);

		// WHEN
		User result = userServiceImpl.updateUser(userSixe);

		// THEN
		assertEquals(userSixe, result);
	}

	@Test
	public void updateUser_ShouldThrowRessourceNotFoundException() throws RessourceNotFoundException {
		// GIVEN
		User userSixe = new User("userSixe@gmail.com", "newUserSixe");
		when(!userServiceImpl.existsByEmail(userSixe.getEmail())).thenReturn(false);

		// WHEN // THEN
		assertThrows(RessourceNotFoundException.class, () -> {
			userServiceImpl.updateUser(userSixe);
		});
	}

	@Test
	public void getCurrentUser_ShouldReturnNull() {
		// GIVEN
		when(Authentication.class != null).thenReturn(false);

		// WHEN
		User result = userServiceImpl.getCurrentUser();

		// THEN
		assertEquals(null, result);
	}

	@Test
	public void existsByEmail_ShouldReturnBoolean() {
		// GIVEN
		String email = "userTest@gmail.com";
		when(userRepository.existsByEmail(email)).thenReturn(true);

		// WHEN
		Boolean result = userServiceImpl.existsByEmail(email);
		
		// THEN
		assertTrue(result);
	}

	@Test
	public void findByEmail_ShouldReturnUserWithEmailInParamater() {
		// GIVEN
		String email = "userTest@gmail.com";
		User user = new User("userTest@gmail.com", "userTest");
		when(userRepository.findByEmail(email)).thenReturn(user);

		// WHEN
		User result = userServiceImpl.findByEmail(email);
		
		// THEN
		assertEquals(email, result.getEmail());
	}
	
	@Test
	public void loeadUserByUsername_ShouldReturnUserDetails() {
		// GIVEN
		User user = new User("userTest@gmail.com", "userTest");
		when(userRepository.findByEmail("userTest@gmail.com")).thenReturn(user);
		
		// WHEN
		UserDetails result = userServiceImpl.loadUserByUsername("userTest@gmail.com");
		
		// THEN
		assertEquals("userTest@gmail.com", result.getUsername());
		assertEquals("userTest", result.getPassword());
	}
	
	
	@Test
	public void loeadUserByUsername_ShouldThrowUsernameNotFoundException() {
		// GIVEN
		
		// WHEN // THEN
		assertThrows(UsernameNotFoundException.class, () -> {
			userServiceImpl.loadUserByUsername("userTest@gmail.com");
		});
		
	}

	@Test
	public void saveConnection_ShouldReturnUserInParameter()
			throws RessourceNotFoundException, ImpossibleConnectionException {
		// GIVEN
		User user = new User("userTest@gmail.com", "userTest");
		user.setConnections(new ArrayList<User>());
		User connection = new User("connection@gmail.com", "connection");

		when(userRepository.findByEmail(connection.getEmail())).thenReturn(connection);
		when(userRepository.existsByEmail(user.getEmail())).thenReturn(true);
		when(userRepository.save(user)).thenReturn(user);

		// WHEN
		User result = userServiceImpl.saveConnection(user, connection.getEmail());

		// THEN
		assertEquals(user, result);
	}

	@Test
	public void saveConnection_ShouldThrowImpossibleConnectionException() {
		// GIVEN
		User user = new User("userTest@gmail.com", "userTest");
		String email = "connection@gmail.com";
		when(userServiceImpl.findByEmail(email)).thenReturn(null);

		// WHEN // THEN
		assertThrows(ImpossibleConnectionException.class, () -> {
			userServiceImpl.saveConnection(user, email);
		});
	}

}
