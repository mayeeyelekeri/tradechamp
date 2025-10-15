package com.mine.tradechamp.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

import com.mine.tradechamp.dto.AccountDto;
import com.mine.tradechamp.dto.DividendPayoutDto;
import com.mine.tradechamp.dto.StockTradeOrderDto;

import lombok.AllArgsConstructor;

//@AllArgsConstructor
@Controller 
@RequestMapping("/dividendpayouts")
public class DividendPayoutControllerWeb {

	@Value("${app.baseUrl}")
	String baseUrl;
	
	@Value("${server.port}")
	String port;
	
	private Logger logger = LoggerFactory.getLogger(DividendPayoutControllerWeb.class);
	
	RestClient restClient = RestClient.create();

	//public DividendPayControllerWeb() {} 
	
	// this is something odd, not sure why we need it
	public DividendPayoutControllerWeb(Environment env) { 
		
		// "baseUrl" is not getting passed inside the constructor
		
		System.out.println("---- inside const, baseurl = " + env.getProperty("app.baseUrl"));
		this.restClient = RestClient.builder()
				.baseUrl(env.getProperty("app.baseUrl") + "/api/dividendpayouts")
				.build(); 
	}  
	
	@GetMapping()
	public ModelAndView getAllDividendPayouts() {
		
		String apiUrl = baseUrl + "/api/dividendpayouts"; 
		
		logger.info("url : "+ apiUrl);
		
		List<DividendPayoutDto> allDividends = restClient.get()
                //.accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<List<DividendPayoutDto>>() {});

        System.out.println("GET Request Body (allDividends):");
        System.out.println(allDividends);
        
        ModelAndView mv = new ModelAndView(); 
        mv.addObject("allDividends", allDividends); 
        mv.setViewName("payout/allDividendPayouts"); 
        
        return mv; 
	}
	
	@GetMapping("{id}")
	public ModelAndView getDividendPayById(@PathVariable("id") Long id) {
		
		logger.info("baseurl " + baseUrl); 
		String apiUrl = baseUrl + "/api/dividendpayouts/" + id; 
		logger.info("url to call " + apiUrl);
		
		DividendPayoutDto dividendPay = restClient.get()
                .uri(apiUrl)
                .retrieve()
                .body(DividendPayoutDto.class);

        System.out.println("GET Request Body (Object):");
        System.out.println(dividendPay);
        
        ModelAndView mv = new ModelAndView(); 
        mv.addObject("dividendPayDetails", dividendPay); 
        mv.setViewName("payout/dividendPayoutDetails"); 
        
        return mv; 
	}
	
	@GetMapping("/add")
	public ModelAndView initiateNewDividendPayout() {
		logger.info("inside DividendPayoutControllerWeb().initiateDividendPayout()");
		
		// Pass all account information to html 
		String apiUrl = baseUrl + "/api/accounts"; 
		
		logger.info("url : "+ apiUrl);
		
		List<AccountDto> allAccounts = restClient.get()
				.uri(apiUrl)
                .retrieve()
                .body(new ParameterizedTypeReference<List<AccountDto>>() {});

		// Extract the list of names
		List<String> accountIds = new ArrayList<>();
		for (AccountDto account : allAccounts) {
			accountIds.add(String.valueOf(account.getAccountId()));
		}
        
        
        logger.info("GET Request Body (allAccounts):");
        logger.info("all accounts " + accountIds);
        
        ModelAndView mv = new ModelAndView(); 
        
        mv.addObject("allAccounts", accountIds); 
        mv.setViewName("payout/newDividendPayout"); 
        
        return mv; 
	}
	
	@PostMapping
    public @ResponseBody ModelAndView addDividendPayout(@ModelAttribute DividendPayoutDto order) {
		logger.info("inside DividendPayoutControllerWeb().addDividendPayout(), order is "+order);
		String apiUrl = baseUrl + "/api/dividendpayouts";  
		logger.info("apiUrl:" + apiUrl); 
		
		logger.info("new order: " + order);
		
		// If the date is not specified, then make it current date 
		if (order.getPayoutDate() == null) { 
			order.setPayoutDate(LocalDate.now());
		}
		
		logger.info("...calling /api/dividendpayouts with new payout: "+ order); 
		ResponseEntity<DividendPayoutDto> responseObj = restClient.post()
                .uri(apiUrl) // Specific endpoint for creating posts
                .contentType(MediaType.APPLICATION_JSON) // Set content type for the request body
                .body(order) // Provide the request body
                .retrieve() // Execute the request and retrieve the response
                .toEntity(DividendPayoutDto.class); // Map the response body to a Post object
		
		logger.info("response from post action: "+ responseObj);
		logger.info("GET Request Body (Object):");
        logger.info("dividend details: "+ responseObj);
        
     // 4. Handle the response
        if (responseObj.getStatusCode().is2xxSuccessful()) {
            logger.info("Post created successfully!");
            logger.info("Created Post: " + responseObj.getBody());
            
            // Create/Update the existing Stock portfolio 
            //portfolioUpdate(order); 
            
        } else {
            System.err.println("Failed to create post. Status code: " + responseObj.getStatusCode());
            System.err.println("Response body: " + responseObj.getBody());
        }
        
        ModelAndView mv = new ModelAndView(); 
        mv.addObject("dividendPayoutDetails", responseObj.getBody()); //get the body which is an object 
        mv.setViewName("payout/dividendPayoutDetails"); 
        
        return mv; 
	}
	
}
