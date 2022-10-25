package com.payMyBuddy.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payMyBuddy.app.model.TransactionUser;

/**
 * 
 * 
 * @author Antoine
 */
@Repository
public interface TransactionUserRepository extends JpaRepository<TransactionUser, Integer> {

}
