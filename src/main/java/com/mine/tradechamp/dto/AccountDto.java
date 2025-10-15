package com.mine.tradechamp.dto;

import java.time.LocalDate;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class AccountDto {
	Long id; 
	Long accountId; 
	String accountName;
	String accountOwner;
	String broker;
	String accountType; 
	LocalDate openedDate;
	
	public AccountDto(Long id, Long accountId, String accountName, String accountOwner, String broker,
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
		return "AccountDto [id=" + id + ", accountId=" + accountId + ", accountName=" + accountName + ", accountOwner="
				+ accountOwner + ", broker=" + broker + ", accountType=" + accountType + ", openedDate=" + openedDate
				+ "]";
	}
	
}
