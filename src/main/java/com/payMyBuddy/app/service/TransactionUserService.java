package com.payMyBuddy.app.service;

import com.payMyBuddy.app.DTO.TransferUserDTO;
import com.payMyBuddy.app.exception.AlreadyExistException;
import com.payMyBuddy.app.exception.ImpossibleTransferException;
import com.payMyBuddy.app.exception.RessourceNotFoundException;
import com.payMyBuddy.app.exception.TransactionFailedException;
import com.payMyBuddy.app.model.TransactionUser;
import com.payMyBuddy.app.model.User;

/**
 * 
 * @author Antoine
 */
public interface TransactionUserService {

	public TransactionUser addTransactionUser(TransactionUser transactionUser);

	public void newTransferWithUser(TransferUserDTO transferUserDTO, User user) throws AlreadyExistException,
			RessourceNotFoundException, ImpossibleTransferException, TransactionFailedException;

	double calculateTax(double amount);

}
