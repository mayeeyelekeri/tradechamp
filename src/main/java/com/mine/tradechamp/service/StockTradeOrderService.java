package com.mine.tradechamp.service;

import java.util.List;

import com.mine.tradechamp.dto.StockTradeOrderDto;

public interface StockTradeOrderService {
	
	StockTradeOrderDto createStockTradeOrder(StockTradeOrderDto dto); 
	
	// get a single record by ID 
	StockTradeOrderDto getStockTradeOrderById(Long id); 
	
	// get all records 
	List<StockTradeOrderDto> getAllStockTradeOrders(); 
	
	// update DividendPay
	StockTradeOrderDto updateStockTradeOrder(Long id, StockTradeOrderDto dto); 
	
	// delete DividendPay
	void deleteStockTradeOrder(Long id); 		
}
