package com.mine.tradechamp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mine.tradechamp.dto.DividendPayoutDto;
import com.mine.tradechamp.exception.ResourceNotFoundException;
import com.mine.tradechamp.model.Account;
import com.mine.tradechamp.model.DividendPayout;
import com.mine.tradechamp.model.Stock;
import com.mine.tradechamp.repo.AccountRepository;
import com.mine.tradechamp.repo.DividendPayoutRepository;
import com.mine.tradechamp.repo.StockRepository;
import com.mine.tradechamp.service.DividendPayoutService;
import com.mine.tradechamp.service.SharedService;

@Service
//@AllArgsConstructor 
public class DividendPayoutServiceImpl implements DividendPayoutService {

	@Autowired
	private DividendPayoutRepository repo; 
	
	@Autowired
	private StockRepository stockRepo;
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired 
	private SharedService sharedService; 
	
	@Override
	public DividendPayoutDto createDividendPayout(DividendPayoutDto dto) { 
		
		System.out.println("... inside DividendPayServiceImpl::createDividendPay"); 
		System.out.println(dto); 
		
		DividendPayout payout = mapToDividendPayout(dto); 
		DividendPayout savedDividendPay = repo.save(payout); 
		
		return mapToDividendPayoutDto(savedDividendPay); 
	}

	@Override
	public DividendPayoutDto getDividendPayoutById(Long dividendPayId) {
		
		System.out.println("... inside DividendPayServiceImpl::getDividendPayById, id = " + dividendPayId); 

		DividendPayout dividendPay = repo.findById(dividendPayId)
			.orElseThrow(() -> new ResourceNotFoundException("Dividend Pay doesn't exist with ID : " + dividendPayId)); 
		
		System.out.println(dividendPay); 
		
		return mapToDividendPayoutDto(dividendPay);
	}

	@Override
	public List<DividendPayoutDto> getAllDividendPayoutDtos() {
		// get a list all dividend pays 
		List<DividendPayout> dividendPays = repo.findAll(); 
		
		// convert all DividendPay records list to DividendPay List using Lambda expression  
		return dividendPays.stream().map((dividend) -> mapToDividendPayoutDto(dividend))
				.collect(Collectors.toList()); 
	}

	@Override
	public DividendPayoutDto updateDividendPayout(Long dividendPayId, DividendPayoutDto dto) {
		
		DividendPayout dividendPay = repo.findById(dividendPayId)
				.orElseThrow(() -> new ResourceNotFoundException("Dividend Pay doesn't exist with ID : " + dividendPayId));
		
		Stock stock = stockRepo.findByStockSymbol(dto.getStockSymbol()); 
		Account account = accountRepo.findById(dto.getAccountId())
				.orElseThrow(() -> new ResourceNotFoundException("Account doesn't exist with ID : " + dto.getAccountId())); 
			
		
		dividendPay.setAccount(account);
		dividendPay.setStock(stock);
		dividendPay.setPayoutAmount(dto.getPayoutAmount());
		dividendPay.setCurrentStockQuantity(dto.getCurrentStockQuantity());
		dividendPay.setCurrentStockPrice(dto.getCurrentStockPrice());
		dividendPay.setCurrentYield(dto.getCurrentYield()); 
		dividendPay.setPayoutDate(dto.getPayoutDate());
		
		DividendPayout updatedDividendPay = repo.save(dividendPay); 
		
		return mapToDividendPayoutDto(updatedDividendPay); 
	}

	@Override
	public void deleteDividendPayout(Long dividendPayId) {
		
		DividendPayout dividendPay = repo.findById(dividendPayId)
				.orElseThrow(() -> new ResourceNotFoundException("Dividend Payout doesn't exist with ID : " + dividendPayId));
		
		repo.deleteById(dividendPayId);  
		 
	}
	
	// Convert from payout to dto with additional information 
	public DividendPayoutDto mapToDividendPayoutDto(DividendPayout dividend) {
		
		DividendPayoutDto dto = new DividendPayoutDto(
				dividend.getId(), 
				dividend.getAccount().getAccountId(), 
				dividend.getStock().getStockSymbol(),
				dividend.getCurrentStockQuantity(), 
				dividend.getCurrentStockPrice(), 
				dividend.getCurrentYield(),
				dividend.getPayoutAmount(), 
				dividend.getPayoutDate(), 
				dividend.getDividendFrequency()
		); 
		
		return dto; 
	}
	
	public DividendPayout mapToDividendPayout(DividendPayoutDto dto) {
		Stock stock = sharedService.getStockByStockSymbol(dto.getStockSymbol()); 
		Account account = sharedService.getAccountByAccountId(dto.getAccountId());
			// .orElseThrow(() -> new ResourceNotFoundException("Account doesn't exist with ID : " + dto.getAccountId())); 
		
		
		DividendPayout payout = new DividendPayout(
				dto.getId(), 
				account,  
				stock,
				dto.getCurrentStockQuantity(), 
				dto.getCurrentStockPrice(), 
				dto.getCurrentYield(), 
				dto.getPayoutAmount(), 
				dto.getPayoutDate(), 
				dto.getDividendFrequency()
		); 
		
		return payout; 
	}
	
	
}
