package com.payMyBuddy.app.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.payMyBuddy.app.exception.AlreadyExistException;
import com.payMyBuddy.app.exception.ImpossibleConnectionException;
import com.payMyBuddy.app.exception.RessourceNotFoundException;
import com.payMyBuddy.app.model.User;
import com.payMyBuddy.app.service.UserService;

@ExtendWith(MockitoExtension.class)
public class ContactControllerTest {

	@Mock
	private UserService userService;
	@InjectMocks
	private ContactController contactController;

	@Test
	public void getContactPage_ShouldReturnContactPage() {
		// GIVEN
		Model m = new ExtendedModelMap();
		User user = new User("user@gmail.com", "user");
		when(userService.getCurrentUser()).thenReturn(user);

		// WHEN
		String result = contactController.getContactPage(m);

		// THEN
		assertEquals("contact", result);
	}

	@Test
	public void addConnection_ShouldRedirectOnContactPageSuccess() throws AlreadyExistException, RessourceNotFoundException {
		// GIVEN
		String email = "contact@gmail.com";
		User user = new User("user@gmail.com", "user");
		when(userService.getCurrentUser()).thenReturn(user);

		// WHEN
		String result = contactController.addConnection(email);

		// THEN
		assertEquals("redirect:/contact?sucess", result);
	}
	
	@Test
	public void addConnection_ShouldRedirectOnContactPageError() throws AlreadyExistException, RessourceNotFoundException, ImpossibleConnectionException {
		// GIVEN
		String email = "contact@gmail.com";
		User user = new User("user@gmail.com", "user");
		when(userService.getCurrentUser()).thenReturn(user);
		doThrow(new ImpossibleConnectionException("")).when(userService).saveConnection(user, email);

		// WHEN
		String result = contactController.addConnection(email);

		// THEN
		assertEquals("redirect:/contact?error", result);
	}
	
	@Test
	public void deleteConnection_ShouldRedirectOnContactPage() throws RessourceNotFoundException {
		// GIVEN
		String email = "contact@gmail.com";
		User user = new User("user@gmail.com", "user");
		when(userService.getCurrentUser()).thenReturn(user);

		// WHEN
		String result = contactController.deleteConnection(email);

		// THEN
		assertEquals("redirect:/contact", result);
	}
	
}
