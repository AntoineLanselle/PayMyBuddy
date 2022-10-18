package com.payMyBuddy.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.payMyBuddy.app.model.Transaction;

/**
 * 
 * 
 * @author Antoine
 */
@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> { // changer Integer en fonction du type de la clé

	//JpaRepository ou CrudRepository ? 
	
}
