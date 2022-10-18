package com.payMyBuddy.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payMyBuddy.app.model.Transaction;
import com.payMyBuddy.app.repository.TransactionRepository;

/**
 * 
 * 
 * @author Antoine
 */
@Service
public class TransactionService {

	@Autowired
	public TransactionRepository transactionRepository;
	
	public Iterable<Transaction> getUsers() {
		return transactionRepository.findAll();
	}
	
	public Optional<Transaction> getTransactionById(Integer id) {
		return transactionRepository.findById(id);
	}	
	
}
