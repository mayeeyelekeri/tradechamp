package com.mine.tradechamp.dto.mapper;

import com.mine.tradechamp.dto.DividendAnnouncementDto;
import com.mine.tradechamp.dto.DividendPayoutDto;
import com.mine.tradechamp.model.DividendAnnouncement;
import com.mine.tradechamp.model.DividendPayout;

public class DividendAnnouncementMapper {

	public static DividendAnnouncementDto mapToDividendAnnouncementDto(DividendAnnouncement dividend) {
		return new DividendAnnouncementDto(
				dividend.getId(), 
				dividend.getStockSymbol(), 
				dividend.getDeclaredAmount(), 
				dividend.getDeclaredDate(), 
				dividend.getExDividendDate(), 
				dividend.getPayDate(),
				dividend.getDividendFrequency()
		); 
	}
	
	public static DividendAnnouncement mapToDividendAnnouncement(DividendAnnouncementDto dividend) {
		return new DividendAnnouncement(
				dividend.getId(), 
				dividend.getStockSymbol(), 
				dividend.getDeclaredAmount(), 
				dividend.getDeclaredDate(), 
				dividend.getExDividendDate(), 
				dividend.getPayDate(), 
				dividend.getDividendFrequency()
		); 
	}
}
