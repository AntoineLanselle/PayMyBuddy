package com.payMyBuddy.app.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.payMyBuddy.app.model.User;
import com.payMyBuddy.app.service.UserService;

@ExtendWith(MockitoExtension.class)
public class WebControllerTest {

	@Mock
	private UserService userService;
	@InjectMocks
	private WebController webController;

	@Test
	public void getLoginPage_ShouldReturnLoginPage() {
		// GIVEN

		// WHEN
		String result = webController.getLoginPage();

		// THEN
		assertEquals("login", result);
	}

	@Test
	public void getHomePage_ShouldReturnHomePage() throws Exception {
		// GIVEN	
		Model m = new ExtendedModelMap();
		User user = new User("user@gmail.com", "user");
		when(userService.getCurrentUser()).thenReturn(user);

		// WHEN
		String result = webController.getHomePage(m);

		// THEN
		assertEquals("index", result);
	}

}
