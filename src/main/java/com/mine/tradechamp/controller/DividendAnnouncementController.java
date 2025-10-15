package com.mine.tradechamp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.mine.tradechamp.dto.DividendPayoutDto;
import com.mine.tradechamp.dto.StockPortfolioDto;
import com.mine.tradechamp.service.DividendAnnouncementService;
import com.mine.tradechamp.service.DividendPayoutService;
import com.mine.tradechamp.service.StockPortfolioService;

import lombok.AllArgsConstructor;

//@AllArgsConstructor
@RestController 
@RequestMapping("/api/dividendannouncements")
public class DividendAnnouncementController {

	@Autowired
	private DividendAnnouncementService service; 
	
	@Autowired
	private DividendPayoutService payoutService;
	
	@Autowired
	private StockPortfolioService portfolioService;
	
	private Logger logger = LoggerFactory.getLogger(DividendAnnouncementController.class);
	
	@PostMapping 
	public ResponseEntity<DividendAnnouncementDto> createDividendAnnouncement(@RequestBody DividendAnnouncementDto dto) {
		
		logger.info("inside DividendAnnouncementController::createDividendAnnouncement()"); 
		DividendAnnouncementDto savedDividendAnnouncementDto = service.createDividendAnnouncement(dto); 
		
		// create new payouts based on announcement 
		//addOrUpdatePortfolio(savedDividendAnnouncementDto); 
		
		return new ResponseEntity<>(savedDividendAnnouncementDto, HttpStatus.CREATED); 
	}
	
	@GetMapping("{id}")
	public ResponseEntity<DividendAnnouncementDto> getDividendPayById(@PathVariable("id") Long id) { 
		DividendAnnouncementDto dividendAnnouncementDto = service.getDividendAnnouncementById(id); 
		return ResponseEntity.ok(dividendAnnouncementDto); 
	}
	
	// Get all DividendPays 
	@GetMapping
	public ResponseEntity<List<DividendAnnouncementDto>> getAllDividendPays() { 
		List<DividendAnnouncementDto> dividendPayDtos = service.getAllDividendAnnouncementDtos(); 
		return ResponseEntity.ok(dividendPayDtos); 
	}
	
	// Get all DividendPays 
	@PutMapping("{id}")
	public ResponseEntity<DividendAnnouncementDto> updateDividendAnnouncements(@PathVariable("id") Long id, DividendAnnouncementDto newDividendAnnouncementDto) { 
		DividendAnnouncementDto dividendPayDto = service.updateDividendAnnouncement(id, newDividendAnnouncementDto);  
		return ResponseEntity.ok(dividendPayDto); 
	}
	
	// Delete  
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteDividendPays(@PathVariable("id") Long id) { 
		service.deleteDividendAnnouncement(id);  
		return ResponseEntity.ok("DividendPay record deleted successfully"); 
	}
	
	// Create/Update DividendPayout record 
	void addOrUpdatePortfolio(DividendAnnouncementDto annDto) {
		
		// Find all portfolio records, create dividend payouts 
		List<StockPortfolioDto> portfolioDtos = portfolioService.getStockPortfolioByStockSymbol(annDto.getStockSymbol()); 
		logger.info("portfolio records : " + portfolioDtos);
		
		for (StockPortfolioDto port: portfolioDtos) {
			logger.info("inside portfolio loop: " + port.getAccountId() + ", and stock: " + port.getStockSymbol());		
			
			DividendPayoutDto payoutDto = new DividendPayoutDto(); 
			
			payoutDto.setAccountId(port.getAccountId()); 
			
			payoutDto.setCurrentStockQuantity(port.getStockQuantity()); 
			payoutDto.setCurrentStockPrice(port.getCurrentStockPrice()); 
			
			payoutDto.setCurrentYield((annDto.getDeclaredAmount() * getDividendFrequencyIntValue(annDto.getDividendFrequency()))/port.getCurrentStockPrice()); 
			payoutDto.setPayoutAmount(port.getStockQuantity() * port.getCurrentStockPrice()); 
			
			payoutDto.setStockSymbol(annDto.getStockSymbol()); 
			payoutDto.setPayoutDate(annDto.getPayDate()); 
			payoutDto.setDividendFrequency(annDto.getDividendFrequency());

			DividendPayoutDto newDto = payoutService.createDividendPayout(payoutDto); 
			logger.info("New Payout added : " + newDto);
			
		} 
	} // end of function
	
	int getDividendFrequencyIntValue(String str) { 
		Map<String, Integer> divFrequencyMap = new HashMap<>(); 
		divFrequencyMap.put("Weekly", 52); 
		divFrequencyMap.put("4-Week", 13); 
		divFrequencyMap.put("Monthly", 12); 
		divFrequencyMap.put("Quarterly", 4); 
		
		return divFrequencyMap.get(str); 
	}
}
