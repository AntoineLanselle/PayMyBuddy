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
import com.payMyBuddy.app.exception.RessourceNotFoundException;
import com.payMyBuddy.app.model.User;
import com.payMyBuddy.app.service.UserService;

/**
 * 
 * @author Antoine
 */
@Controller
//@RequestMapping("/contact")
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
        User newConnection = userService.findByEmail(email);
    	user.getConnections().add(newConnection);
    	userService.updateUser(user);

    	return "redirect:/contact";
	}
	
	/**
	 * @throws RessourceNotFoundException 
	 * 
	 */
	@PostMapping("/deleteConnection")
    public String deleteConnection(@RequestParam int id) throws RessourceNotFoundException { 
    	User user = userService.getCurrentUser();
    	LOGGER.info("Delete connection of " + user.getId() + " with " + id);
    	user.getConnections().removeIf(connectionUser -> (connectionUser.getId() == id ));
    	userService.updateUser(user);
        
        return "redirect:/contact";
    }
	
}
