package com.payMyBuddy.app.service;

import com.payMyBuddy.app.DTO.TransferBankDTO;
import com.payMyBuddy.app.exception.AlreadyExistException;
import com.payMyBuddy.app.exception.ImpossibleTransferException;
import com.payMyBuddy.app.exception.RessourceNotFoundException;
import com.payMyBuddy.app.exception.TransactionFailedException;
import com.payMyBuddy.app.model.TransactionBank;
import com.payMyBuddy.app.model.User;

/**
 * 
 * @author Antoine
 */
public interface TransactionBankService {

	public TransactionBank addTransactionBank(TransactionBank transactionBank) throws AlreadyExistException;

	public User transferOnBalance(TransferBankDTO transferBank, User user)
			throws ImpossibleTransferException, RessourceNotFoundException, TransactionFailedException;

	public User transferOnBank(TransferBankDTO transferBank, User user)
			throws RessourceNotFoundException, ImpossibleTransferException, TransactionFailedException;

}
