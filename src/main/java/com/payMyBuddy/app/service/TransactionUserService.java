package com.payMyBuddy.app.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payMyBuddy.app.model.TransactionUser;
import com.payMyBuddy.app.repository.TransactionUserRepository;

/**
 * 
 * 
 * @author Antoine
 */
@Service
public class TransactionUserService {
	
	private static final Logger LOGGER = LogManager.getLogger(TransactionUserService.class);

	@Autowired
	private TransactionUserRepository transactionUserRepository;

	public List<TransactionUser> getTransactionUsers() {
		LOGGER.info("Getting all transactions between Users");
		return transactionUserRepository.findAll();
	}

	public Optional<TransactionUser> getTransactionUserById(Integer id) {
		LOGGER.info("Getting transaction between Users with Id: " + id);
		return transactionUserRepository.findById(id);
	}

}
