package com.mine.tradechamp.model;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mine.tradechamp.dto.DividendPayoutDto;
import com.mine.tradechamp.model.DividendPayout;

import static org.testng.Assert.assertEquals;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;

public class DividendPayoutTest {
	DividendPayout divPay; 
	
	private Logger logger = LoggerFactory.getLogger(DividendPayout.class);
	
	@Parameters({"id", "accountId", "stockSymbol", "currentStockQuantity", "currentStockPrice", "currentYield","payoutAmount", "payoutDate", "dividendFrequency"})
	@Test(priority = 0)
	public void dividendPayConstructorTest(Long id, Long accountId, String stockSymbol, double currentStockQuantity, double currentStockPrice, double currentYield, double payoutAmount, String payoutDateStr, String dividendFrequency) {
		//DividendPay divPay = new DividendPay(id, stock, amount, LocalDate.parse(declaredDateStr), LocalDate.parse(exDividendDateStr), LocalDate.parse(payDateStr));
		assertEquals(divPay.getId(), id); 
		assertEquals(divPay.getAccountId(), accountId);
		assertEquals(divPay.getStockSymbol(), stockSymbol);
		assertEquals(divPay.getCurrentStockQuantity(), currentStockQuantity);
		assertEquals(divPay.getCurrentStockPrice(), currentStockPrice);
		assertEquals(divPay.getCurrentYield(), currentYield);
		assertEquals(divPay.getPayoutAmount(), payoutAmount); 
		assertEquals(divPay.getPayoutDate(), LocalDate.parse(payoutDateStr));
		assertEquals(divPay.getDividendFrequency(), dividendFrequency);
	}

	@Parameters({"id"})
	@Test(priority = 1)
	public void dividendPayoutIdTest(Long id ) {
		assertEquals(divPay.getId(), id); 
		
		long myId = 15; 
		divPay.setId(myId);
		
		assertEquals(divPay.getId(), myId); 
	}
	
	@Parameters({"accountId"})
	@Test(priority = 1)
	public void dividendPayoutAccoutIdTest(Long id ) {
		assertEquals(divPay.getAccountId(), id); 
		
		long myId = 15; 
		divPay.setAccountId(myId);
		
		assertEquals(divPay.getAccountId(), myId); 
	}
	
	@Parameters({"stockSymbol"})
	@Test(priority = 1)
	public void dividendPayoutStockSymbolTest(String stock ) {
		assertEquals(divPay.getStockSymbol(), stock); 
		
		String newStock = "NVDA";
		divPay.setStockSymbol(newStock);
		
		assertEquals(divPay.getStockSymbol(), newStock); 
	}
	
	@Parameters({"currentStockQuantity"})
	@Test(priority = 1)
	public void dividendPayoutCurrentStockQuantityTest(double quantity ) {
		assertEquals(divPay.getCurrentStockQuantity(), quantity); 
		
		long newQuantity = 30; 
		divPay.setCurrentStockQuantity(newQuantity);
		
		assertEquals(divPay.getCurrentStockQuantity(), newQuantity); 
	}
	
	@Parameters({"currentStockPrice"})
	@Test(priority = 1)
	public void dividendPayoutCurrentStockPriceTest(double price ) {
		assertEquals(divPay.getCurrentStockPrice(), price); 
		
		double newPrice = 45.5; 
		divPay.setCurrentStockPrice(newPrice);
		
		assertEquals(divPay.getCurrentStockPrice(), newPrice); 
	}
	
	@Parameters({"currentYield"})
	@Test(priority = 1)
	public void dividendPayoutCurrentYieldTest(double price ) {
		assertEquals(divPay.getCurrentYield(), price); 
		
		double newYield = 47; 
		divPay.setCurrentYield(newYield);
		
		assertEquals(divPay.getCurrentYield(), newYield); 
	}
	
	@Parameters({"payoutAmount"})
	@Test(priority = 1)
	public void dividendPayoutPayoutAmountTest(double amount ) {
		assertEquals(divPay.getPayoutAmount(), amount); 
		
		double newPrice = 0.14; 
		divPay.setPayoutAmount(newPrice);
		
		assertEquals(divPay.getPayoutAmount(), newPrice); 
	}
	
	@Parameters({"payoutDate"})
	@Test(priority = 1)
	public void dividendPayoutCurrentStockPriceTest(String dt ) {
		assertEquals(divPay.getPayoutDate(), LocalDate.parse(dt)); 
		
		LocalDate newDate = LocalDate.parse("2015-12-02"); 
		divPay.setPayoutDate(newDate);
		
		assertEquals(divPay.getPayoutDate(), newDate); 
	}
	
	@Parameters({"dividendFrequency"})
	@Test(priority = 1)
	public void dividendFrequencyTest(String df ) {
		assertEquals(divPay.getDividendFrequency(), df); 
		
		divPay.setDividendFrequency("Monthly");
		
		assertEquals(divPay.getDividendFrequency(), "Monthly"); 
	}	
	
	@Parameters({"id", "accountId", "stockSymbol", "currentStockQuantity", "currentStockPrice", "currentYield","payoutAmount", "payoutDate", "dividendFrequency"})
	@BeforeTest
	public void beforeTest(Long id, Long accountId, String stockSymbol, double currentStockQuantity, double currentStockPrice, double currentYield, double payoutAmount, String payoutDateStr, String dividendFrequency) {
		logger.info("inside DividendPayTesting::beforeTest() ...... ");
		divPay = new DividendPayout(id, accountId, stockSymbol, currentStockQuantity, currentStockPrice, currentYield, payoutAmount,  LocalDate.parse(payoutDateStr), dividendFrequency);
	}
	
	@AfterTest
	public void afterTest() {
		logger.info("...... inside DividendPayoutTesting::afterTest()");
	}
	
}
