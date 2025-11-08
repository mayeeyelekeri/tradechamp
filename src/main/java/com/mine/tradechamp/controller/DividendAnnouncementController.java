package com.mine.tradechamp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mine.tradechamp.dto.DividendAnnouncementDto;
import com.mine.tradechamp.dto.DividendPayoutDto;
import com.mine.tradechamp.dto.StockPortfolioDto;
import com.mine.tradechamp.model.Stock;
import com.mine.tradechamp.service.DividendAnnouncementService;
import com.mine.tradechamp.service.SharedService;
import com.mine.tradechamp.utils.MyUtils;

import jakarta.servlet.http.HttpServletRequest;

//@AllArgsConstructor
@RestController 
@RequestMapping("/api/dividendannouncements")
public class DividendAnnouncementController {

	@Autowired
	private DividendAnnouncementService service; 
	
	@Autowired 
	private SharedService sharedService; 
	
	RestClient restClient = RestClient.create();
	
	private Logger logger = LoggerFactory.getLogger(DividendAnnouncementController.class);
	
	@PostMapping 
	public ResponseEntity<DividendAnnouncementDto> createDividendAnnouncement(@RequestBody DividendAnnouncementDto dto) {
		
		logger.info("inside DividendAnnouncementController::createDividendAnnouncement()"); 
		DividendAnnouncementDto savedDividendAnnouncementDto = service.createDividendAnnouncement(dto); 
		
		// create new payouts based on announcement 
		addOrUpdatePayouts(savedDividendAnnouncementDto); 
		
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
		
	int getDividendFrequencyIntValue(String str) { 
		Map<String, Integer> divFrequencyMap = new HashMap<>(); 
		divFrequencyMap.put("Weekly", 52); 
		divFrequencyMap.put("4-Week", 13); 
		divFrequencyMap.put("Monthly", 12); 
		divFrequencyMap.put("Quarterly", 4); 
		
		return divFrequencyMap.get(str); 
	}

	// Create/Update DividendPayout record 
	// Create "Payout" Record for the particular stock in all accounts 
	//
	void addOrUpdatePayouts(DividendAnnouncementDto annDto) {
		
		// Find all portfolio records, create dividend payouts 
		//List<StockPortfolioDto> portfolioDtos = portfolioService.getStockPortfolioByStockSymbol(annDto.getStockSymbol()); 
		String apiUrl = getBaseUrl() + "/api/stockportfolios"; 
		
		logger.info("url : "+ apiUrl);
		
		List<StockPortfolioDto> allPorts = restClient.get()
                //.accept(MediaType.APPLICATION_JSON)
				.uri(apiUrl)
                .retrieve()
                .body(new ParameterizedTypeReference<List<StockPortfolioDto>>() {});
		logger.info("... all portfolio records : " + allPorts);
		
		for (StockPortfolioDto port: allPorts) {
			logger.info("inside portfolio loop: " + port.getAccountId() + ", and stock: " + port.getStockSymbol());		
			
			// proceed only for the relevant stock 
			if (port.getStockSymbol().equals(annDto.getStockSymbol())) { 
				
				
				
				DividendPayoutDto payoutDto = new DividendPayoutDto(); 
								
				payoutDto.setStockSymbol(port.getStockSymbol());
				payoutDto.setAccountId(port.getAccountId()); 
				
				payoutDto.setCurrentStockQuantity(port.getStockQuantity()); 
				payoutDto.setCurrentStockPrice(port.getCurrentStockPrice()); 
				
				
				double frequency = 0;  
				if (annDto.getDividendFrequency() == "") { 
					// get frequency from portfolio itself
					frequency = MyUtils.getDividendFrequencyIntValue(port.getDividendFrequency());
				} else {  
					frequency = MyUtils.getDividendFrequencyIntValue(annDto.getDividendFrequency());
					payoutDto.setDividendFrequency(annDto.getDividendFrequency());
				}
			
				double payoutAmount = MyUtils.removeDecimalsFromDoubleValue(port.getStockQuantity() * annDto.getDeclaredAmount()); 
				double yield = MyUtils.removeDecimalsFromDoubleValue((((annDto.getDeclaredAmount() * frequency)/port.getCurrentStockPrice()) * 100)); 				
				
				
				payoutDto.setCurrentYield(MyUtils.removeDecimalsFromDoubleValue(yield));  
				payoutDto.setPayoutAmount(MyUtils.removeDecimalsFromDoubleValue(payoutAmount)); 
				
				payoutDto.setPayoutDate(annDto.getPayDate()); 			
				
				
				// pass additional stock information from the "Stock" record 
				Stock stock = sharedService.getStockByStockSymbol(port.getStockSymbol()); 
				payoutDto.setCurrentStockPrice(stock.getCurrentStockPrice()); 
				
				// Create new payouts for stock 
				
				apiUrl = getBaseUrl() + "/api/dividendpayouts";
				ResponseEntity<DividendAnnouncementDto> responseObj = restClient.post()
		                .uri(apiUrl) // Specific endpoint for creating posts
		                .contentType(MediaType.APPLICATION_JSON) // Set content type for the request body
		                .body(payoutDto) // Provide the request body
		                .retrieve() // Execute the request and retrieve the response
		                .toEntity(DividendAnnouncementDto.class); // Map the response body to a Post object
				
				logger.info("response from post action: "+ responseObj);
			} // end of if condition 
		} // end of for loop 
	} // end of function
	
	// Get the baseURL of the application 
	public String getBaseUrl() {
	    String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
	            .build()
	            .toUriString();
	    return baseUrl;
	}
}
