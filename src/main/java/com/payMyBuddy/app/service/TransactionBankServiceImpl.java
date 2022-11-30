package com.payMyBuddy.app.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payMyBuddy.app.DTO.TransferBankDTO;
import com.payMyBuddy.app.exception.AlreadyExistException;
import com.payMyBuddy.app.exception.ImpossibleTransferException;
import com.payMyBuddy.app.exception.RessourceNotFoundException;
import com.payMyBuddy.app.model.TransactionBank;
import com.payMyBuddy.app.model.User;
import com.payMyBuddy.app.repository.TransactionBankRepository;

/**
 * 
 * 
 * @author Antoine
 */
@Service
public class TransactionBankServiceImpl implements TransactionBankService {

	private static final Logger LOGGER = LogManager.getLogger(TransactionBankServiceImpl.class);

	@Autowired
	private TransactionBankRepository transactionBankRepository;
	@Autowired
	private UserService userService;

	/*
	 * 
	 */
	@Override
	public List<TransactionBank> getTransactionBanks() {
		LOGGER.info("Getting all transactions with banks");
		return transactionBankRepository.findAll();
	}

	/*
	 * 
	 */
	@Override
	public Optional<TransactionBank> getTransactionBankById(Integer id) {
		LOGGER.info("Getting transaction with bank Id: " + id);
		return transactionBankRepository.findById(id);
	}

	/*
	 * 
	 */
	@Override
	public TransactionBank addTransactionBank(TransactionBank transactionBank) throws AlreadyExistException {
		LOGGER.info("Saving transaction between User and bank: " + transactionBank.getUser().getEmail() + " and "
				+ transactionBank.getBankaccount());
		return transactionBankRepository.save(transactionBank);
	}

	/*
	 * 
	 */
	@Override
	public TransactionBank updateTransactionBank(TransactionBank transactionBank) throws RessourceNotFoundException {
		if (getTransactionBankById(transactionBank.getId()) == null) {
			String error = "Transaction bank: " + transactionBank.getId() + " already exist";
			LOGGER.error(error);
			throw new RessourceNotFoundException(error);
		}
		LOGGER.info("Updating transaction between User and bank Id: " + transactionBank.getId());
		return transactionBankRepository.save(transactionBank);
	}

	/*
	 * 
	 */
	@Override
	public void deleteTransactionBank(TransactionBank transactionBank) throws RessourceNotFoundException {
		if (getTransactionBankById(transactionBank.getId()) == null) {
			String error = "Transaction bank: " + transactionBank.getId() + " already exist";
			LOGGER.error(error);
			throw new RessourceNotFoundException(error);
		}
		LOGGER.info("Deleting transaction between User and bank Id: " + transactionBank.getId());
		transactionBankRepository.delete(transactionBank);
	}

	/*
	 * 
	 */
	@Override
	public void deleteTransactionBank(Integer id) throws RessourceNotFoundException {
		if (getTransactionBankById(id) == null) {
			String error = "Transaction bank: " + id + " already exist";
			LOGGER.error(error);
			throw new RessourceNotFoundException(error);
		}
		LOGGER.info("Deleting transaction between User and bank Id: " + id);
		transactionBankRepository.deleteById(id);
	}

	@Override
	public void transferOnBalance(TransferBankDTO transferBank, User user) throws AlreadyExistException, ImpossibleTransferException, RessourceNotFoundException {

		TransactionBank transfer = new TransactionBank();

		if (transferBank.getAmount() != 0 && transferBank.getBankAccount().length() > 0) {

			transfer.setAmount(transferBank.getAmount());
			transfer.setBankaccount(transferBank.getBankAccount());
			transfer.setUser(user);

			addTransactionBank(transfer);
			user.setBalance(user.getBalance() + transferBank.getAmount());
			user.getTransactionsBank().add(transfer);
			userService.updateUser(user);
			
		} else {
			String error = "Transfer on balance impossible";
			LOGGER.error(error);
			throw new ImpossibleTransferException(error);
		}

	}

	@Override
	public void transferOnBank(TransferBankDTO transferBank, User user) throws RessourceNotFoundException, AlreadyExistException, ImpossibleTransferException {
		
		TransactionBank transfer = new TransactionBank();
		
		if ( transferBank.getAmount() != 0 
				&& transferBank.getBankAccount().length() > 0
				&& (user.getBalance() - transferBank.getAmount() > 0) ) { 
																			
					transfer.setAmount(transferBank.getAmount());
					transfer.setBankaccount(transferBank.getBankAccount());
					transfer.setUser(user);
					addTransactionBank(transfer);
					
					user.setBalance(user.getBalance() - transferBank.getAmount());
					user.getTransactionsBank().add(transfer);
					userService.updateUser(user);
				
		} else {
			String error = "Transfer to Bank impossible";
			LOGGER.error(error);
			throw new ImpossibleTransferException(error);
		}

	}

}
