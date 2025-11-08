package com.mine.tradechamp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mine.tradechamp.model.Account;
import com.mine.tradechamp.model.Stock;
import com.mine.tradechamp.model.StockPortfolio;

// all the DB methods are already created automatically from this class
public interface StockPortfolioRepository extends JpaRepository<StockPortfolio, Long> {
	
	// special function to access data based on multiple fields 
	// information is used in "StockTradeOrderController" class 
	StockPortfolio findByAccountAndStock(Account account, Stock stock);
	
	// special function to access data based on stock symbol  
	List<StockPortfolio> findByStock(Stock stock);
		
}
