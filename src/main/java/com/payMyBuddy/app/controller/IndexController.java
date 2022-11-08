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
@RequestMapping("/index")
public class IndexController {

	private static final Logger LOGGER = LogManager.getLogger(IndexController.class);
	
	/**
	 * 
	 */
	@GetMapping
	public String getRegistrationPage() {
		LOGGER.info("GET - home page");
		return "index";
	}
	
}
