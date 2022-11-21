package com.payMyBuddy.app.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.payMyBuddy.app.model.User;
import com.payMyBuddy.app.service.UserService;

@Controller
@RequestMapping("/transfer")
public class TransferController {

	private static final Logger LOGGER = LogManager.getLogger(TransferController.class);

	@Autowired
	private UserService userService;

	/**
	 * 
	 */
	@GetMapping
	public String getTransferPage(Model model) {
		LOGGER.info("GET - transfer page");

		User user = userService.getCurrentUser();
		model.addAttribute("user", user);
		model.addAttribute("transfers", user.getTransactionsUser());

		return "transfer";
	}

}
