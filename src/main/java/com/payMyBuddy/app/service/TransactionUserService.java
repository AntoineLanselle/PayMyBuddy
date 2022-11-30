package com.payMyBuddy.app.service;

import java.util.List;
import java.util.Optional;

import com.payMyBuddy.app.DTO.TransferUserDTO;
import com.payMyBuddy.app.exception.AlreadyExistException;
import com.payMyBuddy.app.exception.ImpossibleTransferException;
import com.payMyBuddy.app.exception.RessourceNotFoundException;
import com.payMyBuddy.app.model.TransactionUser;
import com.payMyBuddy.app.model.User;

/**
 * 
 * @author Antoine
 */
public interface TransactionUserService {

	public List<TransactionUser> getTransactionUsers();

	public Optional<TransactionUser> getTransactionUserById(Integer id);

	public TransactionUser addTransactionUser(TransactionUser transactionUser);

	public TransactionUser updateTransactionUser(TransactionUser transactionUser) throws RessourceNotFoundException;

	public void deleteTransactionUser(TransactionUser transactionUser) throws RessourceNotFoundException;

	public void deleteTransactionUser(Integer id) throws RessourceNotFoundException;

	public void newTransferWithUser(TransferUserDTO transferUserDTO, User user) throws AlreadyExistException, RessourceNotFoundException, ImpossibleTransferException;

}
