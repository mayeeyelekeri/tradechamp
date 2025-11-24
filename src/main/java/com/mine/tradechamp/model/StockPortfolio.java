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
import jakarta.persistence.OneToOne;
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
@Table(name = "stockportfolio")
public class StockPortfolio {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) // this make the ID auto increment
	Long id; 
	 
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ACCOUNT", referencedColumnName = "ID")
	Account account;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "STOCK", referencedColumnName = "ID")
	Stock stock;

	@Column(name = "stock_quantity")
	double stockQuantity;
	
	@Column(name = "average_stock_price")
	double averageStockPrice;
	
	@Column(name = "original_investment")
	double originalInvestment;
	
	@Column(name = "drip")
	String drip; // yes/no/partial
	
	@Column(name = "purchase_date")
	LocalDate purchaseDate; 
		
	@Column(name = "comments")
	String comments;
	
	@Column(name = "total_dividend_amount")
	double totalDividendAmount; 
	
	public StockPortfolio() {}

	public StockPortfolio(Long id, Account account, Stock stock, double stockQuantity, double averageStockPrice,
			double originalInvestment, String drip, LocalDate purchaseDate, String comments,
			double totalDividendAmount) {
		super();
		this.id = id;
		this.account = account;
		this.stock = stock;
		this.stockQuantity = stockQuantity;
		this.averageStockPrice = averageStockPrice;
		this.originalInvestment = originalInvestment;
		this.drip = drip;
		this.purchaseDate = purchaseDate;
		this.comments = comments;
		this.totalDividendAmount = totalDividendAmount;
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

	public double getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(double stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public double getAverageStockPrice() {
		return averageStockPrice;
	}

	public void setAverageStockPrice(double averageStockPrice) {
		this.averageStockPrice = averageStockPrice;
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

	@Override
	public String toString() {
		return "StockPortfolio [id=" + id + ", account=" + account + ", stock=" + stock + ", stockQuantity="
				+ stockQuantity + ", averageStockPrice=" + averageStockPrice + ", originalInvestment="
				+ originalInvestment + ", drip=" + drip + ", purchaseDate=" + purchaseDate + ", comments=" + comments
				+ ", totalDividendAmount=" + totalDividendAmount + "]";
	}	
}
