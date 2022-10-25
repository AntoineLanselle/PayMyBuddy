package com.payMyBuddy.app.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payMyBuddy.app.model.TransactionBank;
import com.payMyBuddy.app.repository.TransactionBankRepository;

/**
 * 
 * 
 * @author Antoine
 */
@Service
public class TransactionBankService {
	
	private static final Logger LOGGER = LogManager.getLogger(TransactionBankService.class);

	@Autowired
	private TransactionBankRepository transactionAccountRepository;

	public List<TransactionBank> getTransactionAccounts() {
		LOGGER.info("Getting all transactions with banks");
		return transactionAccountRepository.findAll();
	}

	public Optional<TransactionBank> getTransactionAccountById(Integer id) {
		LOGGER.info("Getting transaction with bank with Id: " + id);
		return transactionAccountRepository.findById(id);
	}

}
