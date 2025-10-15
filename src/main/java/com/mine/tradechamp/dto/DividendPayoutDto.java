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
public class DividendPayoutDto {
	Long id; 
	Long accountId; 
	String stockSymbol;
	double currentStockQuantity; 
	double currentStockPrice; 
	double currentYield;
	double payoutAmount;
	LocalDate payoutDate;
	String dividendFrequency; 
			
	public DividendPayoutDto() {}

	public DividendPayoutDto(Long id, Long accountId, String stockSymbol, double currentStockQuantity,
			double currentStockPrice, double currentYield, double payoutAmount, LocalDate payoutDate, 
			String dividendFrequency) {
		super();
		this.id = id;
		this.accountId = accountId;
		this.stockSymbol = stockSymbol;
		this.currentStockQuantity = currentStockQuantity;
		this.currentStockPrice = currentStockPrice;
		this.currentYield = currentYield;
		this.payoutAmount = payoutAmount;
		this.payoutDate = payoutDate;
		this.dividendFrequency = dividendFrequency; 
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public double getCurrentStockQuantity() {
		return currentStockQuantity;
	}

	public void setCurrentStockQuantity(double currentStockQuantity) {
		this.currentStockQuantity = currentStockQuantity;
	}

	public double getCurrentStockPrice() {
		return currentStockPrice;
	}

	public void setCurrentStockPrice(double currentStockPrice) {
		this.currentStockPrice = currentStockPrice;
	}

	public double getCurrentYield() {
		return currentYield;
	}

	public void setCurrentYield(double currentYield) {
		this.currentYield = currentYield;
	}

	public double getPayoutAmount() {
		return payoutAmount;
	}

	public void setPayoutAmount(double payoutAmount) {
		this.payoutAmount = payoutAmount;
	}

	public LocalDate getPayoutDate() {
		return payoutDate;
	}

	public void setPayoutDate(LocalDate payoutDate) {
		this.payoutDate = payoutDate;
	}

	
	public String getDividendFrequency() {
		return dividendFrequency;
	}

	public void setDividendFrequency(String dividendFrequency) {
		this.dividendFrequency = dividendFrequency;
	}

	@Override
	public String toString() {
		return "DividendPayoutDto [id=" + id + ", accountId=" + accountId + ", stockSymbol=" + stockSymbol
				+ ", currentStockQuantity=" + currentStockQuantity + ", currentStockPrice=" + currentStockPrice
				+ ", currentYield=" + currentYield + ", payoutAmount=" + payoutAmount + ", payoutDate=" + payoutDate
				+ ", dividendFrequency=" + dividendFrequency + "]";
	}		
}
