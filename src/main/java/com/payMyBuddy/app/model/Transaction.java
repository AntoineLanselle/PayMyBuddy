package com.payMyBuddy.app.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * 
 * @author Antoine
 */
@Entity
@Table(name = "transaction")
public class Transaction {

	@Id
	private int id;
	
	//@Column(name = "")
	public User payer;
	
	public User receiver;
	
	public Date date;
	
	public String description;
	
	public float amount;
	
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
	public Date getDate() {
		return date;
	}
	
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
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
	public float getAmount() {
		return amount;
	}
	
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}
	
}
