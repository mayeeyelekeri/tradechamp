package com.mine.tradechamp.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mine.tradechamp.dto.StockPortfolioDto;
import com.mine.tradechamp.dto.mapper.StockPortfolioMapper;
import com.mine.tradechamp.exception.ResourceNotFoundException;
import com.mine.tradechamp.model.StockPortfolio;
import com.mine.tradechamp.repo.StockPortfolioRepository;
import com.mine.tradechamp.service.StockPortfolioService;

@Service
public class StockPortfolioServiceImpl implements StockPortfolioService{

	@Autowired
	private StockPortfolioRepository repo;

	private Logger logger = LoggerFactory.getLogger(StockPortfolioServiceImpl.class);
	
	@Override
	public StockPortfolioDto createStockPortfolio(StockPortfolioDto dto) {
		StockPortfolio stock = StockPortfolioMapper.mapToStockPortfolio(dto); 
		StockPortfolio savedStock = repo.save(stock); 
		
		return StockPortfolioMapper.mapToStockPortfolioDto(savedStock); 
	}

	@Override
	public StockPortfolioDto getStockPortfolioById(Long id) {
		StockPortfolio stock = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Stock doesn't exist with ID : " + id)); 
			
		logger.info("Portfolio: "+ stock); 
			
		return StockPortfolioMapper.mapToStockPortfolioDto(stock); 
	}

	@Override
	public List<StockPortfolioDto> getAllStockPortfolios() {
		// get a list all stock portfolios
		logger.info("inside impl, going to call findall() ");
		List<StockPortfolio> stocks = repo.findAll(); 
				
		logger.info("inside impl, all stocks: " + stocks);
		// convert all StockPortfolio records list to StockPortfolio List using Lambda expression  
		return stocks.stream().map((stock) -> StockPortfolioMapper.mapToStockPortfolioDto(stock))
			.collect(Collectors.toList()); 
	}

	@Override
	public StockPortfolioDto updateStockPortfolio(Long id, StockPortfolioDto newStock) {
		StockPortfolio stock = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("StockPortfolio doesn't exist with ID : " + id));
		
		stock.setAccountId(newStock.getAccountId());
		stock.setStockSymbol(newStock.getStockSymbol());
		stock.setStockName(newStock.getStockName());
		stock.setProductType(newStock.getProductType());
		stock.setStockQuantity(newStock.getStockQuantity());
		stock.setCurrentStockPrice(newStock.getCurrentStockPrice());
		stock.setAverageStockPrice(newStock.getAverageStockPrice());
		stock.setCurrentMarketCap(newStock.getCurrentMarketCap());
		stock.setOriginalInvestment(newStock.getOriginalInvestment());
		stock.setDrip(newStock.getDrip()); 
		stock.setOriginalInvestment(newStock.getOriginalInvestment()); 
		stock.setPurchaseDate(newStock.getPurchaseDate());
		stock.setDividendFrequency(newStock.getDividendFrequency());
		stock.setComments(newStock.getComments());
		stock.setTotalDividendAmount(newStock.getTotalDividendAmount());
		stock.setPlCurrent(newStock.getPlCurrent());
		stock.setPlWithDividend(newStock.getPlWithDividend());
		stock.setCurrentYield(newStock.getCurrentYield());
		
		StockPortfolio updatedStock = repo.save(stock); 
		
		return StockPortfolioMapper.mapToStockPortfolioDto(updatedStock); 
	}

	@Override
	public void deleteStockPortfolio(Long id) {
		StockPortfolio stock = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Stock doesn't exist with ID : " + id)); 
		
		repo.deleteById(id); 
	}

	@Override
	public StockPortfolioDto findByAccountIdAndStockSymbol(Long accountId, String stockSymbol) {
		logger.info("inside findByAccountIdAndStockSymbol, accountId and stockSymbol :" + accountId + ", " +stockSymbol); 
		
		StockPortfolio stock = repo.findByAccountIdAndStockSymbol(accountId, stockSymbol); 
		logger.info("Portfolio: "+ stock); 
			
		if (stock == null) { 
			return null; 
		}
		return StockPortfolioMapper.mapToStockPortfolioDto(stock); 
	}

	@Override
	public List<StockPortfolioDto> getStockPortfolioByStockSymbol(String stockSymbol) {
		// get a list all stock portfolios by stock symbol 
		logger.info("inside portfolio impl, going to call findall() based on stock symbol ");
		List<StockPortfolio> portfolios = repo.findByStockSymbol(stockSymbol); 
						
		logger.info("inside impl, all portfolios: " + portfolios);
		// convert all StockPortfolio records list to StockPortfolio List using Lambda expression  
		return portfolios.stream().map((stock) -> StockPortfolioMapper.mapToStockPortfolioDto(stock))
			.collect(Collectors.toList()); 
	}
}
