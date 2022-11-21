package com.payMyBuddy.app.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 
 * @author Antoine
 */
@Controller
public class WebController {

	private static final Logger LOGGER = LogManager.getLogger(WebController.class);
	
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
	public String getHomePage() {
		LOGGER.info("GET - home page");
		return "index";
	}
	
	/**
	 * 
	 */
	@GetMapping("/profile")
	public String getProfilePage() {
		LOGGER.info("GET - profile page");
		return "profile";
	}
	
}
