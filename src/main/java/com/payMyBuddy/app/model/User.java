package com.payMyBuddy.app.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * 
 * @author Antoine
 */
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "balance")
	private double balance;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "user_connection", 
			joinColumns = @JoinColumn(name = "user_id"), 
			inverseJoinColumns = @JoinColumn(name = "connection_id"))
	  private List<User> connections = new ArrayList<>();

	@OneToMany(mappedBy = "payer", fetch = FetchType.LAZY) 
	private List<TransactionUser> transactionsPayer = new ArrayList<>();
	
	@ManyToMany(mappedBy = "receiver", fetch = FetchType.LAZY) 
	private List<TransactionUser> transactionsReceiver = new ArrayList<>();

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<TransactionBank> transactionsBank = new ArrayList<>();
	
	public User(String email, String password) {
		setEmail(email);
		setPassword(password);
		setBalance(0);
	}
	
	public User() {
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

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
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * @return the connections
	 */
	public List<User> getConnections() {
		return connections;
	}

	/**
	 * @param connections the connections to set
	 */
	public void setConnections(List<User> connections) {
		this.connections = connections;
	}

	/**
	 * @return the transactionsUser
	 */
	public List<TransactionUser> getTransactionsPayer() {
		return transactionsPayer;
	}

	/**
	 * @param transactionsUser the transactionsUser to set
	 */
	public void setTransactionsPayer(List<TransactionUser> transactionsUser) {
		this.transactionsPayer = transactionsUser;
	}
	
	/**
	 * @return the transactionsUser
	 */
	public List<TransactionUser> getTransactionsReceiver() {
		return transactionsReceiver;
	}

	/**
	 * @param transactionsUser the transactionsUser to set
	 */
	public void setTransactionsReceiver(List<TransactionUser> transactionsUser) {
		this.transactionsReceiver = transactionsUser;
	}

	/**
	 * @return the transactionsAccount
	 */
	public List<TransactionBank> getTransactionsBank() {
		return transactionsBank;
	}

	/**
	 * @param transactionsAccount the transactionsAccount to set
	 */
	public void setTransactionsBank(List<TransactionBank> transactionsBank) {
		this.transactionsBank = transactionsBank;
	}	
	
}
