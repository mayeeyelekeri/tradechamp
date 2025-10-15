package com.mine.tradechamp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mine.tradechamp.dto.DividendPayoutDto;
import com.mine.tradechamp.dto.mapper.DividendPayoutMapper;
import com.mine.tradechamp.exception.ResourceNotFoundException;
import com.mine.tradechamp.model.DividendPayout;
import com.mine.tradechamp.repo.DividendPayoutRepository;
import com.mine.tradechamp.service.DividendPayoutService;

import lombok.AllArgsConstructor;

@Service
//@AllArgsConstructor 
public class DividendPayoutServiceImpl implements DividendPayoutService {

	@Autowired
	private DividendPayoutRepository repo; 
	
	@Override
	public DividendPayoutDto createDividendPayout(DividendPayoutDto dto) { 
		
		System.out.println("... inside DividendPayServiceImpl::createDividendPay"); 
		System.out.println(dto); 
		
		DividendPayout dividend = DividendPayoutMapper.mapToDividendPayout(dto); 
		DividendPayout savedDividendPay = repo.save(dividend); 
		
		return DividendPayoutMapper.mapToDividendPayoutDto(savedDividendPay); 
	}

	@Override
	public DividendPayoutDto getDividendPayoutById(Long dividendPayId) {
		
		System.out.println("... inside DividendPayServiceImpl::getDividendPayById, id = " + dividendPayId); 

		DividendPayout dividendPay = repo.findById(dividendPayId)
			.orElseThrow(() -> new ResourceNotFoundException("Dividend Pay doesn't exist with ID : " + dividendPayId)); 
		
		System.out.println(dividendPay); 
		
		return DividendPayoutMapper.mapToDividendPayoutDto(dividendPay);
	}

	@Override
	public List<DividendPayoutDto> getAllDividendPayoutDtos() {
		// get a list all dividend pays 
		List<DividendPayout> dividendPays = repo.findAll(); 
		
		// convert all DividendPay records list to DividendPay List using Lambda expression  
		return dividendPays.stream().map((dividend) -> DividendPayoutMapper.mapToDividendPayoutDto(dividend))
				.collect(Collectors.toList()); 
	}

	@Override
	public DividendPayoutDto updateDividendPayout(Long dividendPayId, DividendPayoutDto newDividendPay) {
		
		DividendPayout dividendPay = repo.findById(dividendPayId)
				.orElseThrow(() -> new ResourceNotFoundException("Dividend Pay doesn't exist with ID : " + dividendPayId));
		
		dividendPay.setAccountId(newDividendPay.getAccountId());
		dividendPay.setStockSymbol(newDividendPay.getStockSymbol());
		dividendPay.setPayoutAmount(newDividendPay.getPayoutAmount());
		dividendPay.setCurrentStockQuantity(newDividendPay.getCurrentStockQuantity());
		dividendPay.setCurrentStockPrice(newDividendPay.getCurrentStockPrice());
		dividendPay.setCurrentYield(newDividendPay.getCurrentYield()); 
		dividendPay.setPayoutDate(newDividendPay.getPayoutDate());
		
		DividendPayout updatedDividendPay = repo.save(dividendPay); 
		
		return DividendPayoutMapper.mapToDividendPayoutDto(updatedDividendPay); 
	}

	@Override
	public void deleteDividendPayout(Long dividendPayId) {
		
		DividendPayout dividendPay = repo.findById(dividendPayId)
				.orElseThrow(() -> new ResourceNotFoundException("Dividend Payout doesn't exist with ID : " + dividendPayId));
		
		repo.deleteById(dividendPayId);  
		 
	}
	
	
}
