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
@Table(name = "dividendannouncement")
public class DividendAnnouncement {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //this make the ID auto-increment
	Long id; 
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "STOCK", referencedColumnName = "ID")
	Stock stock;
	
	@Column
	double declaredAmount;
	
	@Column
	LocalDate declaredDate; 
	
	@Column
	LocalDate exDividendDate;
	
	@Column
	LocalDate payDate; 

	@Column 
	String dividendFrequency; 
	
	public DividendAnnouncement() {}

	public DividendAnnouncement(Long id, Stock stock, double declaredAmount, LocalDate declaredDate,
			LocalDate exDividendDate, LocalDate payDate, String dividendFrequency) {
		super();
		this.id = id;
		this.stock = stock;
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
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
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
		return "DividendAnnouncement [id=" + id + ", stock=" + stock + ", declaredAmount="
				+ declaredAmount + ", declaredDate=" + declaredDate + ", exDividendDate=" + exDividendDate
				+ ", payDate=" + payDate + ", dividendFrequency=" + dividendFrequency + "]";
	} 	
	
}
