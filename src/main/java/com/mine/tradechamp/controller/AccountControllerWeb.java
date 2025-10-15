package com.mine.tradechamp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.mine.tradechamp.dto.AccountDto;

import lombok.AllArgsConstructor;

//@AllArgsConstructor
@Controller 
@RequestMapping("/accounts")
public class AccountControllerWeb {

	@Value("${app.baseUrl}")
	String baseUrl;
	
	@Value("${server.port}")
	String port;
	
	private Logger logger = LoggerFactory.getLogger(AccountControllerWeb.class);
	
	RestClient restClient = RestClient.create();

	//public DividendPayControllerWeb() {} 
	
	// this is something odd, not sure why we need it
	public AccountControllerWeb(Environment env) { 
		// "baseUrl" is not getting passed inside the constructor
		this.restClient = RestClient.builder()
				.baseUrl(env.getProperty("app.baseUrl") + "/api/accounts")
				.build(); 
	}  
	
	@GetMapping()
	public ModelAndView getAllAccounts() {
		
		String apiUrl = baseUrl + "/api/accounts"; 
		
		logger.info("url : "+ apiUrl);
		
		List<AccountDto> allAccounts = restClient.get()
                //.accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<List<AccountDto>>() {});

        System.out.println("GET Request Body (allAccounts):");
        System.out.println(allAccounts);
        
        ModelAndView mv = new ModelAndView(); 
        mv.addObject("allAccounts", allAccounts); 
        mv.setViewName("account/allAccounts"); 
        
        return mv; 
	}
	
	@GetMapping("{id}")
	public ModelAndView getDividendPayById(@PathVariable("id") Long id) {
		
		logger.info("baseurl " + baseUrl); 
		String apiUrl = baseUrl + "/api/accounts/" + id; 
		logger.info("url to call " + apiUrl);
		
		AccountDto account = restClient.get()
                .uri(apiUrl)
                .retrieve()
                .body(AccountDto.class);

        System.out.println("GET Request Body (Object):");
        System.out.println(account);
        
        ModelAndView mv = new ModelAndView(); 
        mv.addObject("accountDetails", account); 
        mv.setViewName("accountDetails"); 
        
        return mv; 
	}
	
}
