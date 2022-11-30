package com.payMyBuddy.app.exception;

/**
 * 
 * 
 * @author Antoine
 */
public class RessourceNotFoundException extends Exception {

	private static final long serialVersionUID = 3L;

	/**
	 * Construit une RessourceNotFoundException.
	 * 
	 * @param le message de l'exception.
	 * 
	 */
	public RessourceNotFoundException(String message) {
		super(message);
	}

}