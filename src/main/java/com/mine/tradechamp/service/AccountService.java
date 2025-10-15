package com.mine.tradechamp.service;

import java.util.List;

import com.mine.tradechamp.dto.AccountDto;

public interface AccountService {
	
	AccountDto createAccount(AccountDto accountDto); 
	
	// get a single record by ID 
	AccountDto getAccountById(Long accountId); 
	
	// get all records 
	List<AccountDto> getAllAccountDtos(); 
	
	// update DividendPay
	AccountDto updateAccount(Long id, AccountDto accountDto); 
	
	// delete DividendPay
	void deleteAccount(Long id); 		
}
