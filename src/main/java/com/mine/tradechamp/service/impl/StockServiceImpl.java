package com.mine.tradechamp.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.mine.tradechamp.dto.StockDto;
import com.mine.tradechamp.dto.StockPortfolioDto;
import com.mine.tradechamp.dto.mapper.StockMapper;
import com.mine.tradechamp.exception.ResourceNotFoundException;
import com.mine.tradechamp.model.Stock;
import com.mine.tradechamp.model.StockRealQuote;
import com.mine.tradechamp.repo.StockRepository;
import com.mine.tradechamp.service.StockService;

@Service 
public class StockServiceImpl implements StockService {

	@Autowired
	private StockRepository repo; 
	
	@Autowired
	private StockRepository stockRepo; 
	
	private Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);
	
	@Override
	public StockDto createStock(StockDto stockDto) {
		Stock stock = StockMapper.mapToStock(stockDto); 
		Stock savedStock = repo.save(stock); 
		
		return StockMapper.mapToStockDto(savedStock); 
	}

	@Override
	public StockDto getStockById(Long stockId) {
		Stock stock = repo.findById(stockId)
				.orElseThrow(() -> new ResourceNotFoundException("Stock doesn't exist with ID : " + stockId)); 
			
			System.out.println(stock); 
			
			return StockMapper.mapToStockDto(stock);
	}

	@Override
	public List<StockDto> getAllStocks() {
		// get a list all Accounts
		List<Stock> stocks = repo.findAll(); 
				
		// convert all Account records list to Account List using Lambda expression  
		return stocks.stream().map((stock) -> StockMapper.mapToStockDto(stock))
				.collect(Collectors.toList()); 
	}

	@Override
	public StockDto updateStock(Long id, StockDto stockDto) {
		Stock stock = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Stock doesn't exist with ID : " + id));
		
		stock.setStockSymbol(stockDto.getStockSymbol());
		stock.setCurrentStockPrice(stockDto.getCurrentStockPrice());
		stock.setStockName(stockDto.getStockName());
		stock.setStockDescription(stockDto.getStockDescription());
		stock.setStockType(stockDto.getStockType());
		stock.setIndustry(stockDto.getIndustry());
		stock.setDividendYield(stockDto.getDividendYield());
		stock.setDividendFrequency(stockDto.getDividendFrequency());
		stock.setNextDividendDate(stockDto.getNextDividendDate());
		stock.setComments(stockDto.getComments());
		
		Stock updatedStock = repo.save(stock); 
		
		return StockMapper.mapToStockDto(updatedStock); 
	}

	@Override
	public void deleteStock(Long id) {
		Stock stock = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Stock doesn't exist with ID : " + id));
		
		repo.deleteById(id);
		
	}

	@Override
	public StockDto getStockBySymbol(String stockSymbol) {
		Stock stock = new Stock(); 
		
		try { 
			stock = repo.findByStockSymbol(stockSymbol); 
		} catch (Exception e) { 
			logger.info("no stock found with the symbol: "+stockSymbol); 
		}
				//.orElseThrow(() -> new ResourceNotFoundException("Stock doesn't exist with ID : " + stockId)); 
			
		return StockMapper.mapToStockDto(stock);
	}

	@Override
	public List<StockDto> refreshAllStocks() {
		// get a list all Stocks
		List<Stock> stockList = repo.findAll(); 
		String realQuoteUrlUrl = "https://financialmodelingprep.com/stable/quote-short?apikey=sfGC7h1zUyt8eN45pldwxhEewQ9JpbhF"; 
		
		
		// refresh all stocks through API
		for (Stock stock: stockList)  { 
			RestClient restClient = RestClient.create();
			
			StockRealQuote stockRealQuote = restClient.get()
	                //.accept(MediaType.APPLICATION_JSON)
					.uri(realQuoteUrlUrl + "&symbol="+ stock.getStockSymbol())
	                .retrieve()
	                .body(new ParameterizedTypeReference<StockRealQuote>() {});
			
			// update the stock 
			
			
		}
		// convert all Account records list to Account List using Lambda expression  
		return stockList.stream().map((stock) -> StockMapper.mapToStockDto(stock))
				.collect(Collectors.toList()); 
	}

}
