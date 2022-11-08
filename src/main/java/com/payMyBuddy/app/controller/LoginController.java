package com.payMyBuddy.app.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author Antoine
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	private static final Logger LOGGER = LogManager.getLogger(LoginController.class);
	
	/**
	 * 
	 */
	@GetMapping
	public String getLoginPage() {
		LOGGER.info("GET - login page");
		return "login";
	}
	
}
