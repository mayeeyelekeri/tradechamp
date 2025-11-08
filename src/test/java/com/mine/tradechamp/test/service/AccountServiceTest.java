package com.mine.tradechamp.test.service;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.mine.tradechamp.dto.AccountDto;
import com.mine.tradechamp.dto.mapper.AccountMapper;
import com.mine.tradechamp.model.Account;
import com.mine.tradechamp.repo.AccountRepository;
import com.mine.tradechamp.service.AccountService;
import com.mine.tradechamp.service.impl.AccountServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

	@Mock
	AccountRepository repo; 
	
	@InjectMocks 
	private AccountService service = new AccountServiceImpl(); 
	
	Account model;
	//AccountDto dto; 
	
	private Logger logger = LoggerFactory.getLogger(AccountServiceTest.class);
	
	public AccountServiceTest() { 
		model = new Account((long) 1, (long) 2, "MM-401K", "MM", "Hood", "401K", LocalDate.parse("2025-10-31"));
	}
	
	/* @Parameters({"accountName"}) 
	@Test 
	void accountIdTest(String accountName) {
		logger.info("accountID: "+accountName);
		// arrange 
		//when(repo.findByAccountId((long) accountId)).thenReturn(model);
		
		// act 
		//AccountDto dto = service.getAccountById((long)1);
		
		// assert 
		//assertEquals(dto.getAccountId(), model.getAccountId());
		assertEquals(1,1); 
	} */ 
	
	@Test 
	void accountId2Test() { // String accountName) {
		logger.info("........... account : "+model);
		// arrange 
		//when(repo.findByAccountId((long)1)).thenReturn(model);

		logger.info("model: " + model);
		//logger.info("dto: "+ dto);
		
		// act 
		//AccountDto dto = service.getAccountById((long)1);
		//logger.info("dto after: "+ dto);

		// assert 
		//assertEquals(dto.getAccountId(), model.getAccountId());
	}  
	
	@Parameters({"id", "accountId", "accountName", "accountOwner", "broker", "accountType", "openedDate"})
	@BeforeTest(alwaysRun = true)
	public void beforeTest(Long id, Long accountId, String accountName, String accountOwner, String broker, String accountType, String openedDateStr) {
		logger.info("inside AccountTest::beforeTest() ...... ");
		model = new Account(id, accountId, accountName, accountOwner, broker, accountType, LocalDate.parse(openedDateStr));
		//dto = new AccountDto(id, accountId, accountName, accountOwner, broker, accountType, LocalDate.parse(openedDateStr));
	}
}
