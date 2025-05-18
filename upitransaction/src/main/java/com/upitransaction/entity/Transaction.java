package com.upitransaction.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity // This will be mapped as a database table ( Automatically it will be created in the database )
public class Transaction {
	
	@Id // Marked as a primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)// This will increament the data automatic.
	private Long id;
	
	@ManyToOne // Many transaction can be made by the same sender and this will be marked as a foreign key relationship in the database.
	private User sender;
	
	@ManyToOne // Many transaction can be made by the same receiver and this will be marked as a foreign key relationship in the database.
	private User receiver;
	
	private BigDecimal amount; // To calculate the amount.
	private LocalDateTime timestamp; // To store the date and time fo the transaction.
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public User getReceiver() {
		return receiver;
	}
	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
