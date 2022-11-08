package com.payMyBuddy.app.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.payMyBuddy.app.DTO.UserRegistrationDTO;
import com.payMyBuddy.app.exception.AlreadyExistException;
import com.payMyBuddy.app.service.UserService;

/**
 * 
 * @author Antoine
 */
@Controller
@RequestMapping("/registration")
public class RegistrationController {

	private static final Logger LOGGER = LogManager.getLogger(RegistrationController.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * 
	 */
	@GetMapping
	public String getRegistrationPage(Model model) {
		model.addAttribute("user", new UserRegistrationDTO());
		LOGGER.info("GET - registration page");
		return "registration";
	}
	
	/**
	 * @throws AlreadyExistException 
	 * 
	 */
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDTO userRegistration) throws AlreadyExistException {
		LOGGER.info("Add user in database - " + userRegistration.getEmail());
		userService.saveUser(userRegistration);
		return "redirect:/registration?success";
	}
	
}
