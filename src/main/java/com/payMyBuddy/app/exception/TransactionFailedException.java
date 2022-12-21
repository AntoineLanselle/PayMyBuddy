package com.payMyBuddy.app.exception;

public class TransactionFailedException extends Exception {

	private static final long serialVersionUID = 2L;

	/**
	 * Construit une TransactionFailedException.
	 * 
	 * @param le message de l'exception.
	 * 
	 */
	public TransactionFailedException(String message) {
		super(message);
	}
	
}