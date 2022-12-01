package com.payMyBuddy.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.payMyBuddy.app.DTO.UserRegistrationDTO;
import com.payMyBuddy.app.exception.AlreadyExistException;
import com.payMyBuddy.app.exception.RessourceNotFoundException;
import com.payMyBuddy.app.model.User;
import com.payMyBuddy.app.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
	
	@Mock
	private UserRepository userRepository;
	@InjectMocks
	private UserServiceImpl userServiceImpl;

	private static List<User> listAllUsers;

	@BeforeAll
	public static void init() {
		listAllUsers = new ArrayList<User>();

		User userOne = new User("userOne@gmail.com", "userOne");
		User userTwo = new User("userTwo@gmail.com", "userTwo");
		User userThree = new User("userThree@gmail.com", "userThree");
		User userFore = new User("userFore@gmail.com", "userFore");
		User userFive = new User("userFive@gmail.com", "userFive");

		listAllUsers.add(userOne);
		listAllUsers.add(userTwo);
		listAllUsers.add(userThree);
		listAllUsers.add(userFore);
		listAllUsers.add(userFive);
	}

	@Test
	public void addUser_ShouldAddUserInParameterInRepository() throws AlreadyExistException {
		// GIVEN
		User userSixe = new User("userSixe@gmail.com", "userSixe");
		
		// WHEN
		userServiceImpl.addUser(userSixe);
		
		// THEN
		verify(userRepository, times(1)).save(userSixe);
		//assertTrue(listAllUsers.size() == 6);
	}
	
	@Test
	public void addUser_ShouldthrowAlreadyExistException() throws AlreadyExistException {
		// GIVEN
		User userSixe = new User("userSixe@gmail.com", "userSixe");
		
		// WHEN

		
		// THEN
		//verify(userRepository, times(1)).save(userSixe);
		assertThrows(AlreadyExistException.class, () -> {userServiceImpl.addUser(userSixe);});
	}
	
	@Test
	public void saveUser_ShouldSaveUserWithUserRegistrationDTO() throws AlreadyExistException {
		// GIVEN
		UserRegistrationDTO userRegistration = new UserRegistrationDTO("userSixe@gmail.com", "userSixe");
		
		// WHEN
		userServiceImpl.saveUser(userRegistration);
		
		// THEN
		//verify(userRepository, times(1)).save(userSixe);
		//assertTrue(listAllUsers.size() == 6);
	}
	
	@Test
	public void saveUser_ShouldthrowAlreadyExistException() throws AlreadyExistException {
		// GIVEN
		User userSixe = new User("userSixe@gmail.com", "userSixe");
		UserRegistrationDTO userRegistration = new UserRegistrationDTO("userSixe@gmail.com", "userSixe");
		
		// WHEN
		userServiceImpl.addUser(userSixe);
		
		// THEN
		verify(userRepository, times(1)).save(userSixe);
		assertThrows(AlreadyExistException.class, () -> {userServiceImpl.saveUser(userRegistration);});
	}
	
	@Test
	public void updateUser_ShouldUpdateUserInParameter() throws RessourceNotFoundException {
		// GIVEN
		User userSixe = new User("userSixe@gmail.com", "newUserSixe");

		// WHEN
		userServiceImpl.updateUser(userSixe);
		
		// THEN
		assertEquals("newUserSixe" , userServiceImpl.findByEmail("userSixe@gmail.com").getPassword());
	}
	
	@Test
	public void updateUser_ShouldThrowRessourceNotFoundException() throws RessourceNotFoundException {
		// GIVEN
		User userSixe = new User("userSixe@gmail.com", "newUserSixe");

		// WHEN
		userServiceImpl.updateUser(userSixe);
		
		// THEN
		assertEquals("newUserSixe" , userServiceImpl.findByEmail("userSixe@gmail.com").getPassword());
	}

	@Test
	public void getUserByEmail_ShouldReturnTheUserWithIdInParameter() {
		// GIVEN
		User user = new User("userTest@gmail.com", "userTest");
		when(userRepository.findByEmail("userTest@gmail.com")).thenReturn(user);

		// WHEN

		// THEN
		assertEquals("userTest@gmail.com" , userServiceImpl.findByEmail("userTest@gmail.com").getEmail());
	}

}
