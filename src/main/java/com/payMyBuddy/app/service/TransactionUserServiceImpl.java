package com.payMyBuddy.app.service;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payMyBuddy.app.DTO.TransferUserDTO;
import com.payMyBuddy.app.exception.ImpossibleTransferException;
import com.payMyBuddy.app.exception.RessourceNotFoundException;
import com.payMyBuddy.app.exception.TransactionFailedException;
import com.payMyBuddy.app.model.TransactionUser;
import com.payMyBuddy.app.model.User;
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
	@Autowired
	private UserService userService;

	/*
	 * 
	 */
	@Override
	public TransactionUser addTransactionUser(TransactionUser transactionUser) {
		String info = "Saving transaction between Users:" + transactionUser.getPayer().getEmail() + " and "
				+ transactionUser.getReceiver().getEmail();
		LOGGER.info(info);
		return transactionUserRepository.save(transactionUser);
	}

	/*
	 * 
	 */
	@Override
	@Transactional(rollbackOn = TransactionFailedException.class)
	public void newTransferWithUser(TransferUserDTO transferUserDTO, User user)
			throws RessourceNotFoundException, ImpossibleTransferException, TransactionFailedException {

		User userReceiver = userService.findByEmail(transferUserDTO.getReceiver());

		// verify the transfer is valid
		if (userReceiver != null && transferUserDTO.getAmount() > 0
				&& (user.getBalance() - (transferUserDTO.getAmount() * 1.005)) >= 0) {

			// create the new transfer and add it in database
			TransactionUser transferUser = new TransactionUser(user, userReceiver, transferUserDTO.getDescription(),
					transferUserDTO.getAmount(), calculateTax(transferUserDTO.getAmount()));
			addTransactionUser(transferUser);

			// operations on the balance of users
			User applicationAccount = userService.findByEmail("applicationBalance");
			user.setBalance(user.getBalance() - (transferUser.getAmount() + transferUser.getTax()));
			userReceiver.setBalance(userReceiver.getBalance() + transferUser.getAmount());
			applicationAccount.setBalance(applicationAccount.getBalance() + transferUser.getTax());
			
			// update the changes
			try {
				userService.updateUser(user);
				userService.updateUser(userReceiver);
				userService.updateUser(applicationAccount);
			} catch (Exception e) {
				throw new TransactionFailedException("Transaction failed can not join database.");
			}
			

		} else {
			String error = "Transaction between users impossible";
			LOGGER.error(error);
			throw new ImpossibleTransferException(error);
		}
	}
	
	/*
	 * 
	 */
	@Override
	public double calculateTax(double amount) {
		Double tax = ((double)((int)(amount*0.005*100)))/100;
		if(tax > 0) {
			return tax;
		} else {
			return 0.01;
		}
	}

}
