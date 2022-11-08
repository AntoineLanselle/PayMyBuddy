package com.payMyBuddy.app.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.payMyBuddy.app.repository.TransactionUserRepository;

@ExtendWith(MockitoExtension.class)
public class TransactionUserServiceImplTest {
	
	@Mock
	private TransactionUserRepository transactionUserRepository;
	@InjectMocks
	private TransactionUserServiceImpl transactionUserServiceImpl;
	
	
	
}
