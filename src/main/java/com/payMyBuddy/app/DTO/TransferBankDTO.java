package com.payMyBuddy.app.DTO;

public class TransferBankDTO {

	private String bankaccount;
	private double amount;
	
	public TransferBankDTO(String bankaccount, double amount) {
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
	public double getAmount() {
		return amount;
	}

	/**
	 * 
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	
}
