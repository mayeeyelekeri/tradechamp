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

import com.mine.tradechamp.dto.DividendPayDto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller 
@RequestMapping("/dividendpays")
public class DividendPayControllerWeb {

	@Value("${app.baseUrl}")
	String baseUrl;
	
	@Value("${server.port}")
	String port;
	
	private Logger logger = LoggerFactory.getLogger(DividendPayControllerWeb.class);
	
	RestClient restClient = RestClient.create();

	// this is something odd, not sure why we need it
	public DividendPayControllerWeb(Environment env) { 
		
		// "baseUrl" is not getting passed inside the constructor
		
		System.out.println("inside const, baseurl = " + env.getProperty("app.baseUrl"));
		this.restClient = RestClient.builder()
				.baseUrl("http://localhost:8080/api/dividendpays")
				.build(); 
	}  
	
	@GetMapping()
	public ModelAndView getAllDividendPays() {
		
		String apiUrl = baseUrl + "/api/dividendpays"; 
		
		logger.info("url : "+ apiUrl);
		
		List<DividendPayDto> allDividends = restClient.get()
                //.accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<List<DividendPayDto>>() {});

        System.out.println("GET Request Body (allDividends):");
        System.out.println(allDividends);
        
        ModelAndView mv = new ModelAndView(); 
        mv.addObject("allDividends", allDividends); 
        mv.setViewName("allDividends"); 
        
        return mv; 
	}
	
	@GetMapping("{id}")
	public ModelAndView getDividendPayById(@PathVariable("id") Long id) {
		
		logger.info("baseurl " + baseUrl); 
		String apiUrl = baseUrl + "/api/dividendpays/" + id; 
		logger.info("url to call " + apiUrl);
		
		DividendPayDto dividendPay = restClient.get()
                .uri(apiUrl)
                .retrieve()
                .body(DividendPayDto.class);

        System.out.println("GET Request Body (Object):");
        System.out.println(dividendPay);
        
        ModelAndView mv = new ModelAndView(); 
        mv.addObject("dividendPayDetails", dividendPay); 
        mv.setViewName("dividendPayDetails"); 
        
        return mv; 
	}
	
}
