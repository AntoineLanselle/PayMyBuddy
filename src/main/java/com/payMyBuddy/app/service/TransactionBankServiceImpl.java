package com.payMyBuddy.app.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payMyBuddy.app.DTO.TransferBankDTO;
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
	public TransactionBank addTransactionBank(TransactionBank transactionBank) {
		String info = "Saving transaction between User and bank: " + transactionBank.getUser().getEmail() + " and "
				+ transactionBank.getBankaccount();
		LOGGER.info(info);
		return transactionBankRepository.save(transactionBank);
	}

	/*
	 * 
	 */
	@Override
	public User transferOnBalance(TransferBankDTO transferBank, User user)
			throws ImpossibleTransferException, RessourceNotFoundException {

		if (transferBank.getAmount() != 0 && transferBank.getBankAccount().length() > 0) {

			TransactionBank transfer = new TransactionBank(user, transferBank.getBankAccount(), transferBank.getAmount());
			addTransactionBank(transfer);

			user.setBalance(user.getBalance() + transferBank.getAmount());
			return userService.updateUser(user);

		} else {
			String error = "Transfer on balance impossible";
			LOGGER.error(error);
			throw new ImpossibleTransferException(error);
		}
	}

	/*
	 * 
	 */
	@Override
	public User transferOnBank(TransferBankDTO transferBank, User user)
			throws RessourceNotFoundException, ImpossibleTransferException {

		if (transferBank.getAmount() > 0 && transferBank.getBankAccount().length() > 0
				&& (user.getBalance() - transferBank.getAmount() >= 0)) {

			TransactionBank transfer = new TransactionBank(user, transferBank.getBankAccount(), -transferBank.getAmount());
			addTransactionBank(transfer);

			user.setBalance(user.getBalance() - transferBank.getAmount());
			return userService.updateUser(user);

		} else {
			String error = "Transfer to Bank impossible";
			LOGGER.error(error);
			throw new ImpossibleTransferException(error);
		}
	}
}