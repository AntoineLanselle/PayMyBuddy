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
import com.payMyBuddy.app.model.TransactionUser;
import com.payMyBuddy.app.model.User;
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

	/**
	 * 
	 */
	@GetMapping
	public String getTransferPage(Model model, @RequestParam(required=false) Integer page, @RequestParam(required=false) Integer size) {

		LOGGER.info("GET - transfer page");

        int currentPage = page == null ? 0 : page;
        int pageSize = size == null ? 3 : size;
		 
		User user = userService.getCurrentUser();
		List<TransactionUser> transfers = transactionUserService.getPagination(currentPage, pageSize, user);
		int pageNumber = transactionUserService.getPageNumber(pageSize, user);

		model.addAttribute("user", user);
		model.addAttribute("transfers", transfers);
		model.addAttribute("pageNumber", pageNumber);

		return "transfer";
	}

	/**
	 * @throws AlreadyExistException
	 * @throws RessourceNotFoundException
	 * @throws ImpossibleTransferException
	 * 
	 */
	@PostMapping
	public String postTransferWithUser(@ModelAttribute("transferUser") TransferUserDTO transferUser)
			throws RessourceNotFoundException, ImpossibleTransferException, AlreadyExistException {

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
