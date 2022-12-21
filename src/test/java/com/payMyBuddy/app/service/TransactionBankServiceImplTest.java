package com.payMyBuddy.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.payMyBuddy.app.DTO.TransferBankDTO;
import com.payMyBuddy.app.exception.ImpossibleTransferException;
import com.payMyBuddy.app.exception.RessourceNotFoundException;
import com.payMyBuddy.app.exception.TransactionFailedException;
import com.payMyBuddy.app.model.TransactionBank;
import com.payMyBuddy.app.model.User;
import com.payMyBuddy.app.repository.TransactionBankRepository;

@ExtendWith(MockitoExtension.class)
public class TransactionBankServiceImplTest {

	@Mock
	private TransactionBankRepository transactionBankRepository;
	@Mock
	private UserService userService;
	@InjectMocks
	private TransactionBankServiceImpl transactionBankServiceImpl;

	@Test
	public void addTransactionBank_ShouldReturnTrasactionBank() {
		// GIVEN
		User user = new User("userTest@gmail.com", "userTest");
		TransactionBank transactionBank = new TransactionBank(user, "account", 1);
		when(transactionBankRepository.save(transactionBank)).thenReturn(transactionBank);

		// WHEN
		TransactionBank result = transactionBankServiceImpl.addTransactionBank(transactionBank);

		// THEN
		assertEquals(transactionBank, result);
	}

	@Test
	public void transferOnBalance_ShouldReturnUser()
			throws ImpossibleTransferException, RessourceNotFoundException, TransactionFailedException {
		// GIVEN
		User user = new User("userTest@gmail.com", "userTest");
		TransferBankDTO transferBankDTO = new TransferBankDTO("bankAccount", 100);
		when(userService.updateUser(user)).thenReturn(user);

		// WHEN
		User result = transactionBankServiceImpl.transferOnBalance(transferBankDTO, user);

		// THEN
		assertEquals(user, result);
	}

	@Test
	public void transferOnBalance_ShouldThrowImpossibleTransferException()
			throws ImpossibleTransferException, RessourceNotFoundException {
		// GIVEN
		User user = new User("userTest@gmail.com", "userTest");
		TransferBankDTO transferOne = new TransferBankDTO("", 100);
		TransferBankDTO transferTwo = new TransferBankDTO("bankAccount", 0);

		// WHEN // THEN
		assertThrows(ImpossibleTransferException.class, () -> {
			transactionBankServiceImpl.transferOnBalance(transferOne, user);
		});
		assertThrows(ImpossibleTransferException.class, () -> {
			transactionBankServiceImpl.transferOnBalance(transferTwo, user);
		});
	}

	@Test
	public void transferOnBank_ShouldReturnUser()
			throws ImpossibleTransferException, RessourceNotFoundException, TransactionFailedException {
		// GIVEN
		User user = new User("userTest@gmail.com", "userTest");
		user.setBalance(50);
		TransferBankDTO transferBankDTO = new TransferBankDTO("bankAccount", 50);
		when(userService.updateUser(user)).thenReturn(user);

		// WHEN
		User result = transactionBankServiceImpl.transferOnBank(transferBankDTO, user);

		// THEN
		assertEquals(user, result);
	}

	@Test
	public void transferOnBank_ShouldThrowImpossibleTransferException()
			throws ImpossibleTransferException, RessourceNotFoundException {
		// GIVEN
		User user = new User("userTest@gmail.com", "userTest");
		user.setBalance(10);
		TransferBankDTO transferOne = new TransferBankDTO("", 5);
		TransferBankDTO transferTwo = new TransferBankDTO("bankAccount", 0);
		TransferBankDTO transferThree = new TransferBankDTO("bankAccount", 20);

		// WHEN // THEN
		assertThrows(ImpossibleTransferException.class, () -> {
			transactionBankServiceImpl.transferOnBank(transferOne, user);
		});
		assertThrows(ImpossibleTransferException.class, () -> {
			transactionBankServiceImpl.transferOnBank(transferTwo, user);
		});
		assertThrows(ImpossibleTransferException.class, () -> {
			transactionBankServiceImpl.transferOnBank(transferThree, user);
		});
	}

}
