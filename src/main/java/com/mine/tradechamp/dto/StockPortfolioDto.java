package com.mine.tradechamp.dto;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class StockPortfolioDto {

	Long id; 
	Long accountId;
	String stockSymbol;
	String stockName; 
	String productType; // Regular/ETF/DividendETF
	double stockQuantity;
	double currentStockPrice;
	double averageStockPrice;
	double currentMarketCap;
	double originalInvestment;
	String drip; //yes/no/partial
	LocalDate purchaseDate; 
	String dividendFrequency; 
	String comments;
	double totalDividendAmount; 
	double plCurrent; 
	double plWithDividend; 
	double currentYield; 
	
	public StockPortfolioDto() {}

	public StockPortfolioDto(Long id, Long accountId, String stockSymbol, String stockName, String productType,
			double stockQuantity, double currentStockPrice, double averageStockPrice, double currentMarketCap,
			double originalInvestment, String drip, LocalDate purchaseDate, String dividendFrequency, String comments,
			double totalDividendAmount, double plCurrent, double plWithDividend, double currentYield) {
		super();
		this.id = id;
		this.accountId = accountId;
		this.stockSymbol = stockSymbol;
		this.stockName = stockName;
		this.productType = productType;
		this.stockQuantity = stockQuantity;
		this.currentStockPrice = currentStockPrice;
		this.averageStockPrice = averageStockPrice;
		this.currentMarketCap = currentMarketCap;
		this.originalInvestment = originalInvestment;
		this.drip = drip;
		this.purchaseDate = purchaseDate;
		this.dividendFrequency = dividendFrequency;
		this.comments = comments;
		this.totalDividendAmount = totalDividendAmount;
		this.plCurrent = plCurrent;
		this.plWithDividend = plWithDividend;
		this.currentYield = currentYield;
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

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public double getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(double stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public double getCurrentStockPrice() {
		return currentStockPrice;
	}

	public void setCurrentStockPrice(double currentStockPrice) {
		this.currentStockPrice = currentStockPrice;
	}

	public double getAverageStockPrice() {
		return averageStockPrice;
	}

	public void setAverageStockPrice(double averageStockPrice) {
		this.averageStockPrice = averageStockPrice;
	}

	public double getCurrentMarketCap() {
		return currentMarketCap;
	}

	public void setCurrentMarketCap(double currentMarketCap) {
		this.currentMarketCap = currentMarketCap;
	}

	public double getOriginalInvestment() {
		return originalInvestment;
	}

	public void setOriginalInvestment(double originalInvestment) {
		this.originalInvestment = originalInvestment;
	}

	public String getDrip() {
		return drip;
	}

	public void setDrip(String drip) {
		this.drip = drip;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getDividendFrequency() {
		return dividendFrequency;
	}

	public void setDividendFrequency(String dividendFrequency) {
		this.dividendFrequency = dividendFrequency;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public double getTotalDividendAmount() {
		return totalDividendAmount;
	}

	public void setTotalDividendAmount(double totalDividendAmount) {
		this.totalDividendAmount = totalDividendAmount;
	}

	public double getPlCurrent() {
		return plCurrent;
	}

	public void setPlCurrent(double plCurrent) {
		this.plCurrent = plCurrent;
	}

	public double getPlWithDividend() {
		return plWithDividend;
	}

	public void setPlWithDividend(double plWithDividend) {
		this.plWithDividend = plWithDividend;
	}

	public double getCurrentYield() {
		return currentYield;
	}

	public void setCurrentYield(double currentYield) {
		this.currentYield = currentYield;
	}

	@Override
	public String toString() {
		return "StockPortfolioDto [id=" + id + ", accountId=" + accountId + ", stockSymbol=" + stockSymbol + ", stockName="
				+ stockName + ", productType=" + productType + ", stockQuantity=" + stockQuantity
				+ ", currentStockPrice=" + currentStockPrice + ", averageStockPrice=" + averageStockPrice
				+ ", currentMarketCap=" + currentMarketCap + ", originalInvestment=" + originalInvestment + ", drip="
				+ drip + ", purchaseDate=" + purchaseDate + ", dividendFrequency=" + dividendFrequency + ", comments="
				+ comments + ", totalDividendAmount=" + totalDividendAmount + ", plCurrent=" + plCurrent
				+ ", plWithDividend=" + plWithDividend + ", currentYield=" + currentYield + "]";
	}
}
