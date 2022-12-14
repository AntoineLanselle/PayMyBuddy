package com.payMyBuddy.app.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

@Controller
@RequestMapping("/transfer")
public class TransferController {

	private static final Logger LOGGER = LogManager.getLogger(TransferController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private TransactionUserService transactionUserService;
	@Autowired
	private PaginationService paginationService;

	/**
	 * 
	 */
	@GetMapping
	@SuppressWarnings("unchecked")
	public String getTransferPage(Model model, @RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size) {

		int currentPage = page == null ? 0 : page;
		int pageSize = size == null ? 3 : size;

		LOGGER.info("GET - transfer page " + currentPage);

		User user = userService.getCurrentUser();
		List<TransactionUser> allTransfer = user.getTransactionsPayer();
		allTransfer.addAll(user.getTransactionsReceiver());
		List<TransactionUser> pagedTransfer = (List<TransactionUser>) paginationService.getPagination(currentPage,
				pageSize, allTransfer);
		int totalPageNumber = paginationService.getPageNumber(pageSize, allTransfer);

		model.addAttribute("user", user);
		model.addAttribute("transfers", pagedTransfer);
		model.addAttribute("pageNumber", currentPage);
		model.addAttribute("totalPageNumber", totalPageNumber);

		return "transfer";
	}

	/**
	 * @throws AlreadyExistException
	 * @throws RessourceNotFoundException
	 * @throws ImpossibleTransferException
	 * @throws TransactionFailedException
	 * 
	 */
	@PostMapping
	public String postTransferWithUser(@ModelAttribute("transferUser") TransferUserDTO transferUser)
			throws RessourceNotFoundException, ImpossibleTransferException, AlreadyExistException,
			TransactionFailedException {

		LOGGER.info("POST - make a new transaction between users");
		User user = userService.getCurrentUser();
		try {
			transactionUserService.newTransferWithUser(transferUser, user);
		} catch (ImpossibleTransferException e) {
			return "redirect:/transfer?error";
		}
		return "redirect:/transfer?success";
	}

}
