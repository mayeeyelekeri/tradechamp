package com.mine.tradechamp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mine.tradechamp.model.StockPortfolio;

// all the DB methods are already created automatically from this class
public interface StockPortfolioRepository extends JpaRepository<StockPortfolio, Long> {
	
	// special function to access data based on multiple fields 
	// information is used in "StockTradeOrderController" class 
	StockPortfolio findByAccountIdAndStockSymbol(Long accountId, String stockSymbol);
	
	// special function to access data based on stock symbol  
	List<StockPortfolio> findByStockSymbol(String stockSymbol);
		
}
