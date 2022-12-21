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
import org.springframework.web.bind.annotation.RequestParam;

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

@Controller
public class ProfileController {

	private static final Logger LOGGER = LogManager.getLogger(WebController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private TransactionBankService transactionBankservice;
	@Autowired
	private PaginationService paginationService;

	/**
	 * 
	 */
	@GetMapping("/profile")
	@SuppressWarnings("unchecked")
	public String getProfilePage(Model model, @RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size) {

		int currentPage = page == null ? 0 : page;
		int pageSize = size == null ? 3 : size;

		LOGGER.info("GET - profile page " + currentPage);

		User user = userService.getCurrentUser();
		List<TransactionBank> allTransfer = user.getTransactionsBank();
		List<TransactionBank> pagedTransfer = (List<TransactionBank>) paginationService.getPagination(currentPage,
				pageSize, allTransfer);
		int totalPageNumber = paginationService.getPageNumber(pageSize, allTransfer);

		model.addAttribute("user", user);
		model.addAttribute("transfers", pagedTransfer);
		model.addAttribute("pageNumber", currentPage);
		model.addAttribute("totalPageNumber", totalPageNumber);

		return "profile";
	}

	/**
	 * @throws RessourceNotFoundException
	 * @throws AlreadyExistException
	 * @throws TransactionFailedException
	 * 
	 */
	@PostMapping("/profileAdd")
	public String postTransferFromBank(@ModelAttribute("transferBank") TransferBankDTO transferBank)
			throws AlreadyExistException, RessourceNotFoundException, TransactionFailedException {
		User user = userService.getCurrentUser();
		LOGGER.info("POST - new transfer of " + user.getEmail() + " with bank " + transferBank.getBankAccount()
				+ " on balance");
		try {
			transactionBankservice.transferOnBalance(transferBank, user);
			return "redirect:/profile?success";
		} catch (ImpossibleTransferException e) {
			return "redirect:/profile?error";
		}
	}

	/**
	 * @throws AlreadyExistException
	 * @throws RessourceNotFoundException
	 * @throws TransactionFailedException
	 * 
	 */
	@PostMapping("/profileMinus")
	public String postTransferToBank(@ModelAttribute("transferBank") TransferBankDTO transferBank)
			throws RessourceNotFoundException, AlreadyExistException, TransactionFailedException {
		User user = userService.getCurrentUser();
		LOGGER.info("POST - new transfer of " + user.getEmail() + " to bank " + transferBank.getBankAccount());
		try {
			transactionBankservice.transferOnBank(transferBank, user);
			return "redirect:/profile?success";
		} catch (ImpossibleTransferException e) {
			return "redirect:/profile?error";
		}
	}

}
