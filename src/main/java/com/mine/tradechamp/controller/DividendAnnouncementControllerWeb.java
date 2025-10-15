package com.mine.tradechamp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.mine.tradechamp.dto.DividendAnnouncementDto;
import com.mine.tradechamp.dto.DividendPayoutDto;
import com.mine.tradechamp.dto.StockPortfolioDto;
import com.mine.tradechamp.model.DividendAnnouncement;

import lombok.AllArgsConstructor;

//@AllArgsConstructor
@Controller 
@RequestMapping("/dividendannouncements")
public class DividendAnnouncementControllerWeb {

	@Value("${app.baseUrl}")
	String baseUrl;
	
	@Value("${server.port}")
	String port;
	
	private Logger logger = LoggerFactory.getLogger(DividendAnnouncementControllerWeb.class);
	
	RestClient restClient = RestClient.create();

	//public DividendPayControllerWeb() {} 
	
	// this is something odd, not sure why we need it
	public DividendAnnouncementControllerWeb(Environment env) { 
		
		// "baseUrl" is not getting passed inside the constructor
		
		System.out.println("---- inside const, baseurl = " + env.getProperty("app.baseUrl"));
		this.restClient = RestClient.builder()
				.baseUrl(env.getProperty("app.baseUrl") + "/api/dividendannouncements")
				.build(); 
	}  
	
	@GetMapping()
	public ModelAndView getAllDividendAnnouncements() {
		
		String apiUrl = baseUrl + "/api/dividendannouncements"; 
		
		logger.info("url : "+ apiUrl);
		
		List<DividendAnnouncementDto> allDividends = restClient.get()
                //.accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<List<DividendAnnouncementDto>>() {});

        logger.info("GET Request Body (allDividends):");
        logger.info("all divs" + allDividends);
        
        ModelAndView mv = new ModelAndView(); 
        mv.addObject("allDividendAnnouncements", allDividends); 
        mv.setViewName("divAnnounce/allDividendAnnouncements"); 
        
        return mv; 
	}
	
	@GetMapping("{id}")
	public ModelAndView getDividendPayById(@PathVariable("id") Long id) {
		
		logger.info("baseurl " + baseUrl); 
		String apiUrl = baseUrl + "/api/dividendannouncements/" + id; 
		logger.info("url to call " + apiUrl);
		
		DividendAnnouncementDto dividendAnnouncement = restClient.get()
                .uri(apiUrl)
                .retrieve()
                .body(DividendAnnouncementDto.class);

        logger.info("GET Request Body (Object):");
        logger.info("dividend details: "+ dividendAnnouncement);
        
        ModelAndView mv = new ModelAndView(); 
        mv.addObject("dividendAnnouncementDetails", dividendAnnouncement); 
        mv.setViewName("divAnnounce/dividendAnnouncementDetails"); 
        
        return mv; 
	}
	
	@GetMapping("/add")
	public ModelAndView initiateNewDividendAnnouncement() {
		logger.info("inside DividendAnnouncementControllerWeb().addNewDividendAnnouncement()");
		
        ModelAndView mv = new ModelAndView(); 
        mv.setViewName("divAnnounce/newDivAnnounce"); 
        
        return mv; 
	}
	
	@PostMapping
    public @ResponseBody ModelAndView addNewAnnouncement(@ModelAttribute DividendAnnouncementDto div) {
		logger.info("inside DividendAnnouncementControllerWeb().addNewAnnouncement(), dividend is "+div);
		String apiUrl = baseUrl + "/api/dividendannouncements";  
		logger.info("apiUrl:" + apiUrl); 
		
		ResponseEntity<DividendAnnouncementDto> responseObj = restClient.post()
                .uri(apiUrl) // Specific endpoint for creating posts
                .contentType(MediaType.APPLICATION_JSON) // Set content type for the request body
                .body(div) // Provide the request body
                .retrieve() // Execute the request and retrieve the response
                .toEntity(DividendAnnouncementDto.class); // Map the response body to a Post object
		
		logger.info("response from post action: "+ responseObj);
		logger.info("GET Request Body (Object):");
        logger.info("dividend details: "+ responseObj);
        
     // 4. Handle the response
        if (responseObj.getStatusCode().is2xxSuccessful()) {
            System.out.println("Post created successfully!");
            System.out.println("Created Post: " + responseObj.getBody());
            
            // create new payouts based on announcement 
    		addOrUpdatePayouts(responseObj.getBody()); 
    		
        } else {
            System.err.println("Failed to create post. Status code: " + responseObj.getStatusCode());
            System.err.println("Response body: " + responseObj.getBody());
        }
        
        ModelAndView mv = new ModelAndView(); 
        mv.addObject("dividendAnnouncementDetails", responseObj.getBody()); //get the body which is an object 
        mv.setViewName("divAnnounce/dividendAnnouncementDetails"); 
        
        return mv; 
	}
	
	// Create/Update DividendPayout record 
	void addOrUpdatePayouts(DividendAnnouncementDto annDto) {
		
		// Find all portfolio records, create dividend payouts 
		//List<StockPortfolioDto> portfolioDtos = portfolioService.getStockPortfolioByStockSymbol(annDto.getStockSymbol()); 
		String apiUrl = baseUrl + "/api/stockportfolios"; 
		
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
				
				payoutDto.setAccountId(port.getAccountId()); 
				
				payoutDto.setCurrentStockQuantity(port.getStockQuantity()); 
				payoutDto.setCurrentStockPrice(port.getCurrentStockPrice()); 
				
				double frequency = getDividendFrequencyIntValue(annDto.getDividendFrequency()); 
				double payoutAmount = port.getStockQuantity() * annDto.getDeclaredAmount(); 
				double yield = (((annDto.getDeclaredAmount() * frequency)/port.getCurrentStockPrice()) * 100); 
				
				payoutDto.setCurrentYield(yield); 
				payoutDto.setPayoutAmount(payoutAmount); 
				
				payoutDto.setStockSymbol(annDto.getStockSymbol()); 
				payoutDto.setPayoutDate(annDto.getPayDate()); 
				payoutDto.setDividendFrequency(annDto.getDividendFrequency());
				
				
				// Create new payouts for stock 
				
				apiUrl = baseUrl + "/api/dividendpayouts";
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
	
	int getDividendFrequencyIntValue(String str) { 
		Map<String, Integer> divFrequencyMap = new HashMap<>(); 
		divFrequencyMap.put("Weekly", 52); 
		divFrequencyMap.put("4-Week", 13); 
		divFrequencyMap.put("Monthly", 12); 
		divFrequencyMap.put("Quarterly", 4); 
		
		return divFrequencyMap.get(str); 
	} // end of function getDividendFrequencyIntValue
	
}
