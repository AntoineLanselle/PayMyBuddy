package com.payMyBuddy.app.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * 
 * @author Antoine
 */
@Entity
@Table(name = "transaction_bank")
public class TransactionBank {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
	private User user;

	@Column(name = "date")
	private LocalDateTime date;

	@Column(name = "bankaccount")
	private String bankaccount;

	@Column(name = "amount")
	private double amount;

	public TransactionBank() {
		this.date = LocalDateTime.now();
	}
	public TransactionBank(User user, String bankaccount, float amount) {
		this.user = user;
		this.date = LocalDateTime.now();
		this.bankaccount = bankaccount;
		this.amount = amount;
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
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the date
	 */
	public LocalDateTime getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	/**
	 * @return the bankAccount
	 */
	public String getBankaccount() {
		return bankaccount;
	}

	/**
	 * @param bankAccount the bankAccount to set
	 */
	public void setBankaccount(String bankaccount) {
		this.bankaccount = bankaccount;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

}
