package com.mine.tradechamp.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class DividendAnnouncementDto {
	Long id; 
	String stockSymbol;
	double declaredAmount;
	LocalDate declaredDate;
	LocalDate exDividendDate;
	LocalDate payDate; 
	String dividendFrequency;

	public DividendAnnouncementDto() {} 
	
	public DividendAnnouncementDto(Long id, String stockSymbol, double declaredAmount, LocalDate declaredDate,
			LocalDate exDividendDate, LocalDate payDate, String dividendFrequency) {
		super();
		this.id = id;
		this.stockSymbol = stockSymbol;
		this.declaredAmount = declaredAmount;
		this.declaredDate = declaredDate;
		this.exDividendDate = exDividendDate;
		this.payDate = payDate;
		this.dividendFrequency = dividendFrequency;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStockSymbol() {
		return stockSymbol;
	}
	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}
	public double getDeclaredAmount() {
		return declaredAmount;
	}
	public void setDeclaredAmount(double declaredAmount) {
		this.declaredAmount = declaredAmount;
	}
	public LocalDate getDeclaredDate() {
		return declaredDate;
	}
	public void setDeclaredDate(LocalDate declaredDate) {
		this.declaredDate = declaredDate;
	}
	public LocalDate getExDividendDate() {
		return exDividendDate;
	}
	public void setExDividendDate(LocalDate exDividendDate) {
		this.exDividendDate = exDividendDate;
	}
	public LocalDate getPayDate() {
		return payDate;
	}
	public void setPayDate(LocalDate payDate) {
		this.payDate = payDate;
	}
	public String getDividendFrequency() {
		return dividendFrequency;
	}
	public void setDividendFrequency(String dividendFrequency) {
		this.dividendFrequency = dividendFrequency;
	}
	@Override
	public String toString() {
		return "DividendAnnouncementDto [id=" + id + ", stockSymbol=" + stockSymbol + ", declaredAmount="
				+ declaredAmount + ", declaredDate=" + declaredDate + ", exDividendDate=" + exDividendDate
				+ ", payDate=" + payDate + ", dividendFrequency=" + dividendFrequency + "]";
	} 	
	
}
