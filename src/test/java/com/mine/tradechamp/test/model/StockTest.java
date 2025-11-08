package com.mine.tradechamp.test.model;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mine.tradechamp.model.Stock;

import static org.testng.Assert.assertEquals;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;

public class StockTest {
	Stock stock; 
	
	private Logger logger = LoggerFactory.getLogger(Stock.class);
	
	@Parameters({"stockId", "stockSymbol", "stockPrice", "stockName", "stockDescription", "stockType", "industry", "dividendYield", "dividendFrequency", "comments", "nextDividendDateStr"})
	@Test(priority = 0)
	public void stockConstructorTest(Long stockId, String stockSymbol, double stockPrice, String stockName, String stockDescription, String stockType, String industry, double dividendYield, String dividendFrequency, String comments, String nextDividendDateStr)  {
		
		LocalDate nextDividendDate = LocalDate.parse(nextDividendDateStr);
		
		assertEquals(stock.getCurrentStockPrice(), stockPrice);
		assertEquals(stock.getStockId(), stockId); 
		assertEquals(stock.getComments(), comments);
		assertEquals(stock.getDividendYield(), dividendYield); 
		assertEquals(stock.getIndustry(), industry); 
		assertEquals(stock.getNextDividendDate(), nextDividendDate);
		assertEquals(stock.getStockDescription(), stockDescription);
		assertEquals(stock.getStockName(), stockName);
		assertEquals(stock.getStockSymbol(), stockSymbol);
		
		
		assertEquals(stock.getStockType(), stockType);
		
		
	}

	@Parameters({"stockId"})
	@Test(priority = 1)
	public void stockIdTest(Long stockId ) {
		assertEquals(stock.getStockId(), stockId); 
		
		long myId = 15; 
		stock.setStockId(myId);
		
		assertEquals(stock.getStockId(), myId); 
	}
	  
	@Parameters({"currentStockPrice"})
	@Test(priority = 1)
	public void currentStockPriceTest(double id ) {
		assertEquals(stock.getCurrentStockPrice(), id); 
		
		long myId = 15; 
		stock.setCurrentStockPrice(myId);
		
		assertEquals(stock.getCurrentStockPrice(), myId); 
	}
	
	@Parameters({"comments"})
	@Test(priority = 1)
	public void getCommentsTest(String name ) {
		assertEquals(stock.getComments(), name); 
		
		String myName = "different name"; 
		stock.setComments(myName);
		
		assertEquals(stock.getComments(), myName); 
	}
	
	@Parameters({"dividendYield"})
	@Test(priority = 1)
	public void dividendYieldTest(double yield ) {
		assertEquals(stock.getDividendYield(), yield); 
		
		double myYield = 56.7;  
		stock.setDividendYield(myYield);
		
		assertEquals(stock.getDividendYield(), myYield); 
	}
	
	@Parameters({"industry"})
	@Test(priority = 1)
	public void industryTest(String industry ) {
		assertEquals(stock.getIndustry(), industry); 
		
		String myIindustry = "Charles Schwab"; 
		stock.setIndustry(myIindustry);
		
		assertEquals(stock.getIndustry(), myIindustry); 
	}
	
	@Parameters({"nextDividendDateStr"})
	@Test(priority = 1)
	public void nextDividendDateTest(String nextDividendDateStr) {
		assertEquals(stock.getNextDividendDate(), LocalDate.parse(nextDividendDateStr)); 
		
		LocalDate newDate = LocalDate.parse("2006-07-29");
		stock.setNextDividendDate(newDate);
		
		assertEquals(stock.getNextDividendDate(), newDate); 
	}
	
	@Parameters({"stockDescription"})
	@Test(priority = 1)
	public void stockDescriptionTest(String desc ) {
		assertEquals(stock.getStockDescription(), desc); 
		
		String mydesc = "Charles Schwab"; 
		stock.setStockDescription(mydesc);
		
		assertEquals(stock.getStockDescription(), mydesc); 
	}
	
	@Parameters({"stockName"})
	@Test(priority = 1)
	public void stockNameTest(String name) {
		assertEquals(stock.getStockName(), name); 
		
		String myName = "Charles Schwab"; 
		stock.setStockName(myName);
		
		assertEquals(stock.getStockName(), myName); 
	}
	
	@Parameters({"stockSymbol"})
	@Test(priority = 1)
	public void stockSymbolTest(String symbol ) {
		assertEquals(stock.getStockSymbol(), symbol); 
		
		String myName = "Something else"; 
		stock.setStockSymbol(myName);
		
		assertEquals(stock.getStockSymbol(), myName); 
	}
	
	@Parameters({"stockType"})
	@Test(priority = 1)
	public void stockType(String stockType ) {
		assertEquals(stock.getStockType(), stockType); 
		
		String myStockType = "DividendETF"; 
		stock.setStockType(myStockType);
		
		assertEquals(stock.getStockType(), myStockType); 
	}
	
	@Parameters({"stockId", "stockSymbol", "stockPrice", "stockName", "stockDescription", "stockType", "industry", "dividendYield", "dividendFrequency", "comments", "nextDividendDateStr", "modifiedDateStr"})
	@BeforeTest
	public void beforeTest(Long stockId, String stockSymbol, double stockPrice, String stockName, String stockDescription, String stockType, String industry, double dividendYield, String dividendFrequency, String comments, String nextDividendDateStr, String modifiedDateStr) {
		logger.info("inside AccountTest::beforeTest() ...... ");
		stock = new Stock(stockId, stockSymbol, stockPrice, stockName, stockDescription,
				stockType, industry, dividendYield, dividendFrequency,
				LocalDate.parse(nextDividendDateStr), comments, LocalDate.parse(modifiedDateStr));
		logger.info("stock record: "+stock); 
	}
	
	@AfterTest
	public void afterTest() {
		logger.info("...... inside AccountTesting::afterTest()");
	}
	
}
