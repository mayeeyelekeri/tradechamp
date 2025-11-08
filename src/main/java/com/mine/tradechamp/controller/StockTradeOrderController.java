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
import com.mine.tradechamp.service.SharedService;
import com.mine.tradechamp.service.StockPortfolioService;
import com.mine.tradechamp.service.StockTradeOrderService;
import com.mine.tradechamp.service.impl.StockTradeOrderServiceImpl;
import com.mine.tradechamp.utils.MyUtils;

import lombok.AllArgsConstructor;

//@AllArgsConstructor
@RestController 
@RequestMapping("/api/stocktradeorders")
public class StockTradeOrderController {

	@Autowired
	private StockTradeOrderService service; 
	
	@Autowired
	private StockPortfolioService portfolioService;
	
	@Autowired 
	private SharedService sharedService; 
	
	private Logger logger = LoggerFactory.getLogger(StockTradeOrderController.class);
	
	@PostMapping 
	public ResponseEntity<StockTradeOrderDto> createStockTradeOrder(@RequestBody StockTradeOrderDto dto) {
		
		logger.info("inside StockPortfolioController::createStockPortfolio()"); 
		StockTradeOrderDto savedDto = service.createStockTradeOrder(dto); 
		
		// Create/update a portfolio entry
		if (savedDto.getOrderType().equals("BUY") ) { 
			addOrUpdatePortfolio(savedDto);
		} else if( savedDto.getOrderType().equals("SELL")){ 
			deleteOrUpdatePortfolio(savedDto);
		} else { 
			logger.info("..... Wrong order type : "+ savedDto.getOrderType());
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
			
			double oldQuantity = portfolioDto.getStockQuantity();  
			double oldMarketCap = portfolioDto.getCurrentMarketCap(); 
			double oldOriginalInvestment = portfolioDto.getOriginalInvestment(); 
			
			double newStockPrice = tradeDto.getPrice();  
			double newTradeQuantity = tradeDto.getQuantity(); 
			double newTradeMarketCap = newStockPrice * newTradeQuantity;  
			
			double newStockQuantity = portfolioDto.getStockQuantity() + tradeDto.getQuantity(); 
			double newOrigInvest = portfolioDto.getOriginalInvestment() + (tradeDto.getPrice() * tradeDto.getQuantity());
			double newTotalMarketCap = newStockQuantity * tradeDto.getPrice();  
			double newAvgStockPrice = newOrigInvest / newStockQuantity; 

			
			portfolioDto.setStockQuantity(MyUtils.removeDecimalsFromDoubleValue(newStockQuantity));
			portfolioDto.setCurrentMarketCap(MyUtils.removeDecimalsFromDoubleValue(newTotalMarketCap));
			portfolioDto.setOriginalInvestment(MyUtils.removeDecimalsFromDoubleValue(newOrigInvest));
			portfolioDto.setAverageStockPrice(MyUtils.removeDecimalsFromDoubleValue(newAvgStockPrice));
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
			portfolioDto.setCurrentMarketCap(MyUtils.removeDecimalsFromDoubleValue(tradeDto.getQuantity() * tradeDto.getPrice()));
			portfolioDto.setOriginalInvestment(MyUtils.removeDecimalsFromDoubleValue(tradeDto.getQuantity() * tradeDto.getPrice()));
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
			
			double oldAvg = MyUtils.removeDecimalsFromDoubleValue(portfolioDto.getAverageStockPrice() * portfolioDto.getStockQuantity()); 
			double oldQuantity = portfolioDto.getStockQuantity();  
			double oldMarketCap = portfolioDto.getCurrentMarketCap(); 
			double oldOriginalInvestment = portfolioDto.getOriginalInvestment(); 
			
			double newTradeAvg = tradeDto.getPrice();  
			double newTradeQuantity = tradeDto.getQuantity(); 
			double newTradeMarketCap = MyUtils.removeDecimalsFromDoubleValue(newTradeAvg * newTradeQuantity);  
			double newTradeOriginalInvestment = MyUtils.removeDecimalsFromDoubleValue(newTradeAvg * newTradeQuantity); 
			
			// update quantity 
			portfolioDto.setStockQuantity(MyUtils.removeDecimalsFromDoubleValue(oldQuantity - newTradeQuantity));
			// if the quantity is 0, then get rid of the portfolio record itself 
			if (portfolioDto.getStockQuantity() == 0) { 
				logger.info("...mmmmm... looks like a complete selloff for this stock, removing the portfolio for stock :" + 
						portfolioDto.getStockSymbol() + ", and accountId: "+ portfolioDto.getAccountId()); 
				
				portfolioService.deleteStockPortfolio(portfolioDto.getId()); 						
			} else { 
				logger.info("updating the portfolio");
				portfolioDto.setCurrentMarketCap(oldMarketCap - newTradeMarketCap);
				portfolioDto.setOriginalInvestment(oldOriginalInvestment - newTradeOriginalInvestment);
				portfolioDto.setAverageStockPrice(MyUtils.removeDecimalsFromDoubleValue(portfolioDto.getOriginalInvestment()/portfolioDto.getStockQuantity() ));
				portfolioDto.setCurrentStockPrice(tradeDto.getPrice());
				//portfolioDto.setPurchaseDate(tradeDto.getExecutionDate());
				
				StockPortfolioDto newDto = portfolioService.updateStockPortfolio(portfolioDto.getId(), portfolioDto); 
				logger.info("Portfolio updated : " + newDto);
			}
			
		} else { 
			logger.info("Portfolio doesn't exist, nothing to do");
		}	// end of else	
		
	} // end of function deleteOrUpdatePortfolio
}
