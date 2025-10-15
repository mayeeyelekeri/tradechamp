package com.mine.tradechamp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mine.tradechamp.dto.DividendAnnouncementDto;
import com.mine.tradechamp.dto.DividendPayoutDto;
import com.mine.tradechamp.dto.mapper.DividendAnnouncementMapper;
import com.mine.tradechamp.dto.mapper.DividendPayoutMapper;
import com.mine.tradechamp.exception.ResourceNotFoundException;
import com.mine.tradechamp.model.DividendAnnouncement;
import com.mine.tradechamp.model.DividendPayout;
import com.mine.tradechamp.repo.DividendAnnouncementRepository;
import com.mine.tradechamp.repo.DividendPayoutRepository;
import com.mine.tradechamp.service.DividendAnnouncementService;
import com.mine.tradechamp.service.DividendPayoutService;

import lombok.AllArgsConstructor;

@Service
//@AllArgsConstructor 
public class DividendAnnouncementServiceImpl implements DividendAnnouncementService {

	@Autowired
	private DividendAnnouncementRepository repo; 
	
	@Override
	public DividendAnnouncementDto createDividendAnnouncement(DividendAnnouncementDto dto) { 
		
		System.out.println("... inside DividendAnnouncementServiceImpl::createDividendAnnouncement"); 
		System.out.println(dto); 
		
		DividendAnnouncement dividend = DividendAnnouncementMapper.mapToDividendAnnouncement(dto); 
		DividendAnnouncement savedDividendAnnouncement = repo.save(dividend); 
		
		return DividendAnnouncementMapper.mapToDividendAnnouncementDto(savedDividendAnnouncement); 
	}

	@Override
	public DividendAnnouncementDto getDividendAnnouncementById(Long dividendAnnouncementId) {
		
		System.out.println("... inside DividendAnnouncementServiceImpl::getDividendAnnouncementById, id = " + dividendAnnouncementId); 

		DividendAnnouncement dividendAnnouncement = repo.findById(dividendAnnouncementId)
			.orElseThrow(() -> new ResourceNotFoundException("Dividend Announcement doesn't exist with ID : " + dividendAnnouncementId)); 
		
		System.out.println(dividendAnnouncement); 
		
		return DividendAnnouncementMapper.mapToDividendAnnouncementDto(dividendAnnouncement);
	}

	@Override
	public List<DividendAnnouncementDto> getAllDividendAnnouncementDtos() {
		// get a list all dividend pays 
		List<DividendAnnouncement> dividendAnnouncements = repo.findAll(); 
		
		// convert all DividendPay records list to DividendPay List using Lambda expression  
		return dividendAnnouncements.stream().map((dividend) -> DividendAnnouncementMapper.mapToDividendAnnouncementDto(dividend))
				.collect(Collectors.toList()); 
	}

	@Override
	public DividendAnnouncementDto updateDividendAnnouncement(Long dividendAnnouncementId, DividendAnnouncementDto newDividendAnnouncement) {
		
		DividendAnnouncement dividendAnnouncement = repo.findById(dividendAnnouncementId)
				.orElseThrow(() -> new ResourceNotFoundException("Dividend Pay doesn't exist with ID : " + dividendAnnouncementId));
		
		dividendAnnouncement.setStockSymbol(newDividendAnnouncement.getStockSymbol());
		dividendAnnouncement.setDeclaredAmount(newDividendAnnouncement.getDeclaredAmount());
		dividendAnnouncement.setDeclaredDate(newDividendAnnouncement.getDeclaredDate());
		dividendAnnouncement.setExDividendDate(newDividendAnnouncement.getExDividendDate()); 
		dividendAnnouncement.setPayDate(newDividendAnnouncement.getPayDate());
		dividendAnnouncement.setDividendFrequency(newDividendAnnouncement.getDividendFrequency());
		
		DividendAnnouncement updatedDividendPay = repo.save(dividendAnnouncement); 
		
		return DividendAnnouncementMapper.mapToDividendAnnouncementDto(updatedDividendPay); 
	}

	@Override
	public void deleteDividendAnnouncement(Long dividendAnnouncementId) {
		
		DividendAnnouncement dividendAnnouncement = repo.findById(dividendAnnouncementId)
				.orElseThrow(() -> new ResourceNotFoundException("Dividend Announcement doesn't exist with ID : " + dividendAnnouncementId));
		
		repo.deleteById(dividendAnnouncementId);  
		 
	}
	
	
}
