package com.mine.tradechamp.model;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mine.tradechamp.dto.mapper.DividendPayoutMapper;
import com.mine.tradechamp.model.DividendPayout;

import static org.testng.Assert.assertEquals;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;

public class AccountTest {
	Account account; 
	
	private Logger logger = LoggerFactory.getLogger(Account.class);
	
	@Parameters({"id", "accountId", "accountName", "accountOwner", "broker", "accountType", "openedDate"})
	@Test(priority = 0)
	public void accountConstructorTest(Long id, Long accountId, String accountName, String accountOwner, String broker, String accountType, String openedDateStr) {
		
		LocalDate openedDate = LocalDate.parse(openedDateStr);
		
		assertEquals(account.getId(), id); 
		assertEquals(account.getAccountId(), accountId);
		assertEquals(account.getAccountName(), accountName);
		assertEquals(account.getAccountOwner(), accountOwner); 
		assertEquals(account.getBroker(), broker); 
		assertEquals(account.getAccountType(), accountType); 
		assertEquals(account.getOpenedDate(), openedDate); 
	}

	@Parameters({"id"})
	@Test(priority = 1)
	public void idTest(Long id ) {
		assertEquals(account.getId(), id); 
		
		long myId = 15; 
		account.setId(myId);
		
		assertEquals(account.getId(), myId); 
	}
	
	@Parameters({"accountId"})
	@Test(priority = 1)
	public void accountIdTest(Long id ) {
		assertEquals(account.getAccountId(), id); 
		
		long myId = 15; 
		account.setAccountId(myId);
		
		assertEquals(account.getAccountId(), myId); 
	}
	
	@Parameters({"accountName"})
	@Test(priority = 1)
	public void accountNameTest(String name ) {
		assertEquals(account.getAccountName(), name); 
		
		String myName = "different name"; 
		account.setAccountName(myName);
		
		assertEquals(account.getAccountName(), myName); 
	}
	
	@Parameters({"accountOwner"})
	@Test(priority = 1)
	public void accountOwnerTest(String owner ) {
		assertEquals(account.getAccountOwner(), owner); 
		
		String myOwner = "different owner"; 
		account.setAccountOwner(myOwner);
		
		assertEquals(account.getAccountOwner(), myOwner); 
	}
	
	@Parameters({"broker"})
	@Test(priority = 1)
	public void accountBrokerTest(String broker ) {
		assertEquals(account.getBroker(), broker); 
		
		String myBroker = "Charles Schwab"; 
		account.setBroker(myBroker);
		
		assertEquals(account.getBroker(), myBroker); 
	}
	
	@Parameters({"accountType"})
	@Test(priority = 1)
	public void accountTypeTest(String accountType) {
		assertEquals(account.getAccountType(), accountType); 
		
		String myAccountType = "heavy account"; 
		account.setAccountType(myAccountType);
		
		assertEquals(account.getAccountType(), myAccountType); 
	}
	
	@Parameters({"openedDate"})
	@Test(priority = 1)
	public void accountOpenedDateTest(String openedDateStr) {
		assertEquals(account.getOpenedDate(), LocalDate.parse(openedDateStr)); 
		
		LocalDate newOpenedDate = LocalDate.parse("2006-07-29");
		account.setOpenedDate(newOpenedDate);
		
		assertEquals(account.getOpenedDate(), newOpenedDate); 
	}
	
	@Parameters({"id", "accountId", "accountName", "accountOwner", "broker", "accountType", "openedDate"})
	@BeforeTest
	public void beforeTest(Long id, Long accountId, String accountName, String accountOwner, String broker, String accountType, String openedDateStr) {
		logger.info("inside AccountTest::beforeTest() ...... ");
		account = new Account(id, accountId, accountName, accountOwner, broker, accountType, LocalDate.parse(openedDateStr));
	}
	
	@AfterTest
	public void afterTest() {
		logger.info("...... inside AccountTesting::afterTest()");
	}
	
}
