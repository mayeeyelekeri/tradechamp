package com.mine.tradechamp.dto.mapper;

import com.mine.tradechamp.dto.AccountDto;
import com.mine.tradechamp.model.Account;

public class AccountMapper {

	public static AccountDto mapToAccountDto(Account model) {
		return new AccountDto(
				model.getId(),
				model.getAccountId(), 
				model.getAccountName(), 
				model.getAccountOwner(), 
				model.getBroker(), 
				model.getAccountType(), 
				model.getOpenedDate()
		); 
	}
	
	public static Account mapToAccount(AccountDto dto) {
		return new Account(
				dto.getId(),
				dto.getAccountId(), 
				dto.getAccountName(), 
				dto.getAccountOwner(), 
				dto.getBroker(), 
				dto.getAccountType(), 
				dto.getOpenedDate()
		); 
	}
}
