package com.mine.tradechamp.dto.mapper;

import com.mine.tradechamp.dto.StockTradeOrderDto;
import com.mine.tradechamp.model.StockTradeOrder;

public class StockTradeOrderMapper {

	public static StockTradeOrderDto mapToStockTradeOrderDto(StockTradeOrder stock) {
		return new StockTradeOrderDto(
				stock.getId(), 
				stock.getAccountId(), 
				stock.getStockSymbol(),
				stock.getOrderType(),
				stock.getQuantity(), 
				stock.getPrice(), 
				stock.getExecutionDate(), 
				stock.getComments()			
		); 
	}
	
	public static StockTradeOrder mapToStockTradeOrder(StockTradeOrderDto stock) {
		return new StockTradeOrder(
				stock.getId(), 
				stock.getAccountId(), 
				stock.getStockSymbol(),
				stock.getOrderType(),
				stock.getQuantity(), 
				stock.getPrice(), 
				stock.getExecutionDate(), 
				stock.getComments()
		); 
	}
}
