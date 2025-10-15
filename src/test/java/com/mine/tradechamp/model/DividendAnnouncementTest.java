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

public class DividendAnnouncementTest {
	DividendAnnouncement divPay; 
	
	private Logger logger = LoggerFactory.getLogger(DividendAnnouncementTest.class);
	
	@Parameters({"id", "stockSymbol", "declaredAmount", "declaredDate", "exDividendDate", "payDate", "dividendFrequency"})
	@Test(priority = 0)
	public void dividendPayConstructorTest(Long id, String stockSymbol, double declaredAmount, String declaredDateStr, String exDividendDateStr, String payDateStr, String dividendFrequency) {
		assertEquals(divPay.getId(), id); 
		assertEquals(divPay.getStockSymbol(), stockSymbol);
		assertEquals(divPay.getDeclaredAmount(), declaredAmount);
		assertEquals(divPay.getDeclaredDate(), LocalDate.parse(declaredDateStr));
		assertEquals(divPay.getExDividendDate(), LocalDate.parse(exDividendDateStr));
		assertEquals(divPay.getPayDate(), LocalDate.parse(payDateStr));
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
	
	@Parameters({"stockSymbol"})
	@Test(priority = 1)
	public void dividendPayoutStockSymbolTest(String stock ) {
		assertEquals(divPay.getStockSymbol(), stock); 
		
		String newStock = "NVDA";
		divPay.setStockSymbol(newStock);
		
		assertEquals(divPay.getStockSymbol(), newStock); 
	}
	
	@Parameters({"declaredAmount"})
	@Test(priority = 1)
	public void declaredAmountTest(double declaredAmount ) {
		assertEquals(divPay.getDeclaredAmount(), declaredAmount); 
		
		long newQuantity = 30; 
		divPay.setDeclaredAmount(newQuantity);
		
		assertEquals(divPay.getDeclaredAmount(), newQuantity); 
	}
		
	@Parameters({"declaredDate"})
	@Test(priority = 1)
	public void declaredDateTest(String dt ) {
		assertEquals(divPay.getDeclaredDate(), LocalDate.parse(dt)); 
		
		LocalDate newDate = LocalDate.parse("2015-12-02"); 
		divPay.setDeclaredDate(newDate);
		
		assertEquals(divPay.getDeclaredDate(), newDate); 
	}
	
	@Parameters({"exDividendDate"})
	@Test(priority = 1)
	public void exDividendDateTest(String dt ) {
		assertEquals(divPay.getExDividendDate(), LocalDate.parse(dt)); 
		
		LocalDate newDate = LocalDate.parse("2015-12-02"); 
		divPay.setExDividendDate(newDate);
		
		assertEquals(divPay.getExDividendDate(), newDate); 
	}
	
	@Parameters({"payDate"})
	@Test(priority = 1)
	public void payDateTest(String dt ) {
		assertEquals(divPay.getPayDate(), LocalDate.parse(dt)); 
		
		LocalDate newDate = LocalDate.parse("2015-12-02"); 
		divPay.setPayDate(newDate);
		
		assertEquals(divPay.getPayDate(), newDate); 
	}
	
	@Parameters({"dividendFrequency"})
	@Test(priority = 1)
	public void dividendFrequencyTest(String df ) {
		assertEquals(divPay.getDividendFrequency(), df); 
		
		divPay.setDividendFrequency("Monthly");
		
		assertEquals(divPay.getDividendFrequency(), "Monthly"); 
	}	
	
	
	@Parameters({"id", "stockSymbol", "declaredAmount", "declaredDate", "exDividendDate", "payDate", "dividendFrequency"})
	@BeforeTest
	public void beforeTest(Long id, String stockSymbol, double declaredAmount, String declaredDateStr, String exDividendDateStr, String payDateStr, String dividendFrequency) {
		logger.info("inside DividendAnnouncementTest::beforeTest() ...... ");
		divPay = new DividendAnnouncement(id, stockSymbol, declaredAmount, LocalDate.parse(declaredDateStr), LocalDate.parse(exDividendDateStr), LocalDate.parse(payDateStr), dividendFrequency);
	}
	
	@AfterTest
	public void afterTest() {
		logger.info("...... inside DividendAnnouncementTest::afterTest()");
	}
	
}
