package com.payMyBuddy.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.payMyBuddy.app.model.User;

/**
 * 
 * 
 * @author Antoine
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	public User findByEmail(String email);
	
	@Query("SELECT CASE "
			+ "WHEN COUNT(u) > 0 THEN true"
			+ " ELSE false END "
			+ "FROM User u "
			+ "WHERE u.email = :email")
    public Boolean existsByEmail(@Param("email") String email);

}
