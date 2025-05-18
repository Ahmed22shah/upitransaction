package com.upitransaction.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upitransaction.entity.Transaction;
import com.upitransaction.entity.User;
import com.upitransaction.repo.TransactionRepository;
import com.upitransaction.repo.UserRepository;

import jakarta.transaction.Transactional;

@Service // Spring Service component, it holds the business logic and it will detect automatically by component scanning.
public class UpiService {
	
	@Autowired // auto inject - communicate the database
	private UserRepository userRepository;
	
	@Autowired // auto inject - communicate the database
	private TransactionRepository transactionRepository;
	
	//To add a new number
	public void addPhoneNumber(String phoneNumber) {
		
		User user = new User();// creating a object for a user class.
		
		user.setPhoneNumber(phoneNumber); // Getting the phone number from the parameter and setting it to the user object.
		
		userRepository.save(user); // Finally save this user object to the database ( Presist )
	}
	
	//Enabling the UPI phonenumber
	public void enableUpi(String phoneNumber) {
		//Fetches the user phone number from the DB and if there means it will go next line or else it throws a error.
		User user = userRepository.findByPhoneNumber(phoneNumber).orElseThrow(() ->new RuntimeException("User not found!"));
		
		user.setUpiEnabled(true); // Setting the boolean true and it will be updated.
		userRepository.save(user); // Saving this user object to the DB.
				
	}
	
	//Disabling the UPI Phonenumber
	public void disableUpi(String phoneNumber) {
		//Fetches the user phone number from the DB and if there means it will go next line or else it throws a error.
		User user = userRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new RuntimeException("User not found!"));
		
		user.setUpiEnabled(false); // Setting the boolean false and it will be updated.
		userRepository.save(user); // Saving this user object to the DB.

	}
	
	//To check the current balance.
	public BigDecimal checkBalance(String phoneNumber) {
		//Fetches the user phone number from the DB and if there means it will go next line or else it throws a error.
		User user = userRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new RuntimeException("User not found!"));
		
		return user.getBalance(); // Return the current balance.
	}
	
	//To add Money to the given phone number.
	public void addMoney(String phoneNumber, BigDecimal amount) {
		//Fetches the user phone number from the DB and if there means it will go next line or else it throws a error.
		User user = userRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new RuntimeException("User not found!"));
		
		//Using Bigdecimal class we can add the amount to the user.
		//Get the balance and add the amount to it.
		BigDecimal newBalance = user.getBalance().add(amount);
		
		// Condition - balance must not exceed 1 lakh otherwise it throws a error.
		if(newBalance.compareTo(new BigDecimal("100000")) > 0) {
			throw new RuntimeException("Balance cannot exceed 1 lakh");
		}
		
		//if the condition does not satisfy, it will set to the user object.
		user.setBalance(newBalance);
		
		userRepository.save(user); // new balance will be save to the DB.
	}
	
	//Transfer the sender money to the receiver 
	public void transferMoney(String senderPhone, String receiverPhone, BigDecimal amount) {
		
		// Condition - amount must not exceed 20k otherwise it will throw a error,
		if(amount.compareTo(new BigDecimal("20000")) > 0) {
			throw new RuntimeException("Transfer amount exceed 20k limit");
		}
		
		//To check the sender phone number saved in the DB or it will throw a error.
		User sender = userRepository.findByPhoneNumber(senderPhone).orElseThrow(() -> new RuntimeException("Sender not found"));
		
		//To check the receiver phone number saved in the DB or it will throw a error.
		User receiver = userRepository.findByPhoneNumber(receiverPhone).orElseThrow(() -> new RuntimeException("Receiver not found"));
		
		// Condition - The UPI number should be enabled or it will throw a error.
		if(!sender.getUpiEnabled() || !receiver.getUpiEnabled()) {
			throw new RuntimeException("UPI not enabled for sender or receiver");
		}
		
		LocalDate today = LocalDate.now(); // Date only get here.
		
		if(sender.getLastTransferDate() == null || !sender.getLastTransferDate().equals(today)) {
			
			//To calculate the sender transfer count
			sender.setDailyTransferCount(0);
			
			// To calculate the daily transfer amount
			sender.setDailyTransferAmount(BigDecimal.ZERO);
			
			//set the transfer date in the sender object
			sender.setLastTransferDate(today);
		}
		
		// Condition - To check the Daily transfer count must not exceed 3.
		if (sender.getDailyTransferCount() >= 3) {
            throw new RuntimeException("Daily transfer count limit reached");
        }
		
		//Condition - To check the daily transfer limit and sender amount must not exceed 50k.
		BigDecimal newDailyAmount = sender.getDailyTransferAmount().add(amount);
	       if (newDailyAmount.compareTo(new BigDecimal("50000")) > 0) {
	    	   throw new RuntimeException("Daily transfer amount limit exceeded");
	       }
	        
	    // Conditoin - The current balance not below 0.
	    if (sender.getBalance().compareTo(amount) < 0) {
	    	throw new RuntimeException("Insufficient balance");
	    }
	    
	    // After sending the money to the receiver UPI, the sender amount will be subtract from the saving balance.
	    sender.setBalance(sender.getBalance().subtract(amount));
	    
	    // the sender money will be added to the receiver UPI number.
        receiver.setBalance(receiver.getBalance().add(amount));
        
        // counting the daily transfer count per day.
        sender.setDailyTransferCount(sender.getDailyTransferCount() + 1);
        
        // setting the daily transfer amount of the sender per day.
        sender.setDailyTransferAmount(newDailyAmount);
        
        // Saving the transaction details to the transaction table.
        Transaction transaction = new Transaction();
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setAmount(amount);
        transaction.setTimestamp(LocalDateTime.now());
        
        userRepository.save(sender); // Saving the sender data to the DB
        userRepository.save(receiver); // Saving the receicer data to the DB
        transactionRepository.save(transaction); // Saving the transaction data to the DB.
	}
}	
