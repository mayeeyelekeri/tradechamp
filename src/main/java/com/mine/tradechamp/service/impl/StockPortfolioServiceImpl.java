package com.mine.tradechamp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mine.tradechamp.dto.StockPortfolioDto;
import com.mine.tradechamp.exception.ResourceNotFoundException;
import com.mine.tradechamp.model.Account;
import com.mine.tradechamp.model.Stock;
import com.mine.tradechamp.model.StockPortfolio;
import com.mine.tradechamp.repo.AccountRepository;
import com.mine.tradechamp.repo.StockPortfolioRepository;
import com.mine.tradechamp.repo.StockRepository;
import com.mine.tradechamp.service.SharedService;
import com.mine.tradechamp.service.StockPortfolioService;
import com.mine.tradechamp.utils.MyUtils;

@Service
public class StockPortfolioServiceImpl implements StockPortfolioService{

	@Autowired
	private StockPortfolioRepository repo;

	@Autowired
	private StockRepository stockRepo;
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired 
	private SharedService sharedService; 
	
	private Logger logger = LoggerFactory.getLogger(StockPortfolioServiceImpl.class);
	
	@Override
	public StockPortfolioDto createStockPortfolio(StockPortfolioDto dto) {
		// Make sure to create the stock information first 
		//stockRepo.getById(null)
		
		StockPortfolio portfolio = mapToStockPortfolio(dto); 
		StockPortfolio savedStockportfolio = repo.save(portfolio); 		
		
		// convert portfolio to dto with additional fields 
		return mapToStockPortfolioDto(savedStockportfolio); 
	}

	@Override
	public StockPortfolioDto getStockPortfolioById(Long id) {
		StockPortfolio stockPortfolio = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Stock doesn't exist with ID : " + id)); 
			
		logger.info("Portfolio: "+ stockPortfolio); 
		
		return mapToStockPortfolioDto(stockPortfolio); 
	}

	@Override
	public List<StockPortfolioDto> getAllStockPortfolios() {
		// get a list all stock portfolios
		logger.info("inside impl, going to call findall() ");
		List<StockPortfolio> portfolios = repo.findAll(); 
				
		logger.info("inside impl, all portfolios: " + portfolios);
		// convert all StockPortfolio records list to StockPortfolio List using Lambda expression  
		
		return portfolios.stream().map((port) -> mapToStockPortfolioDto(port))
				.collect(Collectors.toList()); 
		
	}

	@Override
	public StockPortfolioDto updateStockPortfolio(Long id, StockPortfolioDto port) {
		StockPortfolio newPortfolio = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("StockPortfolio doesn't exist with ID : " + id));
		
		Stock stock = stockRepo.findByStockSymbol(port.getStockSymbol()); 
		Account account = accountRepo.findByAccountId(port.getAccountId()); 
				// .orElseThrow(() -> new ResourceNotFoundException("Account doesn't exist with ID : " + port.getAccountId())); 
			
		// update reference information for Stock record 
		stock.setCurrentStockPrice(port.getCurrentStockPrice());
		stock.setDividendFrequency(port.getDividendFrequency());
		stock.setDividendYield(port.getCurrentYield()); 
		
		//stock.setDividendYield()
		newPortfolio.setStock(stock); 
		newPortfolio.setAccount(account); 
		
		newPortfolio.setStockQuantity(port.getStockQuantity());
		newPortfolio.setAverageStockPrice(port.getAverageStockPrice());
		
		newPortfolio.setOriginalInvestment(port.getOriginalInvestment());
		newPortfolio.setDrip(port.getDrip()); 
		newPortfolio.setOriginalInvestment(port.getOriginalInvestment()); 
		
		newPortfolio.setTotalDividendAmount(port.getTotalDividendAmount());
		
		// newPortfolio.setComments(port.getComments());
		// update the purchaseDate only for new transaction 
		if (newPortfolio.getPurchaseDate() == null) { 
			newPortfolio.setPurchaseDate(port.getPurchaseDate());
		}
		StockPortfolio updatedPortfolio = repo.save(newPortfolio); 
		
		return mapToStockPortfolioDto(updatedPortfolio); 
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
		
		StockPortfolio port = repo.findByAccountAndStock(sharedService.getAccountByAccountId(accountId), sharedService.getStockByStockSymbol(stockSymbol)); 
		logger.info("Portfolio: "+ port); 
			
		if (port == null) { 
			return null; 
		}
		return mapToStockPortfolioDto(port); 
	} 

	 
	@Override
	public List<StockPortfolioDto> getStockPortfoliosByStockSymbol(String stockSymbol) {
		// get a list all stock portfolios by stock symbol 
		logger.info("inside portfolio impl, going to call findall() based on stock symbol ");
		List<StockPortfolio> portfolios = repo.findByStock(sharedService.getStockByStockSymbol(stockSymbol)); 
						
		logger.info("inside impl, all portfolios: " + portfolios);
		// convert all StockPortfolio records list to StockPortfolio List using Lambda expression  
		return portfolios.stream().map((port) -> mapToStockPortfolioDto(port))
			.collect(Collectors.toList()); 
	}  
	
	// Convert database record to "dto" with additional information 
	public StockPortfolioDto mapToStockPortfolioDto(StockPortfolio port) {		
		
		StockPortfolioDto dto = new StockPortfolioDto(
				port.getId(), 
				port.getAccount().getAccountId(), 
				port.getStock().getStockSymbol(),
				port.getStock().getStockName(),
				port.getStock().getStockType(),  
				port.getStockQuantity(), 
				port.getStock().getCurrentStockPrice(), 
				port.getAverageStockPrice(), 
				
				// Calculate "currentMarketCap" based on currentStockPrice and Quantity 
				// currentMarketCap = (curerntStockPrice * quantity)
				MyUtils.removeDecimalsFromDoubleValue(port.getStock().getCurrentStockPrice() * port.getStockQuantity()), 
				
				port.getOriginalInvestment(), 
				port.getDrip(), 
				port.getPurchaseDate(), 
				port.getStock().getDividendFrequency(),
				port.getStock().getComments(), 
				port.getTotalDividendAmount(),
				
				// Calculate "plCurrent" value based on currentStockPrice and Quantity 
				// plCurrent = (curerntStockPrice * quantity) - OriginalInvestment 
				
				MyUtils.removeDecimalsFromDoubleValue((port.getStock().getCurrentStockPrice() * port.getStockQuantity()) - port.getOriginalInvestment()),
				
				// Calculate "plCurrentWithDividend" value based on currentStockPrice and Quantity 
				// plCurrentWithDividend = ((curerntStockPrice * quantity) + totalDividend - OriginalInvestment)
				MyUtils.removeDecimalsFromDoubleValue(port.getTotalDividendAmount() + (port.getStock().getCurrentStockPrice() * port.getStockQuantity()) - port.getOriginalInvestment()),
				
				port.getStock().getDividendYield()
		); 
		
		return dto; 
	}
	
	// convert portfolio dto to Portfolio db record, strip unnecessary information 
	public StockPortfolio mapToStockPortfolio(StockPortfolioDto dto) {
		
		// Create new Portfolio record 
		StockPortfolio portfolio =  new StockPortfolio(
				dto.getId(), 
				sharedService.getAccountByAccountId(dto.getAccountId()),  
				sharedService.getStockByStockSymbol(dto.getStockSymbol()),  
				dto.getStockQuantity(), 
				dto.getAverageStockPrice(), 
				dto.getOriginalInvestment(), 
				dto.getDrip(), 
				dto.getPurchaseDate(), 
				dto.getComments(), 
				dto.getTotalDividendAmount()
		); 
		
		return portfolio; 
	}
	
}
