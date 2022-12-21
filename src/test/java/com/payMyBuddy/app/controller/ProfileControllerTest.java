package com.payMyBuddy.app.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.payMyBuddy.app.DTO.TransferBankDTO;
import com.payMyBuddy.app.exception.AlreadyExistException;
import com.payMyBuddy.app.exception.ImpossibleTransferException;
import com.payMyBuddy.app.exception.RessourceNotFoundException;
import com.payMyBuddy.app.exception.TransactionFailedException;
import com.payMyBuddy.app.model.TransactionBank;
import com.payMyBuddy.app.model.User;
import com.payMyBuddy.app.service.PaginationService;
import com.payMyBuddy.app.service.TransactionBankService;
import com.payMyBuddy.app.service.UserService;

@ExtendWith(MockitoExtension.class)
public class ProfileControllerTest {

	@Mock
	private UserService userService;
	@Mock
	private TransactionBankService transactionBankservice;
	@Mock
	private PaginationService paginationService;
	@InjectMocks
	private ProfileController profileController;

	@Test
	public void getProfilePage_ShouldReturnProfilePage() {
		// GIVEN
		Model m = new ExtendedModelMap();
		User user = new User("user@gmail.com", "user");
		List<TransactionBank> listTransactions = new ArrayList<TransactionBank>();
		user.setTransactionsBank(listTransactions);
		when(userService.getCurrentUser()).thenReturn(user);
		when(paginationService.getPageNumber(3, listTransactions)).thenReturn(0);
		
		// WHEN
		String result = profileController.getProfilePage(m, null, null);

		// THEN
		assertEquals("profile", result);
	}

	@Test
	public void postTransferFromBank_ShouldRedirectOnProfilePageSuccess()
			throws AlreadyExistException, RessourceNotFoundException, TransactionFailedException {
		// GIVEN
		TransferBankDTO transferDTO = new TransferBankDTO("", 0);
		User user = new User("user@gmail.com", "user");
		when(userService.getCurrentUser()).thenReturn(user);

		// WHEN
		String result = profileController.postTransferFromBank(transferDTO);

		// THEN
		assertEquals("redirect:/profile?success", result);
	}

	@Test
	public void postTransferFromBank_ShouldRedirectOnProfilePageError() throws AlreadyExistException,
			RessourceNotFoundException, ImpossibleTransferException, TransactionFailedException {
		// GIVEN
		TransferBankDTO transferDTO = new TransferBankDTO("", 0);
		User user = new User("user@gmail.com", "user");
		when(userService.getCurrentUser()).thenReturn(user);
		doThrow(new ImpossibleTransferException("")).when(transactionBankservice).transferOnBalance(transferDTO, user);

		// WHEN
		String result = profileController.postTransferFromBank(transferDTO);

		// THEN
		assertEquals("redirect:/profile?error", result);
	}

	@Test
	public void postTransferToBank_ShouldRedirectOnProfilePageSuccess()
			throws AlreadyExistException, RessourceNotFoundException, TransactionFailedException {
		// GIVEN
		TransferBankDTO transferDTO = new TransferBankDTO("", 0);
		User user = new User("user@gmail.com", "user");
		when(userService.getCurrentUser()).thenReturn(user);

		// WHEN
		String result = profileController.postTransferToBank(transferDTO);

		// THEN
		assertEquals("redirect:/profile?success", result);
	}

	@Test
	public void postTransferToBank_ShouldRedirectOnProfilePageError() throws AlreadyExistException,
			RessourceNotFoundException, ImpossibleTransferException, TransactionFailedException {
		// GIVEN
		TransferBankDTO transferDTO = new TransferBankDTO("", 0);
		User user = new User("user@gmail.com", "user");
		when(userService.getCurrentUser()).thenReturn(user);
		doThrow(new ImpossibleTransferException("")).when(transactionBankservice).transferOnBank(transferDTO, user);

		// WHEN
		String result = profileController.postTransferToBank(transferDTO);

		// THEN
		assertEquals("redirect:/profile?error", result);
	}

}
