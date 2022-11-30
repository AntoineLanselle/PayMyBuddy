package com.payMyBuddy.app.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.payMyBuddy.app.model.User;
import com.payMyBuddy.app.service.UserService;

/**
 * 
 * @author Antoine
 */
@Controller
public class WebController {

	private static final Logger LOGGER = LogManager.getLogger(WebController.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * 
	 */
	@GetMapping("/login")
	public String getLoginPage() {
		LOGGER.info("GET - login page");
		return "login";
	}
	
	/**
	 * 
	 */
	@GetMapping("/index")
	public String getHomePage(Model model) {
		User user = userService.getCurrentUser();
		model.addAttribute("email", user.getEmail());
		LOGGER.info("GET - " + user.getEmail() + " home page");
		return "index";
	}
	
}
