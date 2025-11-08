package com.mine.tradechamp.dto;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class StockDto {

	Long id; 
	String stockSymbol;
	double currentStockPrice; 
	String stockName;
	String stockDescription;
	String stockType;
	String industry; 
	double dividendYield; 
	String dividendFrequency; 
	LocalDate nextDividendDate; 
	String comments; 
	LocalDate modifiedDate; 
	
	public StockDto() {}

	public StockDto(Long id, String stockSymbol, double currentStockPrice, String stockName, String stockDescription,
			String stockType, String industry, double dividendYield, String dividendFrequency,
			LocalDate nextDividendDate, String comments, LocalDate modifiedDate) {
		super();
		this.id = id;
		this.stockSymbol = stockSymbol;
		this.currentStockPrice = currentStockPrice;
		this.stockName = stockName;
		this.stockDescription = stockDescription;
		this.stockType = stockType;
		this.industry = industry;
		this.dividendYield = dividendYield;
		this.dividendFrequency = dividendFrequency;
		this.nextDividendDate = nextDividendDate;
		this.comments = comments;
		this.modifiedDate = modifiedDate;
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

	public double getCurrentStockPrice() {
		return currentStockPrice;
	}

	public void setCurrentStockPrice(double currentStockPrice) {
		this.currentStockPrice = currentStockPrice;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public String getStockDescription() {
		return stockDescription;
	}

	public void setStockDescription(String stockDescription) {
		this.stockDescription = stockDescription;
	}

	public String getStockType() {
		return stockType;
	}

	public void setStockType(String stockType) {
		this.stockType = stockType;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public double getDividendYield() {
		return dividendYield;
	}

	public void setDividendYield(double dividendYield) {
		this.dividendYield = dividendYield;
	}

	public String getDividendFrequency() {
		return dividendFrequency;
	}

	public void setDividendFrequency(String dividendFrequency) {
		this.dividendFrequency = dividendFrequency;
	}

	public LocalDate getNextDividendDate() {
		return nextDividendDate;
	}

	public void setNextDividendDate(LocalDate nextDividendDate) {
		this.nextDividendDate = nextDividendDate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public LocalDate getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString() {
		return "StockDto [id=" + id + ", stockSymbol=" + stockSymbol + ", currentStockPrice=" + currentStockPrice
				+ ", stockName=" + stockName + ", stockDescription=" + stockDescription + ", stockType=" + stockType
				+ ", industry=" + industry + ", dividendYield=" + dividendYield + ", dividendFrequency="
				+ dividendFrequency + ", nextDividendDate=" + nextDividendDate + ", comments=" + comments
				+ ", modifiedDate=" + modifiedDate + "]";
	}
}
