package com.mine.tradechamp.service;

import java.util.List;

import com.mine.tradechamp.dto.DividendAnnouncementDto;

public interface DividendAnnouncementService {
	
	DividendAnnouncementDto createDividendAnnouncement(DividendAnnouncementDto dividendAnnouncementDto); 
	
	// get a single record by ID 
	DividendAnnouncementDto getDividendAnnouncementById(Long dividendAnnouncementId); 
	
	// get all records 
	List<DividendAnnouncementDto> getAllDividendAnnouncementDtos(); 
	
	// update DividendPay
	DividendAnnouncementDto updateDividendAnnouncement(Long id, DividendAnnouncementDto dividendAnnouncement); 
	
	// delete DividendPay
	void deleteDividendAnnouncement(Long id); 		
}
