package com.mine.tradechamp.model;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mine.tradechamp.dto.DividendPayoutDto;

import static org.testng.Assert.assertEquals;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;

public class DividendPayoutDtoTest {
	DividendPayoutDto divPay; 
	
	private Logger logger = LoggerFactory.getLogger(DividendPayoutDto.class);
	
	@Parameters({"id", "accountId", "stockSymbol", "currentStockQuantity", "currentStockPrice", "currentYield","payoutAmount", "payoutDate", "dividendFrequency"})
	@Test(priority = 0)
	public void dividendPayoutDtoConstructorTest(Long id, Long accountId, String stockSymbol, double currentStockQuantity, double currentStockPrice, double currentYield, double payoutAmount, String payoutDateStr, String dividendFrequency) {
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
	public void dividendPayoutDtoIdTest(Long id ) {
		assertEquals(divPay.getId(), id); 
		
		long myId = 15; 
		divPay.setId(myId);
		
		assertEquals(divPay.getId(), myId); 
	}
	
	@Parameters({"accountId"})
	@Test(priority = 1)
	public void dividendPayoutDtoAccoutIdTest(Long id ) {
		assertEquals(divPay.getAccountId(), id); 
		
		long myId = 15; 
		divPay.setAccountId(myId);
		
		assertEquals(divPay.getAccountId(), myId); 
	}
	
	@Parameters({"stockSymbol"})
	@Test(priority = 1)
	public void dividendPayoutDtoStockSymbolTest(String stock ) {
		assertEquals(divPay.getStockSymbol(), stock); 
		
		String newStock = "NVDA";
		divPay.setStockSymbol(newStock);
		
		assertEquals(divPay.getStockSymbol(), newStock); 
	}
	
	@Parameters({"currentStockQuantity"})
	@Test(priority = 1)
	public void dividendPayoutDtoCurrentStockQuantityTest(double quantity ) {
		assertEquals(divPay.getCurrentStockQuantity(), quantity); 
		
		long newQuantity = 30; 
		divPay.setCurrentStockQuantity(newQuantity);
		
		assertEquals(divPay.getCurrentStockQuantity(), newQuantity); 
	}
	
	@Parameters({"currentStockPrice"})
	@Test(priority = 1)
	public void dividendPayoutDtoCurrentStockPriceTest(double price ) {
		assertEquals(divPay.getCurrentStockPrice(), price); 
		
		double newPrice = 45.5; 
		divPay.setCurrentStockPrice(newPrice);
		
		assertEquals(divPay.getCurrentStockPrice(), newPrice); 
	}
	
	@Parameters({"currentYield"})
	@Test(priority = 1)
	public void dividendPayoutDtoCurrentYieldTest(double price ) {
		assertEquals(divPay.getCurrentYield(), price); 
		
		double newYield = 47; 
		divPay.setCurrentYield(newYield);
		
		assertEquals(divPay.getCurrentYield(), newYield); 
	}
	
	@Parameters({"payoutAmount"})
	@Test(priority = 1)
	public void dividendPayoutDtoPayoutAmountTest(double amount ) {
		assertEquals(divPay.getPayoutAmount(), amount); 
		
		double newPrice = 0.14; 
		divPay.setPayoutAmount(newPrice);
		
		assertEquals(divPay.getPayoutAmount(), newPrice); 
	}
	
	@Parameters({"payoutDate"})
	@Test(priority = 1)
	public void dividendPayoutDtoCurrentStockPriceTest(String dt ) {
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
		divPay = new DividendPayoutDto(id, accountId, stockSymbol, currentStockQuantity, currentStockPrice, currentYield, payoutAmount,  LocalDate.parse(payoutDateStr), dividendFrequency);
	}
	
	@AfterTest
	public void afterTest() {
		logger.info("...... inside DividendPayoutTesting::afterTest()");
	}
	
}
