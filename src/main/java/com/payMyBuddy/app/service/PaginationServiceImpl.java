package com.payMyBuddy.app.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * 
 * 
 * @author Antoine
 */
@Service
public class PaginationServiceImpl implements PaginationService {

	/**
	 * 
	 */
	@Override
	public List<?> getPagination(int page, int size, List<?> list) {

		int startItem = (page * size);

		if (list.size() < startItem) {
			return Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + size, list.size());
			return list.subList(startItem, toIndex);
		}
	}

	/**
	 * 
	 */
	@Override
	public int getPageNumber(int pageSize, List<?> list) {

		int number = list.size() / pageSize;
		
		if (number * pageSize < list.size()) {
			return number + 1;
		} else {
			return number;
		}
	}

}
