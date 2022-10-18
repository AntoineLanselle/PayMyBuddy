package com.payMyBuddy.app.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * 
 * @author Antoine
 */
@Entity
@Table(name = "utilisateur")
public class User {

	@Id
	private int id;
	
	//@Column(name = "")
	public String email;
	
	public String password; 
	
	public List<User> contacts;
	
	public List<Transaction> transactions;
	
	public float balance;
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return the contacts
	 */
	public List<User> getContacts() {
		return contacts;
	}
	
	/**
	 * @param contacts the contacts to set
	 */
	public void setContacts(List<User> contacts) {
		this.contacts = contacts;
	}
	
	/**
	 * @return the transactions
	 */
	public List<Transaction> getTransactions() {
		return transactions;
	}
	
	/**
	 * @param transactions the transactions to set
	 */
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	/**
	 * @return the balance
	 */
	public float getBalance() {
		return balance;
	}
	
	/**
	 * @param balance the balance to set
	 */
	public void setBalance(float balance) {
		this.balance = balance;
	}
	
}
