package com.payMyBuddy.app.exception;

/**
 * 
 * 
 * @author Antoine
 */
public class AlreadyExistException extends Exception {

	private static final long serialVersionUID = 2L;

	/**
	 * Construit une AlreadyExistException.
	 * 
	 * @param le message de l'exception.
	 * 
	 */
	public AlreadyExistException(String message) {
		super(message);
	}

}
