package com.payMyBuddy.app.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.payMyBuddy.app.DTO.TransferBankDTO;
import com.payMyBuddy.app.exception.AlreadyExistException;
import com.payMyBuddy.app.exception.ImpossibleTransferException;
import com.payMyBuddy.app.exception.RessourceNotFoundException;
import com.payMyBuddy.app.model.User;
import com.payMyBuddy.app.service.TransactionBankService;
import com.payMyBuddy.app.service.UserService;

@Controller
public class ProfileController {

	private static final Logger LOGGER = LogManager.getLogger(WebController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private TransactionBankService transactionBankservice;

	/**
	 * 
	 */
	@GetMapping("/profile")
	public String getProfilePage(Model model) {
		LOGGER.info("GET - profile page");
		User user = userService.getCurrentUser();
		model.addAttribute("user", user);
		model.addAttribute("transfers", user.getTransactionsBank());

		return "profile";
	}

	/**
	 * @throws RessourceNotFoundException 
	 * @throws AlreadyExistException 
	 * 
	 */
	@PostMapping("/profileAdd")
	public String postTransferFromBank(@ModelAttribute("transferBank") TransferBankDTO transferBank) throws AlreadyExistException, RessourceNotFoundException {
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
	 * 
	 */
	@PostMapping("/profileMinus")
	public String postTransferToBank(@ModelAttribute("transferBank") TransferBankDTO transferBank) throws RessourceNotFoundException, AlreadyExistException {
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
