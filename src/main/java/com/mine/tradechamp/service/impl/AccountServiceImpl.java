package com.mine.tradechamp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mine.tradechamp.dto.AccountDto;
import com.mine.tradechamp.dto.mapper.AccountMapper;
import com.mine.tradechamp.exception.ResourceNotFoundException;
import com.mine.tradechamp.model.Account;
import com.mine.tradechamp.repo.AccountRepository;
import com.mine.tradechamp.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository repo; 

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account = AccountMapper.mapToAccount(accountDto); 
		Account savedAccount = repo.save(account); 
		
		return AccountMapper.mapToAccountDto(savedAccount); 
	}

	@Override
	public AccountDto getAccountById(Long accountId) {
		Account account = repo.findById(accountId)
			.orElseThrow(() -> new ResourceNotFoundException("Account doesn't exist with ID : " + accountId)); 
		
		System.out.println(account); 
		
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public List<AccountDto> getAllAccountDtos() {
		// get a list all Accounts
		List<Account> accounts = repo.findAll(); 
				
		// convert all Account records list to Account List using Lambda expression  
		return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
				.collect(Collectors.toList()); 
	}

	@Override
	public AccountDto updateAccount(Long id, AccountDto newAccountDto) {
		Account account = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account doesn't exist with ID : " + id));
		
		account.setAccountId(newAccountDto.getAccountId());
		account.setAccountName(newAccountDto.getAccountName());
		account.setBroker(newAccountDto.getBroker());
		account.setAccountOwner(newAccountDto.getAccountOwner());
		account.setAccountType(newAccountDto.getAccountType());
		account.setOpenedDate(newAccountDto.getOpenedDate());
		
		Account updatedAccount = repo.save(account); 
		
		return AccountMapper.mapToAccountDto(updatedAccount); 
	}

	@Override
	public void deleteAccount(Long id) {
		Account account = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account doesn't exist with ID : " + id));
		
		repo.deleteById(id);		
	}

}
