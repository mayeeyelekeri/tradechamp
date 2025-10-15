package com.mine.tradechamp.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.client.RestClient;
import org.springframework.web.servlet.ModelAndView;

import com.mine.tradechamp.dto.AccountDto;
import com.mine.tradechamp.dto.StockTradeOrderDto;
import com.mine.tradechamp.model.StockPortfolio;

@Controller 
@RequestMapping("/stocktradeorders")
public class StockTradeOrderControllerWeb {

	@Value("${app.baseUrl}")
	String baseUrl;
	
	@Value("${server.port}")
	String port;
	
	private Logger logger = LoggerFactory.getLogger(StockTradeOrderControllerWeb.class);
	
	RestClient restClient = RestClient.create();

	//public DividendPayControllerWeb() {} 
	
	// this is something odd, not sure why we need it
	public StockTradeOrderControllerWeb(Environment env) { 
		
		// "baseUrl" is not getting passed inside the constructor
		
		System.out.println("---- inside const, baseurl = " + env.getProperty("app.baseUrl"));
		this.restClient = RestClient.builder()
				.baseUrl(env.getProperty("app.baseUrl") + "/api/stocktradeorders")
				.build(); 
	}  
	
	@GetMapping()
	public ModelAndView getAllStockTradeOrders() {
		
		String apiUrl = baseUrl + "/api/stocktradeorders"; 
		
		logger.info("url : "+ apiUrl);
		
		List<StockTradeOrderDto> allTrades = restClient.get()
                //.accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<List<StockTradeOrderDto>>() {});

        logger.info("GET Request Body (allStocks):");
        logger.info("all stocks " + allTrades);
        
        ModelAndView mv = new ModelAndView(); 
        mv.addObject("allStockTradeOrders", allTrades); 
        mv.setViewName("trades/allStockTradeOrders"); 
        
        return mv; 
	}
	
	@GetMapping("{id}")
	public ModelAndView getStockTradeOrderById(@PathVariable("id") Long id) {
		
		logger.info("baseurl " + baseUrl); 
		String apiUrl = baseUrl + "/api/stocktradeorders/" + id; 
		logger.info("url to call " + apiUrl);
		
		StockTradeOrderDto trade = restClient.get()
                .uri(apiUrl)
                .retrieve()
                .body(StockTradeOrderDto.class);

        logger.info("GET Request Body (Object):");
        logger.info("dividend details: "+ trade);
        
        ModelAndView mv = new ModelAndView(); 
        mv.addObject("stockTradeOrderDetails", trade); 
        mv.setViewName("trades/stockTradeOrderDetails"); 
        
        return mv; 
	}
	
	@GetMapping("/add")
	public ModelAndView initiateNewStockTradeOrder() {
		logger.info("inside StockPortfolioControllerWeb().initiateStockPortfolio()");
		
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
        mv.setViewName("trades/newStockTradeOrder"); 
        
        return mv; 
	}
	
	@PostMapping
    public @ResponseBody ModelAndView addStockTraderOrder(@ModelAttribute StockTradeOrderDto order) {
		logger.info("inside StockTradeOrderControllerWeb().addStockTraderOrder(), order is "+order);
		String apiUrl = baseUrl + "/api/stocktradeorders";  
		logger.info("apiUrl:" + apiUrl); 
		
		logger.info("new order: " + order);
		
		// If the date is not specified, then make it current date 
		if (order.getExecutionDate() == null) { 
			order.setExecutionDate(LocalDate.now());
		}
		
		ResponseEntity<StockTradeOrderDto> responseObj = restClient.post()
                .uri(apiUrl) // Specific endpoint for creating posts
                .contentType(MediaType.APPLICATION_JSON) // Set content type for the request body
                .body(order) // Provide the request body
                .retrieve() // Execute the request and retrieve the response
                .toEntity(StockTradeOrderDto.class); // Map the response body to a Post object
		
		logger.info("response from post action: "+ responseObj);
		logger.info("GET Request Body (Object):");
        logger.info("dividend details: "+ responseObj);
        
     // 4. Handle the response
        if (responseObj.getStatusCode().is2xxSuccessful()) {
            logger.info("Post created successfully!");
            logger.info("Created Post: " + responseObj.getBody());
            
            // Create/Update the existing Stock portfolio 
            portfolioUpdate(order); 
            
        } else {
            System.err.println("Failed to create post. Status code: " + responseObj.getStatusCode());
            System.err.println("Response body: " + responseObj.getBody());
        }
        
        ModelAndView mv = new ModelAndView(); 
        mv.addObject("stockTradeOrderDetails", responseObj.getBody()); //get the body which is an object 
        mv.setViewName("trades/stockTradeOrderDetails"); 
        
        return mv; 
	}
	
	// If a new order is created, then create/update the portfolio 
	void portfolioUpdate(StockTradeOrderDto order) { 
		StockPortfolio portfolio = new StockPortfolio(); 
		
		//verify if the stock in that account already exists 
		
	}
}
