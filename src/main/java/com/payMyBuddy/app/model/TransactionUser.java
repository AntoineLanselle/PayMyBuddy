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
@Table(name = "transaction_user")
public class TransactionUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payer_id") 
	private User payer;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "receiver_id") 
	private User receiver;

	@Column(name = "date")
	private LocalDateTime date;

	@Column(name = "description")
	private String description;

	@Column(name = "amount")
	private double amount;
	
	@Column(name = "tax")
	private double tax;

	public TransactionUser() {
		this.date = LocalDateTime.now();
		this.amount = 0;
		this.tax = 0;
	}
	
	public TransactionUser(User payer, User receiver, String description, double amount, double tax) {
		this.payer = payer;
		this.receiver = receiver;
		this.date = LocalDateTime.now();
		this.description = description;
		this.amount = amount;
		this.tax = tax;
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
	 * @return the payer
	 */
	public User getPayer() {
		return payer;
	}

	/**
	 * @param payer the payer to set
	 */
	public void setPayer(User payer) {
		this.payer = payer;
	}

	/**
	 * @return the receiver
	 */
	public User getReceiver() {
		return receiver;
	}

	/**
	 * @param receiver the receiver to set
	 */
	public void setReceiver(User receiver) {
		this.receiver = receiver;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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

	/**
	 * @return the tax
	 */
	public double getTax() {
		return tax;
	}

	/**
	 * @param tax the tax to set
	 */
	public void setTax(double tax) {
		this.tax = tax;
	}
	
	
	
}
