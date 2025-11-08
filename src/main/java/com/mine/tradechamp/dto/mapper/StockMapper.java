package com.mine.tradechamp.dto.mapper;

import com.mine.tradechamp.dto.StockDto;
import com.mine.tradechamp.model.Stock;

public class StockMapper {

	public static StockDto mapToStockDto(Stock model) {
		return new StockDto(
				model.getStockId(),
				model.getStockSymbol(), 
				model.getCurrentStockPrice(), 
				model.getStockName(), 
				model.getStockDescription(), 
				model.getStockType(), 
				model.getIndustry(),
				model.getDividendYield(), 
				model.getDividendFrequency(), 
				model.getNextDividendDate(), 
				model.getComments(), 
				model.getModifiedDate()
		); 
	}
	
	public static Stock mapToStock(StockDto dto) {
		return new Stock(
				dto.getId(),
				dto.getStockSymbol(), 
				dto.getCurrentStockPrice(), 
				dto.getStockName(), 
				dto.getStockDescription(), 
				dto.getStockType(), 
				dto.getIndustry(),
				dto.getDividendYield(), 
				dto.getDividendFrequency(), 
				dto.getNextDividendDate(), 
				dto.getComments(), 
				dto.getModifiedDate()
		); 
	}
}
