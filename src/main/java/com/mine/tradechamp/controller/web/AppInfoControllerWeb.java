package com.mine.tradechamp.controller.web;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mine.tradechamp.dto.StockPortfolioDto;
import com.mine.tradechamp.utils.MyUtils;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringBootVersion;

//@AllArgsConstructor
@Controller 
@RequestMapping("/appinfo")
public class AppInfoControllerWeb {
	
	@Value("${WELCOME_MESSAGE}")
	String welcomeMessage; 
	
	@Value("${spring.datasource.url}")
	String databaseUrl; 
	
	private Logger logger = LoggerFactory.getLogger(AppInfoControllerWeb.class);
	
	@GetMapping
	public ModelAndView appInfo() {
		
		logger.info("baseurl " + getBaseUrl()); 
		String springBootVersion = SpringBootVersion.getVersion();
		logger.info("spring version " + springBootVersion);
		
        ModelAndView mv = new ModelAndView(); 
        mv.addObject("springBootVersion", springBootVersion);
        mv.addObject("welcomeMessage", welcomeMessage);
        mv.addObject("databaseUrl", databaseUrl); 
        mv.setViewName("appInfo"); 
        
        return mv; 
	}
	
	// get application base URL 
	public String getBaseUrl() {
	    String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
	            .build()
	            .toUriString();
	    return baseUrl;
	}
	
}
