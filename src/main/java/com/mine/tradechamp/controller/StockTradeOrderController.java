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
import com.mine.tradechamp.dto.StockTradeOrderDto;
import com.mine.tradechamp.model.StockPortfolio;
import com.mine.tradechamp.service.DividendAnnouncementService;
import com.mine.tradechamp.service.StockPortfolioService;
import com.mine.tradechamp.service.StockTradeOrderService;

import lombok.AllArgsConstructor;

//@AllArgsConstructor
@RestController 
@RequestMapping("/api/stocktradeorders")
public class StockTradeOrderController {

	@Autowired
	private StockTradeOrderService service; 
	
	@Autowired
	private StockPortfolioService portfolioService;
	
	private Logger logger = LoggerFactory.getLogger(StockTradeOrderController.class);
	
	@PostMapping 
	public ResponseEntity<StockTradeOrderDto> createStockTradeOrder(@RequestBody StockTradeOrderDto dto) {
		
		logger.info("inside StockPortfolioController::createStockPortfolio()"); 
		StockTradeOrderDto savedDto = service.createStockTradeOrder(dto); 
		
		// Create/update a portfolio entry
		if (savedDto.getOrderType().equals("Buy")) { 
			addOrUpdatePortfolio(savedDto);
		} else { 
			deleteOrUpdatePortfolio(savedDto);
		}		
		
		return new ResponseEntity<>(savedDto, HttpStatus.CREATED); 
	}
	
	@GetMapping("{id}")
	public ResponseEntity<StockTradeOrderDto> getStockTradeOrderById(@PathVariable("id") Long id) { 
		StockTradeOrderDto dto = service.getStockTradeOrderById(id); 
		return ResponseEntity.ok(dto); 
	}
	
	// Get all StockTradeOrders 
	@GetMapping
	public ResponseEntity<List<StockTradeOrderDto>> getAllStockTradeOrders() { 
		logger.info("inside getAllStockPortfolios controller"); 
		List<StockTradeOrderDto> dtos = service.getAllStockTradeOrders(); 
		
		logger.info("count: "+ dtos);
		return ResponseEntity.ok(dtos); 
	}
	
	// Get StockPortfolio by ID
	@PutMapping("{id}")
	public ResponseEntity<StockTradeOrderDto> updateStockTradeOrder(@PathVariable("id") Long id, StockTradeOrderDto newDto) { 
		StockTradeOrderDto dto = service.updateStockTradeOrder(id, newDto);  
		return ResponseEntity.ok(dto); 
	}
	
	// Delete  
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteStockTradeOrder(@PathVariable("id") Long id) { 
		service.deleteStockTradeOrder(id);  
		return ResponseEntity.ok("StockTradeOrder record deleted successfully"); 
	}

	// Create/Update StockPortfolio record 
	void addOrUpdatePortfolio(StockTradeOrderDto tradeDto) {		
		// Identify if there is a portfolio record 
		StockPortfolioDto portfolioDto = portfolioService.findByAccountIdAndStockSymbol(tradeDto.getAccountId(), tradeDto.getStockSymbol()); 
		logger.info("portfolio record : " + portfolioDto);
		
		// If portfolio doesn't exist, create it 
		if (portfolioDto != null ) { 
			logger.info("Portfolio already exists, update it");		
			
			double oldAvg = portfolioDto.getAverageStockPrice() * portfolioDto.getStockQuantity(); 
			double oldQuantity = portfolioDto.getStockQuantity();  
			double oldMarketCap = portfolioDto.getCurrentMarketCap(); 
			double oldOriginalInvestment = portfolioDto.getOriginalInvestment(); 
			
			double newTradeAvg = tradeDto.getPrice();  
			double newTradeQuantity = tradeDto.getQuantity(); 
			double newTradeMarketCap = newTradeAvg * newTradeQuantity;  
			double newTradeOriginalInvestment = newTradeAvg * newTradeQuantity; 
			
			
			portfolioDto.setStockQuantity(oldQuantity + newTradeQuantity);
			portfolioDto.setCurrentMarketCap(oldMarketCap + newTradeMarketCap);
			portfolioDto.setOriginalInvestment(oldOriginalInvestment + newTradeOriginalInvestment);
			portfolioDto.setAverageStockPrice( (oldMarketCap + newTradeMarketCap)/(oldQuantity + newTradeQuantity) );
			portfolioDto.setCurrentStockPrice(tradeDto.getPrice());
			portfolioDto.setPurchaseDate(tradeDto.getExecutionDate());
			
			StockPortfolioDto newDto = portfolioService.updateStockPortfolio(portfolioDto.getId(), portfolioDto); 
			logger.info("Portfolio updated : " + newDto);
			
		} else { 
			logger.info("Portfolio doesn't exist, create it");
			portfolioDto = new StockPortfolioDto(); 
			portfolioDto.setAccountId(tradeDto.getAccountId());
			portfolioDto.setStockSymbol(tradeDto.getStockSymbol());
			portfolioDto.setAverageStockPrice(tradeDto.getPrice());
			portfolioDto.setStockQuantity(tradeDto.getQuantity());
			portfolioDto.setCurrentMarketCap(tradeDto.getQuantity() * tradeDto.getPrice());
			portfolioDto.setOriginalInvestment(tradeDto.getQuantity() * tradeDto.getPrice());
			portfolioDto.setPurchaseDate(tradeDto.getExecutionDate());
			
			portfolioDto.setCurrentStockPrice(tradeDto.getPrice());
			
			StockPortfolioDto newDto = portfolioService.createStockPortfolio(portfolioDto); 
			logger.info("New Portfolio created : " + newDto);
		}
	} // end of function
 	
	// Delete/Update StockPortfolio record 
	void deleteOrUpdatePortfolio(StockTradeOrderDto tradeDto) {
		// Identify if there is a portfolio record 
		StockPortfolioDto portfolioDto = portfolioService.findByAccountIdAndStockSymbol(tradeDto.getAccountId(), tradeDto.getStockSymbol()); 
		logger.info("portfolio record : " + portfolioDto);
		
		// If portfolio exists, delete/update it 
		if (portfolioDto != null ) { 
			
			logger.info("Portfolio already exists, update it");		
			
			double oldAvg = portfolioDto.getAverageStockPrice() * portfolioDto.getStockQuantity(); 
			double oldQuantity = portfolioDto.getStockQuantity();  
			double oldMarketCap = portfolioDto.getCurrentMarketCap(); 
			double oldOriginalInvestment = portfolioDto.getOriginalInvestment(); 
			
			double newTradeAvg = tradeDto.getPrice();  
			double newTradeQuantity = tradeDto.getQuantity(); 
			double newTradeMarketCap = newTradeAvg * newTradeQuantity;  
			double newTradeOriginalInvestment = newTradeAvg * newTradeQuantity; 
			
			// update quantity 
			portfolioDto.setStockQuantity(oldQuantity - newTradeQuantity);
			portfolioDto.setCurrentMarketCap(oldMarketCap - newTradeMarketCap);
			portfolioDto.setOriginalInvestment(oldOriginalInvestment - newTradeOriginalInvestment);
			portfolioDto.setAverageStockPrice( (oldMarketCap - newTradeMarketCap)/(oldQuantity - newTradeQuantity) );
			portfolioDto.setCurrentStockPrice(tradeDto.getPrice());
			portfolioDto.setPurchaseDate(tradeDto.getExecutionDate());
			
			StockPortfolioDto newDto = portfolioService.updateStockPortfolio(portfolioDto.getId(), portfolioDto); 
			logger.info("Portfolio updated : " + newDto);
			
			// if the quantity is 0, then get rid of the portfolio record itself 
			if (newDto.getStockQuantity() == 0) { 
				logger.info("...mmmmm... looks like a complete selloff for this stock, removing the portfolio for stock :" + 
						newDto.getStockSymbol() + ", and accountId: "+ newDto.getAccountId()); 
				
				portfolioService.deleteStockPortfolio(newDto.getId()); 						
			}
		} else { 
			logger.info("Portfolio doesn't exist, nothing to do");
		}	// end of else	
		
	} // end of function deleteOrUpdatePortfolio
}
