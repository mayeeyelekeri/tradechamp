package com.mine.tradechamp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mine.tradechamp.dto.DividendPayDto;
import com.mine.tradechamp.dto.DividendPayMapper;
import com.mine.tradechamp.exception.ResourceNotFoundException;
import com.mine.tradechamp.model.DividendPay;
import com.mine.tradechamp.repo.DividendPayRepository;
import com.mine.tradechamp.service.DividendPayService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor 
public class DividendPayServiceImpl implements DividendPayService {

	@Autowired
	private DividendPayRepository repo; 
	
	@Override
	public DividendPayDto createDividendPay(DividendPayDto dto) { 
		
		System.out.println("... inside DividendPayServiceImpl::createDividendPay"); 
		System.out.println(dto); 
		
		DividendPay dividend = DividendPayMapper.mapToDividendPay(dto); 
		DividendPay savedDividendPay = repo.save(dividend); 
		
		return DividendPayMapper.mapToDividendPayDto(savedDividendPay); 
	}

	@Override
	public DividendPayDto getDividendPayById(Long dividendPayId) {
		
		System.out.println("... inside DividendPayServiceImpl::getDividendPayById, id = " + dividendPayId); 

		DividendPay dividendPay = repo.findById(dividendPayId)
			.orElseThrow(() -> new ResourceNotFoundException("Dividend Pay doesn't exist with ID : " + dividendPayId)); 
		
		System.out.println(dividendPay); 
		
		return DividendPayMapper.mapToDividendPayDto(dividendPay);
	}

	@Override
	public List<DividendPayDto> getAllDividendPayDtos() {
		// get a list all dividend pays 
		List<DividendPay> dividendPays = repo.findAll(); 
		
		// convert all DividendPay records list to DividendPay List using Lambda expression  
		return dividendPays.stream().map((dividend) -> DividendPayMapper.mapToDividendPayDto(dividend))
				.collect(Collectors.toList()); 
	}

	@Override
	public DividendPayDto updateDividendPay(Long dividendPayId, DividendPayDto newDividendPay) {
		
		DividendPay dividendPay = repo.findById(dividendPayId)
				.orElseThrow(() -> new ResourceNotFoundException("Dividend Pay doesn't exist with ID : " + dividendPayId));
		
		dividendPay.setStock(newDividendPay.getStock());
		dividendPay.setAmount(newDividendPay.getAmount());
		dividendPay.setExDividendDate(newDividendPay.getExDividendDate()); 
		dividendPay.setPayDate(newDividendPay.getPayDate());
		
		DividendPay updatedDividendPay = repo.save(dividendPay); 
		
		return DividendPayMapper.mapToDividendPayDto(updatedDividendPay); 
	}

	@Override
	public void deleteDividendPay(Long dividendPayId) {
		
		DividendPay dividendPay = repo.findById(dividendPayId)
				.orElseThrow(() -> new ResourceNotFoundException("Dividend Pay doesn't exist with ID : " + dividendPayId));
		
		repo.deleteById(dividendPayId);  
		 
	}
	
	
}
