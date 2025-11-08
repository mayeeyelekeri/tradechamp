package com.mine.tradechamp.service;

import java.util.List;

import com.mine.tradechamp.dto.StockDto;

public interface StockService {
	
	StockDto createStock(StockDto stockDto); 
	
	// get a single record by ID 
	StockDto getStockById(Long stockId); 
	
	// get a single record by Name 
	StockDto getStockBySymbol(String stockSymbol); 
		
	// get all records 
	List<StockDto> getAllStocks(); 
	
	// update DividendPay
	StockDto updateStock(Long id, StockDto stockDto); 
	
	// delete DividendPay
	void deleteStock(Long id); 	
	
	// get all records 
	List<StockDto> refreshAllStocks(); 
}
