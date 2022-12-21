package com.payMyBuddy.app.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.payMyBuddy.app.DTO.UserRegistrationDTO;
import com.payMyBuddy.app.exception.AlreadyExistException;
import com.payMyBuddy.app.exception.ImpossibleTransferException;
import com.payMyBuddy.app.exception.RessourceNotFoundException;
import com.payMyBuddy.app.service.UserService;

@ExtendWith(MockitoExtension.class)
public class RegistrationControllerTest {

	@Mock
	private UserService userService;
	@InjectMocks
	private RegistrationController registrationController;
	
	@Test
	public void getRegistrationPage_ShouldReturnRegistrationPage() {
		// GIVEN	
		Model m = new ExtendedModelMap();

		// WHEN
		String result = registrationController.getRegistrationPage(m);

		// THEN
		assertEquals("registration", result);
	}

	@Test
	public void postRegisterUserAccount_ShouldRedirectOnRegisterPageSuccess() throws RessourceNotFoundException, ImpossibleTransferException, AlreadyExistException {
		// GIVEN	
		UserRegistrationDTO userRegistration = new UserRegistrationDTO();

		// WHEN
		String result = registrationController.postRegisterUserAccount(userRegistration);

		// THEN
		assertEquals("redirect:/registration?success", result);
	}
	
	@Test
	public void postRegisterUserAccount_ShouldRedirectOnRegisterPageError() throws RessourceNotFoundException, ImpossibleTransferException, AlreadyExistException {
		// GIVEN	
		UserRegistrationDTO userRegistration = new UserRegistrationDTO();
		doThrow(new AlreadyExistException("")).when(userService).saveUser(userRegistration);
		
		// WHEN
		String result = registrationController.postRegisterUserAccount(userRegistration);

		// THEN
		assertEquals("redirect:/registration?error", result);
	}
	
}
