package com.mine.tradechamp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mine.tradechamp.model.Account;
import com.mine.tradechamp.model.Stock;
import com.mine.tradechamp.model.StockPortfolio;
import com.mine.tradechamp.repo.AccountRepository;
import com.mine.tradechamp.repo.DividendAnnouncementRepository;
import com.mine.tradechamp.repo.StockPortfolioRepository;
import com.mine.tradechamp.repo.StockRepository;
import com.mine.tradechamp.service.SharedService;

@Service
public class SharedServiceImpl implements SharedService {

	@Autowired
	private AccountRepository accountRepo; 
	
	@Autowired
	private StockRepository stockRepo; 

	@Autowired
	private StockPortfolioRepository portRepo; 
	
	@Override
	public Stock getStockByStockSymbol(String stockSymbol) {
		Stock stock = stockRepo.findByStockSymbol(stockSymbol);
		return stock;  
	}

	@Override
	public Account getAccountByAccountId(Long accountId) {
		Account account = accountRepo.findByAccountId(accountId);
		// .orElseThrow(() -> new ResourceNotFoundException("Account doesn't exist with ID : " + accountId)); 
		return account; 
	}

	@Override
	public StockPortfolio getAllStockPortfoliosByStockSymbol(String stockSymbol) {
		// TODO Auto-generated method stub
		return null;
	}

}
