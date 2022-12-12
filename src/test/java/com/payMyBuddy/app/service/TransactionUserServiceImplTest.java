package com.payMyBuddy.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.payMyBuddy.app.DTO.TransferUserDTO;
import com.payMyBuddy.app.exception.ImpossibleTransferException;
import com.payMyBuddy.app.exception.RessourceNotFoundException;
import com.payMyBuddy.app.model.TransactionUser;
import com.payMyBuddy.app.model.User;
import com.payMyBuddy.app.repository.TransactionUserRepository;

@ExtendWith(MockitoExtension.class)
public class TransactionUserServiceImplTest {
	
	@Mock
	private TransactionUserRepository transactionUserRepository;
	@Mock
	private UserService userService;
	@InjectMocks
	private TransactionUserServiceImpl transactionUserServiceImpl;
	
	@Test
	public void addTransactionUser_ShouldReturnTrasactionUser() {
		// GIVEN
		User payer = new User("payerTest@gmail.com", "payerTest");
		User receiver = new User("receiverTest@gmail.com", "receiverTest");
		TransactionUser transactionUser = new TransactionUser(payer, receiver, "", 10, 0);
		when(transactionUserRepository.save(transactionUser)).thenReturn(transactionUser);

		// WHEN
		TransactionUser result = transactionUserServiceImpl.addTransactionUser(transactionUser);

		// THEN
		assertEquals(transactionUser, result);
	}
	
	@Test
	public void newTransferWithUser_ShouldUpdateUser() throws RessourceNotFoundException, ImpossibleTransferException {
		// GIVEN
		User payer = new User("payerTest@gmail.com", "payerTest");
		payer.setBalance(20);
		User receiver = new User("receiverTest@gmail.com", "receiverTest");
		User applicationBalance = new User("ApplicationBalance", "Appplication");
		TransferUserDTO transferDTO = new TransferUserDTO(receiver.getEmail(), "", 10);
		when(userService.findByEmail(receiver.getEmail())).thenReturn(receiver);
		when(userService.findByEmail("applicationBalance")).thenReturn(applicationBalance);
		
		// WHEN
		transactionUserServiceImpl.newTransferWithUser(transferDTO, payer);

		// THEN
		verify(userService, times(1)).updateUser(payer);
		verify(userService, times(1)).updateUser(receiver);
	}
	
	@Test
	public void newTransferWithUser_ShouldThrowImpossibleTransferException() {
		// GIVEN
		User payer = new User("payerTest@gmail.com", "payerTest");
		User receiver = new User("receiverTest@gmail.com", "receiverTest");
		TransferUserDTO transferDTO = new TransferUserDTO(receiver.getEmail(), "", 0);

		// WHEN // THEN	
		assertThrows(ImpossibleTransferException.class, () -> {
			transactionUserServiceImpl.newTransferWithUser(transferDTO, payer);
		});
	}
	
	@Test
	public void calculateTax_ShouldReturnPercentOfAmountInParameter() {
		// GIVEN // WHEN
		double resultOne = transactionUserServiceImpl.calculateTax(100);
		double resultTwo = transactionUserServiceImpl.calculateTax(0.20);
		
		// THEN
		assertEquals(0.5, resultOne);
		assertEquals(0.01, resultTwo);
	}
	
	@Test
	public void getPageNumber_ShouldReturnUpperInteger() {
		// GIVEN
		User userOne = new User("payerTest@gmail.com", "payerTest");
		User userTwo = new User("payerTest@gmail.com", "payerTest");
		List<TransactionUser> transactionsOne = new ArrayList<TransactionUser>();
		List<TransactionUser> transactionsTwo = new ArrayList<TransactionUser>();
		for(int i=0; i<9; i++) {
			TransactionUser transaction = new TransactionUser();
			transactionsOne.add(transaction);
			transactionsTwo.add(transaction);
		}
		userOne.setTransactionsPayer(transactionsOne);
		transactionsTwo.add(new TransactionUser());
		userTwo.setTransactionsPayer(transactionsTwo);
		
		// WHEN
		int resultOne = transactionUserServiceImpl.getPageNumber(3, userOne);
		int resultTwo = transactionUserServiceImpl.getPageNumber(3, userTwo);
		
		// THEN
		assertEquals(3, resultOne);
		assertEquals(4, resultTwo);
	}
	
}
