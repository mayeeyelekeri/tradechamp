package com.mine.tradechamp.model;

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

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor

@Entity
@Table(name = "stock")
public class Stock implements java.io.Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) //this make the ID autoincrement
	@Column(name = "id")
	Long stockId; 
	
	@Column(name = "stock_symbol") 
	String stockSymbol;
	
	@Column(name = "current_stock_price")
	double currentStockPrice; 
	
	@Column(name = "stock_name") 
	String stockName;
	
	@Column(name = "stock_description") 
	String stockDescription;
	
	@Column(name = "stock_type")
	String stockType;
	
	@Column(name = "industry")
	String industry; 
	
	@Column(name = "dividend_yield")
	double dividendYield; 
	
	@Column(name = "dividend_frequency")
	String dividendFrequency; 
	
	@Column(name = "next_dividend_date") 
	LocalDate nextDividendDate; 
	
	@Column(name = "comments")
	String comments; 
	
	@Column(name = "modified_date")
	LocalDate modifiedDate; 
	
	public Stock() {}

	public Stock(Long stockId, String stockSymbol, double currentStockPrice, String stockName, String stockDescription,
			String stockType, String industry, double dividendYield, String dividendFrequency,
			LocalDate nextDividendDate, String comments, LocalDate modifiedDate) {
		super();
		this.stockId = stockId;
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

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long id) {
		this.stockId = id;
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
		return "Stock [id=" + stockId + ", stockSymbol=" + stockSymbol + ", currentStockPrice=" + currentStockPrice
				+ ", stockName=" + stockName + ", stockDescription=" + stockDescription + ", stockType=" + stockType
				+ ", industry=" + industry + ", dividendYield=" + dividendYield + ", dividendFrequency="
				+ dividendFrequency + ", nextDividendDate=" + nextDividendDate + ", comments=" + comments
				+ ", modifiedDate=" + modifiedDate + "]";
	}
	
}
