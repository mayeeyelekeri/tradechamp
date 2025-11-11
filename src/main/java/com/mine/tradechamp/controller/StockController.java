package com.mine.tradechamp.controller;

import java.awt.PageAttributes.MediaType;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import com.mine.tradechamp.dto.StockDto;
import com.mine.tradechamp.dto.StockPortfolioDto;
import com.mine.tradechamp.model.Stock;
import com.mine.tradechamp.model.StockRealQuote;
import com.mine.tradechamp.service.StockService;
import com.mine.tradechamp.service.StockService;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@RestController 
@RequestMapping("/api/stocks")
public class StockController {

	@Autowired
	private StockService service; 
	
	// RestClient restClient = RestClient.create();
	//@Autowired
	
	
	private static final Logger logger = LoggerFactory.getLogger(StockController.class);
	
	@PostMapping 
	public ResponseEntity<StockDto> createStock(@RequestBody StockDto dto) {
		
		logger.info("inside StockController::createStock()"); 
		StockDto savedDto = service.createStock(dto); 
		
		return new ResponseEntity<>(savedDto, HttpStatus.CREATED); 
	}
	
	@GetMapping("{id}")
	public ResponseEntity<StockDto> getStockById(@PathVariable("id") Long id) { 
		StockDto dto = service.getStockById(id); 
		return ResponseEntity.ok(dto); 
	}
	
	// Get all Stocks 
	@GetMapping
	public ResponseEntity<List<StockDto>> getAllStocks() { 
		List<StockDto> dtos = service.getAllStocks(); 
		return ResponseEntity.ok(dtos); 
	}
	
	// Update Stock  
	@PutMapping("{id}")
	public ResponseEntity<StockDto> updateStock(@PathVariable("id") Long id, StockDto newDto) { 
		StockDto dto = service.updateStock(id, newDto);  
		return ResponseEntity.ok(dto); 
	}
	
	// Delete Stock 
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteStock(@PathVariable("id") Long id) { 
		service.deleteStock(id);  
		return ResponseEntity.ok("Stock record deleted successfully"); 
	}
	
	// Update All Stocks to Current data  
	@GetMapping("/refresh")
	public ResponseEntity<List<StockDto>> refreshAllStocks() { 
		List<StockDto> stockDtoList = service.getAllStocks(); 
		String realQuoteUrl = "https://financialmodelingprep.com/stable/quote-short?apikey=sfGC7h1zUyt8eN45pldwxhEewQ9JpbhF"; 
		
		// refresh all stocks through API
		for (StockDto dto: stockDtoList)  { 
			
			String stockUrl = realQuoteUrl + "&symbol="+ dto.getStockSymbol(); 
			
			logger.info("processing stock "+ dto.getStockSymbol()); 
			logger.info(stockUrl); 
			//List<StockRealQuote> stockRealQuote =
				// 	restTemplate.getForObject(stockUrl, StockRealQuote.class);
			
			List<StockRealQuote> stockQuote = new ArrayList<>(); 
			
			RestClient restClient = RestClient.builder()
	                .baseUrl("https://financialmodelingprep.com/stable/quote-short?apikey=sfGC7h1zUyt8eN45pldwxhEewQ9JpbhF")
	                .build();
			
			try { 
				stockQuote = restClient.get()
	                //.accept(MediaType.APPLICATION_JSON)
					.uri(stockUrl)
	                .retrieve()
	                .body(new ParameterizedTypeReference<List<StockRealQuote>>() {});
			} catch (HttpClientErrorException e) { 
				logger.info(e.getMessage());	
				continue; 
			}
			
			logger.info(stockQuote.toString()); 
			
			// update the stock 
			dto.setCurrentStockPrice(stockQuote.get(0).getPrice()); 
			StockDto newDto = service.updateStock(dto.getId(), dto);
			logger.info("new stock info after update: "+ newDto); 
		}
		
		return ResponseEntity.ok(stockDtoList); 
	}
		
}
