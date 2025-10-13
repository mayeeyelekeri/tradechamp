package com.mine.tradechamp.service;

import java.util.List;

import com.mine.tradechamp.dto.DividendPayDto;

public interface DividendPayService {
	
	DividendPayDto createDividendPay(DividendPayDto dividendPayDto); 
	
	// get a single record by ID 
	DividendPayDto getDividendPayById(Long dividendPayId); 
	
	// get all records 
	List<DividendPayDto> getAllDividendPayDtos(); 
	
	// update DividendPay
	DividendPayDto updateDividendPay(Long id, DividendPayDto dividendPay); 
	
	// delete DividendPay
	void deleteDividendPay(Long id); 		
}
