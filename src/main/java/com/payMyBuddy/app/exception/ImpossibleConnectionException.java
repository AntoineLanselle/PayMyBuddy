package com.payMyBuddy.app.exception;

/**
 * 
 * 
 * @author Antoine
 */
public class ImpossibleConnectionException extends Exception {

	private static final long serialVersionUID = 4L;

	/**
	 * Construit une ImpossibleConnectionException.
	 * 
	 * @param le message de l'exception.
	 * 
	 */
	public ImpossibleConnectionException(String message) {
		super(message);
	}
	
}
