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

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dividendpay")
public class DividendPay {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //this make the ID autoincrement
	Long id; 
	
	@Column 
	String stock;
	
	@Column
	LocalDate exDividendDate;
	
	@Column
	LocalDate payDate; 
	
	@Column
	double amount;

	public DividendPay() {} 
	
	public DividendPay(Long id, String stock, LocalDate exDividendDate, LocalDate payDate, double amount) {
		super();
		this.id = id;
		this.stock = stock;
		this.exDividendDate = exDividendDate;
		this.payDate = payDate;
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "DividendPay [id=" + id + ", stock=" + stock + ", exDividendDate=" + exDividendDate + ", payDate="
				+ payDate + ", amount=" + amount + "]";
	}

	
}
