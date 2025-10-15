package com.mine.tradechamp.mapper;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mine.tradechamp.dto.DividendPayoutDto;
import com.mine.tradechamp.dto.mapper.DividendPayoutMapper;
import com.mine.tradechamp.model.DividendPayout;

import static org.testng.Assert.assertEquals;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;

public class DividendPayoutMapperTest {

	DividendPayoutMapper mapper; 
	
	private Logger logger = LoggerFactory.getLogger(DividendPayoutMapper.class);
	
	@Parameters({"id", "accountId", "stockSymbol", "currentStockQuantity", "currentStockPrice", "currentYield","payoutAmount", "payoutDate", "dividendFrequency"})
	@Test(priority = 1)
	public void modelToDtoMapTest(Long id, Long accountId, String stockSymbol, double currentStockQuantity, double currentStockPrice, double currentYield, double payoutAmount, String payoutDateStr, String dividendFrequency) {
		DividendPayout model = new DividendPayout(id, accountId, stockSymbol, currentStockQuantity, currentStockPrice, currentYield, payoutAmount,  LocalDate.parse(payoutDateStr), dividendFrequency);
		
		DividendPayoutDto dto = mapper.mapToDividendPayoutDto(model); 
		
		assertEquals(model.getId(), dto.getId());
		assertEquals(model.getAccountId(), dto.getAccountId()); 
		assertEquals(model.getStockSymbol(), dto.getStockSymbol()); 
		assertEquals(model.getCurrentStockQuantity(), dto.getCurrentStockQuantity()); 
		assertEquals(model.getCurrentStockPrice(), dto.getCurrentStockPrice()); 
		assertEquals(model.getCurrentYield(), dto.getCurrentYield()); 
		assertEquals(model.getPayoutAmount(), dto.getPayoutAmount()); 
		assertEquals(model.getPayoutDate(), dto.getPayoutDate());
		assertEquals(model.getDividendFrequency(), dto.getDividendFrequency()); 
	}

	@Parameters({"id", "accountId", "stockSymbol", "currentStockQuantity", "currentStockPrice", "currentYield","payoutAmount", "payoutDate", "dividendFrequency"})
	@Test(priority = 1)
	public void dtoToModelMapTest(Long id, Long accountId, String stockSymbol, double currentStockQuantity, double currentStockPrice, double currentYield, double payoutAmount, String payoutDateStr, String dividendFrequency) {
		DividendPayoutDto model = new DividendPayoutDto(id, accountId, stockSymbol, currentStockQuantity, currentStockPrice, currentYield, payoutAmount,  LocalDate.parse(payoutDateStr), dividendFrequency);
		
		DividendPayout dto = mapper.mapToDividendPayout(model); 
		
		assertEquals(model.getId(), dto.getId());
		assertEquals(model.getAccountId(), dto.getAccountId()); 
		assertEquals(model.getStockSymbol(), dto.getStockSymbol()); 
		assertEquals(model.getCurrentStockQuantity(), dto.getCurrentStockQuantity()); 
		assertEquals(model.getCurrentStockPrice(), dto.getCurrentStockPrice()); 
		assertEquals(model.getCurrentYield(), dto.getCurrentYield()); 
		assertEquals(model.getPayoutAmount(), dto.getPayoutAmount()); 
		assertEquals(model.getPayoutDate(), dto.getPayoutDate()); 
		assertEquals(model.getDividendFrequency(), dto.getDividendFrequency()); 
	}
	
	@BeforeTest
	public void beforeTest() {
		logger.info("inside DividendPayMapperTest::beforeTest() ...... ");
		mapper = new DividendPayoutMapper(); 
		
		//divPay = new DividendPay(id, stock, LocalDate.parse(exDividendDateStr), LocalDate.parse(payDateStr), amount);
		//divPayDto = new DividendPayDto(id, stock, LocalDate.parse(exDividendDateStr), LocalDate.parse(payDateStr), amount); 
	}
	
	@AfterTest
	public void afterTest() {
		logger.info("...... inside DividendPayMapperTest::afterTest()");
	}
	
}
