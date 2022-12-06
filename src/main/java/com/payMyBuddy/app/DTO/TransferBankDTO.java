package com.payMyBuddy.app.DTO;

public class TransferBankDTO {

	private String bankaccount;
	private float amount;
	
	public TransferBankDTO(String bankaccount, Float amount) {
		this.bankaccount = bankaccount;
		this.amount = amount;
	}
	
	/**
	 * 
	 */
	public String getBankAccount() {
		return bankaccount;
	}

	/**
	 * 
	 */
	public void setBankAccount(String bankaccount) {
		this.bankaccount = bankaccount;
	}

	/**
	 * 
	 */
	public float getAmount() {
		return amount;
	}

	/**
	 * 
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	
	
}
