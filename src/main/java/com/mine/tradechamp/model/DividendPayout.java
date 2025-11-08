package com.mine.tradechamp.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "dividendpayout")
public class DividendPayout {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //this make the ID autoincrement
	@Column(name = "id")
	Long id; 
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ACCOUNT", referencedColumnName = "ID")
	Account account;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "STOCK", referencedColumnName = "ID")
	Stock stock;
	
	@Column
	double currentStockQuantity;
	
	@Column
	double currentStockPrice;
	
	@Column
	double currentYield;
	
	@Column
	double payoutAmount;
	
	@Column
	LocalDate payoutDate; 
	
	@Column 
	String dividendFrequency; 

	public DividendPayout() {}

	public DividendPayout(Long id, Account account, Stock stock, double currentStockQuantity,
			double currentStockPrice, double currentYield, double payoutAmount, LocalDate payoutDate, 
			String dividendFrequency) {
		super();
		this.id = id;
		this.account = account;
		this.stock = stock;
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
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
		return "DividendPayout [id=" + id + ", account=" + account + ", stock=" + stock
				+ ", currentStockQuantity=" + currentStockQuantity + ", currentStockPrice=" + currentStockPrice
				+ ", currentYield=" + currentYield + ", payoutAmount=" + payoutAmount + ", payoutDate=" + payoutDate
				+ ", dividendFrequency=" + dividendFrequency + "]";
	}			
}
