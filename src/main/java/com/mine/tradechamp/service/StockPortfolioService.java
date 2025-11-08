package com.mine.tradechamp.service;

import java.util.List;

import com.mine.tradechamp.dto.DividendAnnouncementDto;
import com.mine.tradechamp.dto.StockPortfolioDto;

public interface StockPortfolioService {
	
	StockPortfolioDto createStockPortfolio(StockPortfolioDto stockPortfolioDto); 
	
	// get a single record by ID 
	StockPortfolioDto getStockPortfolioById(Long stockPortfolioId); 
	
	// get all records 
	List<StockPortfolioDto> getAllStockPortfolios(); 
	
	// update DividendPay
	StockPortfolioDto updateStockPortfolio(Long id, StockPortfolioDto stockPortfolioDto); 
	
	// delete DividendPay
	void deleteStockPortfolio(Long id); 	
	
	// get a single record by accountId and stockSymbol 
	StockPortfolioDto findByAccountIdAndStockSymbol(Long accountId, String stockSymbol); 
	
	// get a list of record by stockSymbol 
	List<StockPortfolioDto> getStockPortfoliosByStockSymbol(String stockSymbol); 
		
	
}
