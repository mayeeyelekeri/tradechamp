package com.mine.tradechamp.dto;

import com.mine.tradechamp.model.DividendPay;

public class DividendPayMapper {

	public static DividendPayDto mapToDividendPayDto(DividendPay dividend) {
		return new DividendPayDto(
				dividend.getId(), 
				dividend.getStock(), 
				dividend.getExDividendDate(), 
				dividend.getPayDate(), 
				dividend.getAmount() 
		); 
	}
	
	public static DividendPay mapToDividendPay(DividendPayDto dividend) {
		return new DividendPay(
				dividend.getId(), 
				dividend.getStock(), 
				dividend.getExDividendDate(), 
				dividend.getPayDate(), 
				dividend.getAmount() 
		); 
	}
}
