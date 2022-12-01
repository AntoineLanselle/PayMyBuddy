package com.payMyBuddy.app.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payMyBuddy.app.DTO.TransferUserDTO;
import com.payMyBuddy.app.exception.ImpossibleTransferException;
import com.payMyBuddy.app.exception.RessourceNotFoundException;
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
	UserService userService;

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
	public void newTransferWithUser(TransferUserDTO transferUserDTO, User user)
			throws RessourceNotFoundException, ImpossibleTransferException {

		User userReceiver = userService.findByEmail(transferUserDTO.getReceiver());

		// verify the transfer is valid
		if (userReceiver != null && transferUserDTO.getAmount() > 0
				&& (user.getBalance() - transferUserDTO.getAmount()) >= 0) {

			// create the new transfer
			TransactionUser transferUser = new TransactionUser();
			transferUser.setAmount(transferUserDTO.getAmount());
			transferUser.setPayer(user);
			transferUser.setReceiver(userReceiver);
			transferUser.setDescription(transferUserDTO.getDescription());

			// add the new transfer in database
			user.getTransactionsUser().add(transferUser);
			userReceiver.getTransactionsUser().add(transferUser);
			addTransactionUser(transferUser);

			// operations on the balance of users
			user.setBalance(user.getBalance() - transferUserDTO.getAmount());
			userReceiver.setBalance(userReceiver.getBalance() + transferUserDTO.getAmount());

			// update the changes
			userService.updateUser(user);
			userService.updateUser(userReceiver);

		} else {
			String error = "Transaction between users impossible";
			LOGGER.error(error);
			throw new ImpossibleTransferException(error);
		}
	}

}
