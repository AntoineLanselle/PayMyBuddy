package com.payMyBuddy.app.service;

import java.util.List;

/**
 * 
 * 
 * @author Antoine
 */
public interface PaginationService {
	
	public List<?> getPagination(int page, int size, List<?> list);
	
	public int getPageNumber(int size, List<?> list);

}
