package com.payMyBuddy.app.exception;

/**
 * 
 * 
 * @author Antoine
 */
public class ImpossibleTransferException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Construit une ImpossibleTransferException.
	 * 
	 * @param le message de l'exception.
	 * 
	 */
	public ImpossibleTransferException(String message) {
		super(message);
	}

	
}
