package com.payMyBuddy.app.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payMyBuddy.app.exception.AlreadyExistException;
import com.payMyBuddy.app.exception.RessourceNotFoundException;
import com.payMyBuddy.app.model.TransactionUser;
import com.payMyBuddy.app.repository.TransactionUserRepository;

/**
 * 
 * 
 * @author Antoine
 */
@Service
public class TransactionUserServiceImpl implements TransactionUserService {

	private static final Logger LOGGER = LogManager.getLogger(TransactionUserServiceImpl.class);

	@Autowired
	private TransactionUserRepository transactionUserRepository;

	/*
	 * 
	 */
	@Override
	public List<TransactionUser> getTransactionUsers() {
		LOGGER.info("Getting all transactions between Users");
		return transactionUserRepository.findAll();
	}

	/*
	 * 
	 */
	@Override
	public Optional<TransactionUser> getTransactionUserById(Integer id) {
		LOGGER.info("Getting transaction between Users Id: " + id);
		return transactionUserRepository.findById(id);
	}

	/*
	 * 
	 */
	@Override
	public TransactionUser addTransactionUser(TransactionUser transactionUser) throws AlreadyExistException {
		if (getTransactionUserById(transactionUser.getId()) != null) {
			String error = "Transaction: " + transactionUser.getId() + " already exist";
			LOGGER.error(error);
			throw new AlreadyExistException(error);
		}
		LOGGER.info("Saving transaction between Users:" + transactionUser.getPayer().getEmail() + " and "
				+ transactionUser.getReceiver().getEmail());
		return transactionUserRepository.save(transactionUser);
	}

	/*
	 * 
	 */
	@Override
	public TransactionUser updateTransactionUser(TransactionUser transactionUser) throws RessourceNotFoundException {
		if (getTransactionUserById(transactionUser.getId()) == null) {
			String error = "Transaction between User: " + transactionUser.getId() + " already exist";
			LOGGER.error(error);
			throw new RessourceNotFoundException(error);
		}
		LOGGER.info("Updating transaction between Users Id:" + transactionUser.getId());
		return transactionUserRepository.save(transactionUser);
	}

	/*
	 * 
	 */
	@Override
	public void deleteTransactionUser(TransactionUser transactionUser) throws RessourceNotFoundException {
		if (getTransactionUserById(transactionUser.getId()) == null) {
			String error = "Transaction between User: " + transactionUser.getId() + " already exist";
			LOGGER.error(error);
			throw new RessourceNotFoundException(error);
		}
		LOGGER.info("Deleting transaction between Users Id:" + transactionUser.getId());
		transactionUserRepository.delete(transactionUser);
	}

	/*
	 * 
	 */
	@Override
	public void deleteTransactionUser(Integer id) throws RessourceNotFoundException {
		if (getTransactionUserById(id) == null) {
			String error = "Transaction between User: " + id + " already exist";
			LOGGER.error(error);
			throw new RessourceNotFoundException(error);
		}
		LOGGER.info("Deleting transaction between Users Id:" + id);
		transactionUserRepository.deleteById(id);
	}

}
