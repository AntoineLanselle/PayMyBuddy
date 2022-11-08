package com.payMyBuddy.app.service;

import java.util.List;
import java.util.Optional;

import com.payMyBuddy.app.exception.AlreadyExistException;
import com.payMyBuddy.app.exception.RessourceNotFoundException;
import com.payMyBuddy.app.model.TransactionBank;

/**
 * 
 * @author Antoine
 */
public interface TransactionBankService {

	public List<TransactionBank> getTransactionBanks();
	
	public Optional<TransactionBank> getTransactionBankById(Integer id);
	
	public TransactionBank addTransactionBank(TransactionBank transactionBank) throws AlreadyExistException;
	
	public TransactionBank updateTransactionBank(TransactionBank transactionBank) throws RessourceNotFoundException;
	
	public void deleteTransactionBank(TransactionBank transactionBank) throws RessourceNotFoundException;
	
	public void deleteTransactionBank(Integer id) throws RessourceNotFoundException;
	
}
