package com.payMyBuddy.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.payMyBuddy.app.exception.AlreadyExistException;
import com.payMyBuddy.app.exception.RessourceNotFoundException;
import com.payMyBuddy.app.exception.TransactionFailedException;
import com.payMyBuddy.app.exception.ImpossibleTransferException;

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
	public ResponseEntity<String> handletAlreadyExistException(AlreadyExistException ex) {
		String error = ex.getMessage();
		// Logger
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}
	
	/**
	 * Gêre l'erreur ImpossibleTransferException.
	 * 
	 * @param Une erreur de type TransferImpossibleException.
	 * 
	 * @return response entity CONFLICT avec comme body une erreur.
	 * 
	 */
	@ExceptionHandler(ImpossibleTransferException.class)
	public ResponseEntity<String> handletImpossibleTransferException(ImpossibleTransferException ex) {
		String error = ex.getMessage();
		// Logger
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}
	
	/**
	 * Gêre l'erreur TransactionFailedException.
	 * 
	 * @param Une erreur de type TransferImpossibleException.
	 * 
	 * @return response entity CONFLICT avec comme body une erreur.
	 * 
	 */
	@ExceptionHandler(TransactionFailedException.class)
	public ResponseEntity<String> handletTransactionFailedException(TransactionFailedException ex) {
		String error = ex.getMessage();
		// Logger
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}

}