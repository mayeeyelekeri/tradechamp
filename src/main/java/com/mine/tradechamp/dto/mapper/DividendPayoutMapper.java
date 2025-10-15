package com.mine.tradechamp.dto.mapper;

import com.mine.tradechamp.dto.DividendPayoutDto;
import com.mine.tradechamp.model.DividendPayout;

public class DividendPayoutMapper {

	public static DividendPayoutDto mapToDividendPayoutDto(DividendPayout dividend) {
		return new DividendPayoutDto(
				dividend.getId(), 
				dividend.getAccountId(), 
				dividend.getStockSymbol(),
				dividend.getCurrentStockQuantity(), 
				dividend.getCurrentStockPrice(), 
				dividend.getCurrentYield(),
				dividend.getPayoutAmount(), 
				dividend.getPayoutDate(), 
				dividend.getDividendFrequency()
		); 
	}
	
	public static DividendPayout mapToDividendPayout(DividendPayoutDto dividend) {
		return new DividendPayout(
				dividend.getId(), 
				dividend.getAccountId(), 
				dividend.getStockSymbol(),
				dividend.getCurrentStockQuantity(), 
				dividend.getCurrentStockPrice(), 
				dividend.getCurrentYield(), 
				dividend.getPayoutAmount(), 
				dividend.getPayoutDate(), 
				dividend.getDividendFrequency()
		); 
	}
}
