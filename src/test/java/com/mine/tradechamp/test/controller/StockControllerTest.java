package com.mine.tradechamp.test.controller;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;

import com.mine.tradechamp.model.Stock;

import static org.testng.Assert.assertEquals;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



//@SpringBootTest
//@AutoConfigureMockMvc
public class StockControllerTest {
	Stock stock; 
	
	private Logger logger = LoggerFactory.getLogger(StockControllerTest.class);
	
	@Autowired
 //   private MockMvc mockMvc;
	
	@Parameters({"stockId"})
	@Test(priority = 1)
	public void stockIdTest(Long stockId ) throws Exception {
		//assertEquals(stock.getStockId(), stockId); 
		
		long myId = 15; 
		//stock.setStockId(myId);
		
		//assertEquals(stock.getStockId(), myId); 
		
		// Act
   /*     ResultActions result = mockMvc.perform(get("/api/stocks/{id}", stockId));

        logger.info("result is " + result); 
        // Assert
        result.andExpect(status().isOk())
              .andExpect(content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(jsonPath("$.id").value(stockId))
              .andExpect(jsonPath("$.stockSymbol").value("TSYY"))
              .andExpect(jsonPath("$.stockName").value("Tesa"));
        
	} */ 	

}}