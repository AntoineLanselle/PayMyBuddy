package com.payMyBuddy.app.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.payMyBuddy.app.repository.TransactionBankRepository;

@ExtendWith(MockitoExtension.class)
public class TransactionBankServiceImplTest {
	
	@Mock
	private TransactionBankRepository transactionBankRepository;
	@InjectMocks
	private TransactionBankServiceImpl transactionBankServiceImpl;
	
	

}
