package com.mine.tradechamp.mapper;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mine.tradechamp.dto.DividendAnnouncementDto;
import com.mine.tradechamp.dto.DividendPayoutDto;
import com.mine.tradechamp.dto.mapper.DividendAnnouncementMapper;
import com.mine.tradechamp.dto.mapper.DividendPayoutMapper;
import com.mine.tradechamp.model.DividendAnnouncement;
import com.mine.tradechamp.model.DividendPayout;

import static org.testng.Assert.assertEquals;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;

public class DividendAnnouncementMapperTest {

	DividendAnnouncementMapper mapper; 
	
	private Logger logger = LoggerFactory.getLogger(DividendAnnouncementMapperTest.class);
	
	@Parameters({"id", "stockSymbol", "declaredAmount", "declaredDate", "exDividendDate", "payDate", "dividendFrequency"})
	@Test(priority = 1)
	public void modelToDtoMapTest(Long id, String stockSymbol, double declaredAmount, String declaredDateStr, String exDividendDateStr, String payDateStr, String dividendFrequency) {
		DividendAnnouncement model = new DividendAnnouncement(id, stockSymbol, declaredAmount, LocalDate.parse(declaredDateStr), LocalDate.parse(exDividendDateStr), LocalDate.parse(payDateStr), dividendFrequency);
		
		DividendAnnouncementDto dto = mapper.mapToDividendAnnouncementDto(model); 
		
		assertEquals(model.getId(), dto.getId());
		assertEquals(model.getStockSymbol(), dto.getStockSymbol()); 
		assertEquals(model.getDeclaredAmount(), dto.getDeclaredAmount()); 
		assertEquals(model.getDeclaredDate(), dto.getDeclaredDate()); 
		assertEquals(model.getExDividendDate(), dto.getExDividendDate()); 
		assertEquals(model.getPayDate(), dto.getPayDate());
		assertEquals(model.getDividendFrequency(), dto.getDividendFrequency()); 
	}

	@Parameters({"id", "stockSymbol", "declaredAmount", "declaredDate", "exDividendDate", "payDate", "dividendFrequency"})
	@Test(priority = 1)
	public void dtoToModelMapTest(Long id, String stockSymbol, double declaredAmount, String declaredDateStr, String exDividendDateStr, String payDateStr, String dividendFrequency) {
		DividendAnnouncementDto dto = new DividendAnnouncementDto(id, stockSymbol, declaredAmount, LocalDate.parse(declaredDateStr), LocalDate.parse(exDividendDateStr), LocalDate.parse(payDateStr), dividendFrequency);
		
		DividendAnnouncement model = mapper.mapToDividendAnnouncement(dto); 
		
		assertEquals(model.getId(), dto.getId());
		assertEquals(model.getStockSymbol(), dto.getStockSymbol()); 
		assertEquals(model.getDeclaredAmount(), dto.getDeclaredAmount()); 
		assertEquals(model.getDeclaredDate(), dto.getDeclaredDate()); 
		assertEquals(model.getExDividendDate(), dto.getExDividendDate()); 
		assertEquals(model.getPayDate(), dto.getPayDate()); 
		assertEquals(model.getDividendFrequency(), dto.getDividendFrequency()); 
	}
	
	@BeforeTest
	public void beforeTest() {
		logger.info("inside DividendAnnouncementMapperTest::beforeTest() ...... ");
		mapper = new DividendAnnouncementMapper(); 
	}
	
	@AfterTest
	public void afterTest() {
		logger.info("...... inside DividendAnnouncementMapperTest::afterTest()");
	}
	
}
