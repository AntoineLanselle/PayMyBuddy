package com.payMyBuddy.app.service;

import java.util.List;

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

	public TransactionUser addTransactionUser(TransactionUser transactionUser);

	public void newTransferWithUser(TransferUserDTO transferUserDTO, User user)
			throws AlreadyExistException, RessourceNotFoundException, ImpossibleTransferException;

	double calculateTax(double amount);

	public List<TransactionUser> getPagination(int currentPage, int pageSize, User user);

	public int getPageNumber(int pageSize, User user);

}
