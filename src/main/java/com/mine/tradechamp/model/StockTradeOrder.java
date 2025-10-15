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
@Table(name = "stocktradeorder")
public class StockTradeOrder {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //this make the ID autoincrement
	Long id; 
	
	@Column 
	Long accountId;
	
	@Column 
	String stockSymbol;
	
	@Column 
	String orderType; // Buy/Sell
		
	@Column
	double quantity;
	
	@Column
	double price;
		
	@Column
	LocalDate executionDate; 
	
	@Column
	String comments;
	
	public StockTradeOrder() {}

	public StockTradeOrder(Long id, Long accountId, String stockSymbol, String orderType, double quantity, double price,
			LocalDate executionDate, String comments) {
		super();
		this.id = id;
		this.accountId = accountId;
		this.stockSymbol = stockSymbol;
		this.orderType = orderType;
		this.quantity = quantity;
		this.price = price;
		this.executionDate = executionDate;
		this.comments = comments;
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

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDate getExecutionDate() {
		return executionDate;
	}

	public void setExecutionDate(LocalDate executionDate) {
		this.executionDate = executionDate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "StockTradeOrder [id=" + id + ", accountId=" + accountId + ", stockSymbol=" + stockSymbol
				+ ", orderType=" + orderType + ", quantity=" + quantity + ", price=" + price + ", executionDate="
				+ executionDate + ", comments=" + comments + "]";
	}	
}
