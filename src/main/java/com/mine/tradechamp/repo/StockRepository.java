package com.mine.tradechamp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mine.tradechamp.model.Stock;

// all the DB methods are already created automatically from this class
public interface StockRepository extends JpaRepository<Stock, Long> {

	// special function to access data based on stock symbol  
	Stock findByStockSymbol(String stockSymbol);
}
