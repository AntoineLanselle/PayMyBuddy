package com.payMyBuddy.app.controller;

import javax.annotation.security.RolesAllowed;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class which manage the login page
 * 
 * @author Antoine
 */
@RestController
public class LoginController {

	private static final Logger logger = LogManager.getLogger(LoginController.class);

	/**
	 * Manage user login
	 *
	 * @return String "welcome User"
	 */
	@RolesAllowed("USER")
	@RequestMapping("/*")
	public String getUser() {
		logger.debug("Access user page");
		return "Welcome User";
	}

	/**
	 * Manage admin login
	 *
	 * @return String "welcome Admin"
	 */
	@RolesAllowed({ "USER", "ADMIN" })
	@RequestMapping("/admin")
	public String getAdmin() {
		logger.debug("Access admin page");
		return "Welcome Admin";
	}

}