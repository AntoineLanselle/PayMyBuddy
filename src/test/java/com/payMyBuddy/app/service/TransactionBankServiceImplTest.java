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

import com.payMyBuddy.app.DTO.TransferBankDTO;
import com.payMyBuddy.app.exception.ImpossibleTransferException;
import com.payMyBuddy.app.exception.RessourceNotFoundException;
import com.payMyBuddy.app.model.TransactionBank;
import com.payMyBuddy.app.model.User;
import com.payMyBuddy.app.repository.TransactionBankRepository;
import com.payMyBuddy.app.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class TransactionBankServiceImplTest {
	
	@Mock
	private TransactionBankRepository transactionBankRepository;
	@Mock
	private UserRepository userRepository;
	@InjectMocks
	private TransactionBankServiceImpl transactionBankServiceImpl;
	
	static List<TransactionBank> listAllTransfer;
	/*
	@BeforeAll
	public static void init() {
		listAllTransfer = new ArrayList<TransactionBank>();

		TransactionBank transactionOne = new TransactionBank();
		TransactionBank transactionTwo = new TransactionBank();
		TransactionBank transactionThree = new TransactionBank();
		TransactionBank transactionFore = new TransactionBank();
		TransactionBank transactionFive = new TransactionBank();
		
		listAllTransfer.add(transactionOne);
		listAllTransfer.add(transactionTwo);
		listAllTransfer.add(transactionThree);
		listAllTransfer.add(transactionFore);
		listAllTransfer.add(transactionFive);
	}
	
	@Test
	public void addTransactionBank_ShouldAddTransactionInParameterInRepository() {
		// GIVEN
		TransactionBank transactionBank = new TransactionBank();

		User user = new User("userTest@gmail.com", "userTest");
		
		transactionBank.setAmount(100);
		transactionBank.setBankaccount("bankAccount");
		transactionBank.setUser(user);
		
		when(transactionBankRepository.save(transactionBank)).thenReturn(transactionBank);
		
		// WHEN
		
		// THEN
		assertEquals(transactionBank, transactionBankServiceImpl.addTransactionBank(transactionBank));
	}
	
	@Test
	public void transferOnBalance_ShouldAddAmountInUserBalance() throws ImpossibleTransferException, RessourceNotFoundException {
		// GIVEN
		TransferBankDTO transferBankDTO = new TransferBankDTO();
		transferBankDTO.setAmount(100);
		transferBankDTO.setBankAccount("bankAccount");
		
		User user = new User("userTest@gmail.com", "userTest");
		user.setBalance(0);
		
		TransactionBank transactionBank = new TransactionBank();
		transactionBank.setAmount(100);
		transactionBank.setBankaccount("bankAccount");
		transactionBank.setUser(user);

		when(transactionBankRepository.save(transactionBank)).thenReturn(transactionBank);
		when(userRepository.save(user)).thenReturn(user);

		// WHEN
		transactionBankServiceImpl.transferOnBalance(transferBankDTO, user);
		
		// THEN
		verify(userRepository, times(1)).save(user);
		verify(transactionBankRepository, times(1)).save(transactionBank);
	}
	
	@Test
	public void transferOnBalance_ShouldThrowImpossibleTransferException() throws ImpossibleTransferException, RessourceNotFoundException {
		// GIVEN
		TransferBankDTO transferBankDTOOne = new TransferBankDTO();
		transferBankDTOOne.setAmount(100);
		transferBankDTOOne.setBankAccount(""); //bank account not pute by user
		
		TransferBankDTO transferBankDTOTwo = new TransferBankDTO();
		transferBankDTOTwo.setAmount(0); //amount not pute by user
		transferBankDTOTwo.setBankAccount("bankAccount");
		
		User user = new User("userTest@gmail.com", "userTest");

		// WHEN
		
		// THEN
		assertThrows(ImpossibleTransferException.class, () -> {transactionBankServiceImpl.transferOnBalance(transferBankDTOOne, user);});
		assertThrows(ImpossibleTransferException.class, () -> {transactionBankServiceImpl.transferOnBalance(transferBankDTOTwo, user);});
	}
	
	@Test
	public void transferOnBank_ShouldThrowImpossibleTransferException() throws ImpossibleTransferException, RessourceNotFoundException {
		// GIVEN
		TransferBankDTO transferBankDTOOne = new TransferBankDTO();
		transferBankDTOOne.setAmount(100);
		transferBankDTOOne.setBankAccount(""); //bank account not pute by user
		
		TransferBankDTO transferBankDTOTwo = new TransferBankDTO();
		transferBankDTOTwo.setAmount(0); //amount not pute by user
		transferBankDTOTwo.setBankAccount("bankAccount");
		
		TransferBankDTO transferBankDTOThree = new TransferBankDTO();
		transferBankDTOThree.setAmount(100); //amount bigger than user balance
		transferBankDTOThree.setBankAccount("bankAccount");
		
		User user = new User("userTest@gmail.com", "userTest");
		user.setBalance(20);

		// WHEN
		
		// THEN
		assertThrows(ImpossibleTransferException.class, () -> {transactionBankServiceImpl.transferOnBank(transferBankDTOOne, user);});
		assertThrows(ImpossibleTransferException.class, () -> {transactionBankServiceImpl.transferOnBank(transferBankDTOTwo, user);});
		assertThrows(ImpossibleTransferException.class, () -> {transactionBankServiceImpl.transferOnBank(transferBankDTOThree, user);});
	}
	*/
}
