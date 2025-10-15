package com.mine.tradechamp.service;

import java.util.List;

import com.mine.tradechamp.dto.DividendPayoutDto;

public interface DividendPayoutService {
	
	DividendPayoutDto createDividendPayout(DividendPayoutDto dividendPayoutDto); 
	
	// get a single record by ID 
	DividendPayoutDto getDividendPayoutById(Long dividendPayoutId); 
	
	// get all records 
	List<DividendPayoutDto> getAllDividendPayoutDtos(); 
	
	// update DividendPay
	DividendPayoutDto updateDividendPayout(Long id, DividendPayoutDto dividendPayout); 
	
	// delete DividendPay
	void deleteDividendPayout(Long id); 		
}
