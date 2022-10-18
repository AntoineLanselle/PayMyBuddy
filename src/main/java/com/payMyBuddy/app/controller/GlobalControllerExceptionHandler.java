package com.payMyBuddy.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.payMyBuddy.app.exception.AlreadyExistException;
import com.payMyBuddy.app.exception.RessourceNotFoundException;

/**
 * 
 * 
 * @author Antoine
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {

	/**
	 * Gêre l'erreur RessourceNotFound.
	 * 
	 * @param Une erreur de type RessourceNotFoundException.
	 * 
	 * @return response entity NOT_FOUND avec comme body une erreur.
	 * 
	 */
	@ExceptionHandler(RessourceNotFoundException.class)
	public ResponseEntity<String> handletNotFoundException(RessourceNotFoundException ex) {
		String error = ex.getMessage();
		// Logger
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	/**
	 * Gêre l'erreur AlreadyExistException.
	 * 
	 * @param Une erreur de type AlreadyExistException.
	 * 
	 * @return response entity CONFLICT avec comme body une erreur.
	 * 
	 */
	@ExceptionHandler(AlreadyExistException.class)
	public ResponseEntity<String> handletNotFoundException(AlreadyExistException ex) {
		String error = ex.getMessage();
		// Logger
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}

}