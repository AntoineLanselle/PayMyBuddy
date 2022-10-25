package com.payMyBuddy.app.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payMyBuddy.app.model.TransactionBank;

/**
 * 
 * 
 * @author Antoine
 */
@Repository
public interface TransactionBankRepository extends JpaRepository<TransactionBank, Integer> {
	
}
