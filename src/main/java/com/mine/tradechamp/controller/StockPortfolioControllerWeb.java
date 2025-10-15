package com.mine.tradechamp.controller;

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

import com.mine.tradechamp.dto.StockPortfolioDto;


import lombok.AllArgsConstructor;

//@AllArgsConstructor
@Controller 
@RequestMapping("/stockportfolios")
public class StockPortfolioControllerWeb {

	@Value("${app.baseUrl}")
	String baseUrl;
	
	@Value("${server.port}")
	String port;
	
	private Logger logger = LoggerFactory.getLogger(StockPortfolioControllerWeb.class);
	
	RestClient restClient = RestClient.create();

	//public DividendPayControllerWeb() {} 
	
	// this is something odd, not sure why we need it
	public StockPortfolioControllerWeb(Environment env) { 
		
		// "baseUrl" is not getting passed inside the constructor
		
		System.out.println("---- inside const, baseurl = " + env.getProperty("app.baseUrl"));
		this.restClient = RestClient.builder()
				.baseUrl(env.getProperty("app.baseUrl") + "/api/stockportfolios")
				.build(); 
	}  
	
	@GetMapping()
	public ModelAndView getAllStockPorfolios() {
		
		String apiUrl = baseUrl + "/api/stockportfolios"; 
		
		logger.info("url : "+ apiUrl);
		
		List<StockPortfolioDto> allStocks = restClient.get()
                //.accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<List<StockPortfolioDto>>() {});

        logger.info("GET Request Body (allStocks):");
        logger.info("all stocks " + allStocks);
        
        ModelAndView mv = new ModelAndView(); 
        mv.addObject("allStockPortfolios", allStocks); 
        mv.setViewName("portfolio/allStockPortfolios"); 
        
        return mv; 
	}
	
	@GetMapping("{id}")
	public ModelAndView getStockPortfolioById(@PathVariable("id") Long id) {
		
		logger.info("baseurl " + baseUrl); 
		String apiUrl = baseUrl + "/api/stockportfolios/" + id; 
		logger.info("url to call " + apiUrl);
		
		StockPortfolioDto stock = restClient.get()
                .uri(apiUrl)
                .retrieve()
                .body(StockPortfolioDto.class);

        logger.info("GET Request Body (Object):");
        logger.info("dividend details: "+ stock);
        
        ModelAndView mv = new ModelAndView(); 
        mv.addObject("stockPortfolioDetails", stock); 
        mv.setViewName("portfolio/stockPortfolioDetails"); 
        
        return mv; 
	}
	
	@GetMapping("/add")
	public ModelAndView initiateNewStockPortfolio() {
		logger.info("inside StockPortfolioControllerWeb().initiateStockPortfolio()");
		
        ModelAndView mv = new ModelAndView(); 
        mv.setViewName("portfolio/newStockPortfolio"); 
        
        return mv; 
	}
	
	@PostMapping
    public @ResponseBody ModelAndView addStockPortfolio(@ModelAttribute StockPortfolioDto div) {
		logger.info("inside StockPortfolioControllerWeb().addStockPortfolio(), stock is "+div);
		String apiUrl = baseUrl + "/api/stockportfolios";  
		logger.info("apiUrl:" + apiUrl); 
		
		ResponseEntity<StockPortfolioDto> responseObj = restClient.post()
                .uri(apiUrl) // Specific endpoint for creating posts
                .contentType(MediaType.APPLICATION_JSON) // Set content type for the request body
                .body(div) // Provide the request body
                .retrieve() // Execute the request and retrieve the response
                .toEntity(StockPortfolioDto.class); // Map the response body to a Post object
		
		logger.info("response from post action: "+ responseObj);
		logger.info("GET Request Body (Object):");
        logger.info("dividend details: "+ responseObj);
        
     // 4. Handle the response
        if (responseObj.getStatusCode().is2xxSuccessful()) {
            System.out.println("Post created successfully!");
            System.out.println("Created Post: " + responseObj.getBody());
        } else {
            System.err.println("Failed to create post. Status code: " + responseObj.getStatusCode());
            System.err.println("Response body: " + responseObj.getBody());
        }
        
        ModelAndView mv = new ModelAndView(); 
        mv.addObject("stockPortfolioDetails", responseObj.getBody()); //get the body which is an object 
        mv.setViewName("portfolio/stockPortfolioDetails"); 
        
        return mv; 
	}
}
