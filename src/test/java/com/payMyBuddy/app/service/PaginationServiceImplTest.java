package com.payMyBuddy.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.payMyBuddy.app.model.TransactionUser;

public class PaginationServiceImplTest {

	private PaginationServiceImpl paginationServiceImpl = new PaginationServiceImpl();
	
	@Test
	public void getPageNumber_ShouldReturnUpperInteger() {
		// GIVEN
		List<TransactionUser> transactionsOne = new ArrayList<TransactionUser>();
		List<TransactionUser> transactionsTwo = new ArrayList<TransactionUser>();
		for(int i=0; i<9; i++) {
			TransactionUser transaction = new TransactionUser();
			transactionsOne.add(transaction);
			transactionsTwo.add(transaction);
		}
		transactionsTwo.add(new TransactionUser());
		
		// WHEN
		int resultOne = paginationServiceImpl.getPageNumber(3, transactionsOne);
		int resultTwo = paginationServiceImpl.getPageNumber(3, transactionsTwo);
		
		// THEN
		assertEquals(3, resultOne);
		assertEquals(4, resultTwo);
	}
	
	@Test
	public void getPagination_ShouldReturnEmptyListAndSubList() {
		// GIVEN
		List<TransactionUser> transactions = new ArrayList<TransactionUser>();
		for(int i=0; i<9; i++) transactions.add(new TransactionUser());

		// WHEN
		List<?> resultEmptyList = paginationServiceImpl.getPagination(4, 3, transactions); 
		List<?> resultSubList = paginationServiceImpl.getPagination(0, 3, transactions); 
		
		// THEN
		assertEquals(Collections.emptyList(), resultEmptyList);
		assertEquals(transactions.subList(0, 3), resultSubList);
		
		
	}
	
}
