package com.mine.tradechamp.mapper;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mine.tradechamp.controller.DividendPayoutControllerWeb;
import com.mine.tradechamp.dto.AccountDto;
import com.mine.tradechamp.dto.mapper.AccountMapper;
import com.mine.tradechamp.model.Account;
import com.mine.tradechamp.model.DividendPayout;

import static org.testng.Assert.assertEquals;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;

public class AccountMapperTest {

	AccountMapper mapper; 
	
	private Logger logger = LoggerFactory.getLogger(AccountMapperTest.class);
	
	@Parameters({"id", "accountId", "accountName", "accountOwner", "broker", "accountType", "openedDate"})
	@Test(priority = 0)
	public void AccountModeltoDtoTest(Long id, Long accountId, String accountName, String accountOwner, String broker, String accountType, String openedDateStr) {
		Account model = new Account(id, accountId, accountName, accountOwner, broker, accountType, LocalDate.parse(openedDateStr));
		
		AccountDto dto = mapper.mapToAccountDto(model); 
		
		assertEquals(model.getId(), dto.getId());
		assertEquals(model.getAccountId(), dto.getAccountId());
		assertEquals(model.getAccountName(), dto.getAccountName()); 
		assertEquals(model.getAccountOwner(), dto.getAccountOwner()); 
		assertEquals(model.getBroker(), dto.getBroker()); 
		assertEquals(model.getAccountType(), dto.getAccountType()); 
		assertEquals(model.getOpenedDate(), dto.getOpenedDate());  
	}

	@Parameters({"id", "accountId", "accountName", "accountOwner", "broker", "accountType", "openedDate"})
	@Test(priority = 0)
	public void AccountDtoToModelTest(Long id, Long accountId, String accountName, String accountOwner, String broker, String accountType, String openedDateStr) {
		AccountDto dto = new AccountDto(id, accountId, accountName, accountOwner, broker, accountType, LocalDate.parse(openedDateStr));
		
		Account model = mapper.mapToAccount(dto); 
		
		assertEquals(model.getId(), dto.getId());
		assertEquals(model.getAccountId(), dto.getAccountId());
		assertEquals(model.getAccountName(), dto.getAccountName()); 
		assertEquals(model.getAccountOwner(), dto.getAccountOwner()); 
		assertEquals(model.getBroker(), dto.getBroker()); 
		assertEquals(model.getAccountType(), dto.getAccountType()); 
		assertEquals(model.getOpenedDate(), dto.getOpenedDate()); 
	}
	
	@BeforeTest
	public void beforeTest() {
		logger.info("inside AccountMapperTest::beforeTest() ...... ");
		mapper = new AccountMapper(); 
		
		//divPay = new DividendPay(id, stock, LocalDate.parse(exDividendDateStr), LocalDate.parse(payDateStr), amount);
		//divPayDto = new DividendPayDto(id, stock, LocalDate.parse(exDividendDateStr), LocalDate.parse(payDateStr), amount); 
	}
	
	@AfterTest
	public void afterTest() {
		logger.info("...... inside AccountMapperTest::afterTest()");
	}
	
}
