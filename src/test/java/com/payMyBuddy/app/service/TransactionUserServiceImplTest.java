package com.payMyBuddy.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.payMyBuddy.app.DTO.TransferUserDTO;
import com.payMyBuddy.app.exception.ImpossibleTransferException;
import com.payMyBuddy.app.exception.RessourceNotFoundException;
import com.payMyBuddy.app.exception.TransactionFailedException;
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
	public void newTransferWithUser_ShouldUpdateUser()
			throws RessourceNotFoundException, ImpossibleTransferException, TransactionFailedException {
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

}
