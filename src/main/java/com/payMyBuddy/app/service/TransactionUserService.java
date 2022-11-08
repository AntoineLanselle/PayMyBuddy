package com.payMyBuddy.app.service;

import java.util.List;
import java.util.Optional;

import com.payMyBuddy.app.exception.AlreadyExistException;
import com.payMyBuddy.app.exception.RessourceNotFoundException;
import com.payMyBuddy.app.model.TransactionUser;

/**
 * 
 * @author Antoine
 */
public interface TransactionUserService {

	public List<TransactionUser> getTransactionUsers();

	public Optional<TransactionUser> getTransactionUserById(Integer id);

	public TransactionUser addTransactionUser(TransactionUser transactionUser) throws AlreadyExistException;

	public TransactionUser updateTransactionUser(TransactionUser transactionUser) throws RessourceNotFoundException;

	public void deleteTransactionUser(TransactionUser transactionUser) throws RessourceNotFoundException;

	public void deleteTransactionUser(Integer id) throws RessourceNotFoundException;

}
