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

import com.mine.tradechamp.dto.DividendPayoutDto;
import com.mine.tradechamp.dto.StockPortfolioDto;
import com.mine.tradechamp.dto.StockTradeOrderDto;
import com.mine.tradechamp.service.DividendPayoutService;
import com.mine.tradechamp.service.StockPortfolioService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController 
@RequestMapping("/api/dividendpayouts")
public class DividendPayoutController {

	@Autowired
	private DividendPayoutService service; 
	
	@Autowired
	private StockPortfolioService portfolioService;
	
	private Logger logger = LoggerFactory.getLogger(DividendPayoutController.class);
	
	@PostMapping 
	public ResponseEntity<DividendPayoutDto> createDividendPayout(@RequestBody DividendPayoutDto dto) {
		
		logger.info("..... inside DividendPayoutController::createDividendPay()"); 
		DividendPayoutDto savedDividendPayDto = service.createDividendPayout(dto); 
		
		// update portfolio with additional dividend
		addOrUpdatePortfolio(savedDividendPayDto); 
		
		return new ResponseEntity<>(savedDividendPayDto, HttpStatus.CREATED); 
	}
	
	@GetMapping("{id}")
	public ResponseEntity<DividendPayoutDto> getDividendPayById(@PathVariable("id") Long id) { 
		DividendPayoutDto dividendPayDto = service.getDividendPayoutById(id); 
		return ResponseEntity.ok(dividendPayDto); 
	}
	
	// Get all DividendPays 
	@GetMapping
	public ResponseEntity<List<DividendPayoutDto>> getAllDividendPays() { 
		List<DividendPayoutDto> dividendPayDtos = service.getAllDividendPayoutDtos(); 
		return ResponseEntity.ok(dividendPayDtos); 
	}
	
	// Get all DividendPays 
	@PutMapping("{id}")
	public ResponseEntity<DividendPayoutDto> updateDividendPays(@PathVariable("id") Long id, DividendPayoutDto newDividendPayoutDto) { 
		DividendPayoutDto dividendPayDto = service.updateDividendPayout(id, newDividendPayoutDto);  
		return ResponseEntity.ok(dividendPayDto); 
	}
	
	// Delete  
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteDividendPays(@PathVariable("id") Long id) { 
		service.deleteDividendPayout(id);  
		return ResponseEntity.ok("DividendPay record deleted successfully"); 
	}
	
	// Create/Update DividendPayout record 
	void addOrUpdatePortfolio(DividendPayoutDto payoutDto) {
		
		// Find portfolio record, add dividend information 
		StockPortfolioDto portfolioDto = portfolioService.findByAccountIdAndStockSymbol(payoutDto.getAccountId(), payoutDto.getStockSymbol()); 
		if (portfolioDto == null) { 
			logger.info("ODD:::: appears like the portfolio doesn't exist");
			return; 
		}
		logger.info("portfolio record : " + portfolioDto);

		double newTotalDividend = portfolioDto.getTotalDividendAmount() + payoutDto.getPayoutAmount() ; 
			
		logger.info("new div total: " + newTotalDividend);
		portfolioDto.setTotalDividendAmount(newTotalDividend); 
			
		StockPortfolioDto newDto = portfolioService.updateStockPortfolio(portfolioDto.getId(), portfolioDto); 
		logger.info("Portfolio updated : " + newDto);
			
	} // end of function

}
