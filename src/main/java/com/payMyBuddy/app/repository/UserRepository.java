package com.payMyBuddy.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payMyBuddy.app.model.User;

/**
 * 
 * 
 * @author Antoine
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByEmail(String email);

}
