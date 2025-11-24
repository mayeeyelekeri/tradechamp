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
@Table(name = "account")
public class Account {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //this make the ID autoincrement
	Long id; 
	
	@Column(name = "account_id")
	Long accountId;
	
	@Column(name = "account_name") 
	String accountName;
	
	@Column(name = "account_owner") 
	String accountOwner;
	
	@Column(name = "broker")
	String broker;
	
	@Column(name = "account_type")
	String accountType; 
	
	@Column(name = "opened_date")
	LocalDate openedDate; 

	public Account() {}

	public Account(Long id, Long accountId, String accountName, String accountOwner, String broker,
			String accountType, LocalDate openedDate) {
		super();
		this.id = id;
		this.accountId = accountId;
		this.accountName = accountName;
		this.accountOwner = accountOwner;
		this.broker = broker;
		this.accountType = accountType;
		this.openedDate = openedDate;
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
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountOwner() {
		return accountOwner;
	}
	public void setAccountOwner(String accountOwner) {
		this.accountOwner = accountOwner;
	}
	public String getBroker() {
		return broker;
	}
	public void setBroker(String broker) {
		this.broker = broker;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public LocalDate getOpenedDate() {
		return openedDate;
	}
	public void setOpenedDate(LocalDate openedDate) {
		this.openedDate = openedDate;
	}
	
	@Override
	public String toString() {
		return "Account [id=" + id + ", accountId=" + accountId + ", accountName=" + accountName + ", accountOwner="
				+ accountOwner + ", broker=" + broker + ", accountType=" + accountType + ", openedDate=" + openedDate
				+ "]";
	}	
}
