package com.mine.tradechamp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mine.tradechamp.dto.DividendAnnouncementDto;
import com.mine.tradechamp.exception.ResourceNotFoundException;
import com.mine.tradechamp.model.DividendAnnouncement;
import com.mine.tradechamp.model.Stock;
import com.mine.tradechamp.repo.DividendAnnouncementRepository;
import com.mine.tradechamp.service.DividendAnnouncementService;
import com.mine.tradechamp.service.SharedService;

@Service
//@AllArgsConstructor 
public class DividendAnnouncementServiceImpl implements DividendAnnouncementService {

	@Autowired
	private DividendAnnouncementRepository repo; 
	
	@Autowired 
	private SharedService sharedService; 
	
	@Override
	public DividendAnnouncementDto createDividendAnnouncement(DividendAnnouncementDto dto) { 
		
		System.out.println("... inside DividendAnnouncementServiceImpl::createDividendAnnouncement"); 
		System.out.println(dto); 
		
		DividendAnnouncement dividend = mapToDividendAnnouncement(dto); 
		DividendAnnouncement savedDividendAnnouncement = repo.save(dividend); 
		
		return mapToDividendAnnouncementDto(savedDividendAnnouncement); 
	}

	@Override
	public DividendAnnouncementDto getDividendAnnouncementById(Long dividendAnnouncementId) {
		
		System.out.println("... inside DividendAnnouncementServiceImpl::getDividendAnnouncementById, id = " + dividendAnnouncementId); 

		DividendAnnouncement dividendAnnouncement = repo.findById(dividendAnnouncementId)
			.orElseThrow(() -> new ResourceNotFoundException("Dividend Announcement doesn't exist with ID : " + dividendAnnouncementId)); 
		
		System.out.println(dividendAnnouncement); 
		
		return mapToDividendAnnouncementDto(dividendAnnouncement);
	}

	@Override
	public List<DividendAnnouncementDto> getAllDividendAnnouncementDtos() {
		// get a list all dividend pays 
		List<DividendAnnouncement> dividendAnnouncements = repo.findAll(); 
		
		// convert all DividendPay records list to DividendPay List using Lambda expression  
		return dividendAnnouncements.stream().map((dividend) -> mapToDividendAnnouncementDto(dividend))
				.collect(Collectors.toList()); 
	}

	@Override
	public DividendAnnouncementDto updateDividendAnnouncement(Long id, DividendAnnouncementDto dto) {
		
		DividendAnnouncement newDiv = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Dividend Pay doesn't exist with ID : " + id));
		
		newDiv.setStock(sharedService.getStockByStockSymbol(dto.getStockSymbol()));
		newDiv.setDeclaredAmount(dto.getDeclaredAmount());
		newDiv.setDeclaredDate(dto.getDeclaredDate());
		newDiv.setExDividendDate(dto.getExDividendDate()); 
		newDiv.setPayDate(dto.getPayDate());
		newDiv.setDividendFrequency(dto.getDividendFrequency());
		
		DividendAnnouncement updatedDividendPay = repo.save(newDiv); 
		
		return mapToDividendAnnouncementDto(updatedDividendPay); 
	}

	@Override
	public void deleteDividendAnnouncement(Long dividendAnnouncementId) {
		
		DividendAnnouncement dividendAnnouncement = repo.findById(dividendAnnouncementId)
				.orElseThrow(() -> new ResourceNotFoundException("Dividend Announcement doesn't exist with ID : " + dividendAnnouncementId));
		
		repo.deleteById(dividendAnnouncementId);  
		 
	}
	
	// convert from Database model to Dto 
	public DividendAnnouncementDto mapToDividendAnnouncementDto(DividendAnnouncement model) {
		
		DividendAnnouncementDto dto = new DividendAnnouncementDto(
				model.getId(), 
				model.getStock().getStockSymbol(), 
				model.getDeclaredAmount(), 
				model.getDeclaredDate(), 
				model.getExDividendDate(), 
				model.getPayDate(),
				model.getDividendFrequency()
		); 
		
		return dto; 
	}
	
	// convert from dto to Database record (DividendAnnouncement) 
	public DividendAnnouncement mapToDividendAnnouncement(DividendAnnouncementDto dto) {
		Stock stock = sharedService.getStockByStockSymbol(dto.getStockSymbol()); 
				 
		DividendAnnouncement ann =  new DividendAnnouncement(
				dto.getId(), 
				stock,
				dto.getDeclaredAmount(), 
				dto.getDeclaredDate(), 
				dto.getExDividendDate(), 
				dto.getPayDate(), 
				dto.getDividendFrequency()
		);
		
		return ann; 
	}
}
