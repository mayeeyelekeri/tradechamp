package com.mine.tradechamp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mine.tradechamp.dto.AccountDto;
import com.mine.tradechamp.service.AccountService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@RestController 
@RequestMapping("/api/accounts")
public class AccountController {

	@Autowired
	private AccountService service; 
	
	@PostMapping 
	public ResponseEntity<AccountDto> createDividendPay(@RequestBody AccountDto dto) {
		
		System.out.println("inside AccountController::createAccount()"); 
		AccountDto savedAccountDto = service.createAccount(dto); 
		
		return new ResponseEntity<>(savedAccountDto, HttpStatus.CREATED); 
	}
	
	@GetMapping("{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable("id") Long id) { 
		AccountDto dividendPayDto = service.getAccountById(id); 
		return ResponseEntity.ok(dividendPayDto); 
	}
	
	// Get all Accounts 
	@GetMapping
	public ResponseEntity<List<AccountDto>> getAllAccounts() { 
		List<AccountDto> dividendPayDtos = service.getAllAccountDtos(); 
		return ResponseEntity.ok(dividendPayDtos); 
	}
	
	// Update Account  
	@PutMapping("{id}")
	public ResponseEntity<AccountDto> updateAccount(@PathVariable("id") Long id, AccountDto newAccountDto) { 
		AccountDto accountDto = service.updateAccount(id, newAccountDto);  
		return ResponseEntity.ok(accountDto); 
	}
	
	// Delete account 
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable("id") Long id) { 
		service.deleteAccount(id);  
		return ResponseEntity.ok("Account record deleted successfully"); 
	}
	
}
