package com.payMyBuddy.app.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.payMyBuddy.app.exception.AlreadyExistException;
import com.payMyBuddy.app.exception.ImpossibleConnectionException;
import com.payMyBuddy.app.exception.RessourceNotFoundException;
import com.payMyBuddy.app.model.User;
import com.payMyBuddy.app.service.UserService;

/**
 * 
 * @author Antoine
 */
@Controller
public class ContactController {

	private static final Logger LOGGER = LogManager.getLogger(ContactController.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * 
	 */
	@GetMapping("/contact")
	public String getContactPage(Model model) {
		LOGGER.info("GET - contact page");
		
		User user = userService.getCurrentUser();
		model.addAttribute("users", user.getConnections());

		return "contact";
	}
	
	/**
	 * @throws AlreadyExistException 
	 * @throws RessourceNotFoundException 
	 * 
	 */
	@PostMapping("/contact")
	public String addConnection(@RequestParam String email) throws AlreadyExistException, RessourceNotFoundException {
		LOGGER.info("Add connection in database - " + email);
		User user = userService.getCurrentUser();
		try {
			userService.saveConnection(user, email);
			return "redirect:/contact?sucess";
		} catch(ImpossibleConnectionException e) {
			return "redirect:/contact?error";
		}
	}
	
	/**
	 * @throws RessourceNotFoundException 
	 * 
	 */
	@PostMapping("/deleteConnection")
    public String deleteConnection(@RequestParam String email) throws RessourceNotFoundException { 
    	User user = userService.getCurrentUser();
    	LOGGER.info("Delete connection of " + user.getId() + " with " + email);
    	user.getConnections().removeIf(connectionUser -> (connectionUser.getEmail().equals(email)));
    	userService.updateUser(user);
        
        return "redirect:/contact";
    }
	
}
