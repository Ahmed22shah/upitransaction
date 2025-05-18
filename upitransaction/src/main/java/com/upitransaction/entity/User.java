package com.upitransaction.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // This will be mapped as a database table ( Automatically it will be created in the database )
public class User {
	 
	 @Id // Primary Key
	 @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increament for primary key
	 private Long id;
	 
	 @Column(unique = true) // Unique key because one person consumes unique phone number.
	 private String phoneNumber;
	 
	 private Boolean upiEnabled = false; // for Enabling or Disabling UPI Number.
	 private BigDecimal balance = BigDecimal.ZERO; // For currency Big decimal used to avoid floating point numbers and static value is 0.
	 private Integer dailyTransferCount = 0; // To count the User Transaction and calculating the user per day limit.
	 private BigDecimal dailyTransferAmount = BigDecimal.ZERO; // To calculate the amount transfer per day limit.
	 private LocalDate lastTransferDate; // To save the last transaction user did.
	 
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		public Boolean getUpiEnabled() {
			return upiEnabled;
		}
		public void setUpiEnabled(Boolean upiEnabled) {
			this.upiEnabled = upiEnabled;
		}
		public BigDecimal getBalance() {
			return balance;
		}
		public void setBalance(BigDecimal balance) {
			this.balance = balance;
		}
		public Integer getDailyTransferCount() {
			return dailyTransferCount;
		}
		public void setDailyTransferCount(Integer dailyTransferCount) {
			this.dailyTransferCount = dailyTransferCount;
		}
		public BigDecimal getDailyTransferAmount() {
			return dailyTransferAmount;
		}
		public void setDailyTransferAmount(BigDecimal dailyTransferAmount) {
			this.dailyTransferAmount = dailyTransferAmount;
		}
		public LocalDate getLastTransferDate() {
			return lastTransferDate;
		}
		public void setLastTransferDate(LocalDate lastTransferDate) {
			this.lastTransferDate = lastTransferDate;
		}
		 
		 
		    
}
