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

import com.payMyBuddy.app.DTO.TransferUserDTO;
import com.payMyBuddy.app.exception.AlreadyExistException;
import com.payMyBuddy.app.exception.ImpossibleTransferException;
import com.payMyBuddy.app.exception.RessourceNotFoundException;
import com.payMyBuddy.app.exception.TransactionFailedException;
import com.payMyBuddy.app.model.TransactionUser;
import com.payMyBuddy.app.model.User;
import com.payMyBuddy.app.service.PaginationService;
import com.payMyBuddy.app.service.TransactionUserService;
import com.payMyBuddy.app.service.UserService;

@ExtendWith(MockitoExtension.class)
public class TransferControllerTest {

	@Mock
	private TransactionUserService transactionUserService;
	@Mock
	private UserService userService;
	@Mock
	private PaginationService paginationService;
	@InjectMocks
	private TransferController transferController;

	@Test
	public void getHomePage_ShouldReturnHomePageWithNullPagination() {
		// GIVEN
		Model m = new ExtendedModelMap();
		User user = new User("user@gmail.com", "user");
		List<TransactionUser> listTransactions = new ArrayList<TransactionUser>();
		user.setTransactionsPayer(listTransactions);
		user.setTransactionsReceiver(listTransactions);
		when(userService.getCurrentUser()).thenReturn(user);
		when(paginationService.getPageNumber(3, listTransactions)).thenReturn(0);

		// WHEN
		String result = transferController.getTransferPage(m, null, null);

		// THEN
		assertEquals("transfer", result);
	}

	@Test
	public void postTransferWithUser_ShouldRedirectOnTranferPageSuccess() throws RessourceNotFoundException,
			ImpossibleTransferException, AlreadyExistException, TransactionFailedException {
		// GIVEN
		User user = new User("user@gmail.com", "user");
		TransferUserDTO transferDTO = new TransferUserDTO("user@gmail.com", "", 1);
		when(userService.getCurrentUser()).thenReturn(user);

		// WHEN
		String result = transferController.postTransferWithUser(transferDTO);

		// THEN
		assertEquals("redirect:/transfer?success", result);
	}

	@Test
	public void postTransferWithUser_ShouldRedirectOnTranferPageError() throws AlreadyExistException,
			RessourceNotFoundException, ImpossibleTransferException, TransactionFailedException {
		// GIVEN
		User user = new User("user@gmail.com", "user");
		TransferUserDTO transferDTO = new TransferUserDTO("user@gmail.com", "", 1);
		when(userService.getCurrentUser()).thenReturn(user);
		doThrow(new ImpossibleTransferException("")).when(transactionUserService).newTransferWithUser(transferDTO,
				user);

		// WHEN
		String result = transferController.postTransferWithUser(transferDTO);

		// THEN
		assertEquals("redirect:/transfer?error", result);
	}

}
