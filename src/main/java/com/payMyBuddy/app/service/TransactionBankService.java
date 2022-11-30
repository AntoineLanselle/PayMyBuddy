package com.payMyBuddy.app.service;

import java.util.List;
import java.util.Optional;

import com.payMyBuddy.app.DTO.TransferBankDTO;
import com.payMyBuddy.app.exception.AlreadyExistException;
import com.payMyBuddy.app.exception.ImpossibleTransferException;
import com.payMyBuddy.app.exception.RessourceNotFoundException;
import com.payMyBuddy.app.model.TransactionBank;
import com.payMyBuddy.app.model.User;

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

	public void transferOnBalance(TransferBankDTO transferBank, User user) throws AlreadyExistException, ImpossibleTransferException, RessourceNotFoundException;

	public void transferOnBank(TransferBankDTO transferBank, User user) throws RessourceNotFoundException, AlreadyExistException, ImpossibleTransferException;
	
}
