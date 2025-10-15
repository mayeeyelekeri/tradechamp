package com.mine.tradechamp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mine.tradechamp.dto.DividendAnnouncementDto;
import com.mine.tradechamp.dto.StockPortfolioDto;
import com.mine.tradechamp.service.DividendAnnouncementService;
import com.mine.tradechamp.service.StockPortfolioService;

import lombok.AllArgsConstructor;

//@AllArgsConstructor
@RestController 
@RequestMapping("/api/stockportfolios")
public class StockPortfolioController {

	@Autowired
	private StockPortfolioService service; 
	
	private Logger logger = LoggerFactory.getLogger(StockPortfolioController.class);
	
	@PostMapping 
	public ResponseEntity<StockPortfolioDto> createStockPortfolio(@RequestBody StockPortfolioDto dto) {
		
		logger.info("inside StockPortfolioController::createStockPortfolio()"); 
		StockPortfolioDto savedStockPortfolioDto = service.createStockPortfolio(dto); 
		
		return new ResponseEntity<>(savedStockPortfolioDto, HttpStatus.CREATED); 
	}
	
	@GetMapping("{id}")
	public ResponseEntity<StockPortfolioDto> getStockPortfolioById(@PathVariable("id") Long id) { 
		StockPortfolioDto dto = service.getStockPortfolioById(id); 
		return ResponseEntity.ok(dto); 
	}
	
	// Get all StockPortfolios 
	@GetMapping
	public ResponseEntity<List<StockPortfolioDto>> getAllStockPortfolios() { 
		logger.info("inside getAllStockPortfolios controller"); 
		List<StockPortfolioDto> dtos = service.getAllStockPortfolios(); 
		
		logger.info("count: "+ dtos);
		return ResponseEntity.ok(dtos); 
	}
	
	// Get StockPortfolio by ID
	@PutMapping("{id}")
	public ResponseEntity<StockPortfolioDto> updateStockPortfolio(@PathVariable("id") Long id, StockPortfolioDto newDto) { 
		StockPortfolioDto dto = service.updateStockPortfolio(id, newDto);  
		return ResponseEntity.ok(dto); 
	}
	
	// Delete  
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteStockPortfolio(@PathVariable("id") Long id) { 
		service.deleteStockPortfolio(id);  
		return ResponseEntity.ok("StockPortfolio record deleted successfully"); 
	}
	
}
