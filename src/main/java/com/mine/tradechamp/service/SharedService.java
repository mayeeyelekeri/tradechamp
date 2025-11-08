package com.mine.tradechamp.service;

import com.mine.tradechamp.model.Account;
import com.mine.tradechamp.model.Stock;
import com.mine.tradechamp.model.StockPortfolio;

public interface SharedService {
	
	public Stock getStockByStockSymbol(String stockSymbol); 
	
	public Account getAccountByAccountId(Long accountId); 
	
	public StockPortfolio getAllStockPortfoliosByStockSymbol(String stockSymbol);
	
}
