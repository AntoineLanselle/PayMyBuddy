package com.payMyBuddy.app.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.payMyBuddy.app.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
	
	@Mock
	private UserRepository userRepository;
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
	@Test
	public void getUsers_ShouldReturnTheListOfAllUsers() {
		//GIVEN
		
		
		//WHEN
		
		
		//THEN
		
		
	}
	
}
