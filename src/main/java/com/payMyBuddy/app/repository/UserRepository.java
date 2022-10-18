package com.payMyBuddy.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.payMyBuddy.app.model.User;

/**
 * 
 * 
 * @author Antoine
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> { // changer Integer en fonction du type de la clé

}
