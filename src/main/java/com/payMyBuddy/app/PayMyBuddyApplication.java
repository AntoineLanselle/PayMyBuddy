package com.payMyBuddy.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.payMyBuddy.app.model.TransactionBank;
import com.payMyBuddy.app.model.TransactionUser;
import com.payMyBuddy.app.model.User;
import com.payMyBuddy.app.service.TransactionBankServiceImpl;
import com.payMyBuddy.app.service.TransactionUserServiceImpl;
import com.payMyBuddy.app.service.UserServiceImpl;

/**
 * 
 * 
 * @author Antoine
 */
@SpringBootApplication
public class PayMyBuddyApplication implements CommandLineRunner {

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private TransactionBankServiceImpl transactionBankService;

	@Autowired
	private TransactionUserServiceImpl transactionUserService;

	public static void main(String[] args) {
		SpringApplication.run(PayMyBuddyApplication.class, args);
	}

	/*
	 * Test pour vérifier le bon fonctionnement entre mon code et la base de données
	 * 
	 * à supprimer plus tard
	 */
	@Override
	public void run(String... args) throws Exception {
		
		Iterable<User> users = userService.getUsers();
		for (User user : users) {
			System.out.println(
					"Id: " + user.getId() + " | Email: " + user.getEmail() + " | Balance: " + user.getBalance());
			for (User connection : user.getConnections()) {
				System.out.println("Connection: " + connection.getEmail());
			}
		}
		
		Iterable<TransactionBank> transactionBank = transactionBankService.getTransactionBanks();
		for (TransactionBank transaction : transactionBank) {
			System.out.println("Id: " + transaction.getId() + " | email: " + transaction.getUser().getEmail()
					+ " | montant: " + transaction.getAmount());
		}
		
		Iterable<TransactionUser> transactionUsers = transactionUserService.getTransactionUsers();
		for (TransactionUser transaction : transactionUsers) {
			System.out.println("Id: " + transaction.getId() + " | payer: " + transaction.getPayer().getEmail()
					+ " | receiver: " + transaction.getReceiver().getEmail() + " | amount: " + transaction.getAmount());
		}
	}

}
