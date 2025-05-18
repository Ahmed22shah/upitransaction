package com.upitransaction.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.upitransaction.service.UpiService;

@RestController // @Controller - component , @ ResponseBody - return json
@RequestMapping("/upitransaction") // Base path for all the API
@CrossOrigin // Enabling the cross origin resource sharing
public class UpiController {
	
	@Autowired // Auto-inject ( injecting the business logic )
    private UpiService upiService;
	
	@PostMapping("/addPhoneNumber") // POST method
	//ResponseEntity - for HTTP response
	public ResponseEntity<String> addPhoneNumber(@RequestParam String phoneNumber){ // Parameter - Phone number
		
		upiService.addPhoneNumber(phoneNumber); // calls the addPhoneNumber service
		
		return ResponseEntity.ok("Phone Number added successfully!."); // OK - success.
	}
	
	 @PostMapping("/enable") // POST method
	//ResponseEntity - for HTTP response
	 public ResponseEntity<String> enableUpi(@RequestParam String phoneNumber) {  // Parameter - Phone number
		 
		 upiService.enableUpi(phoneNumber); // calls the enableUpi service
        
		 return ResponseEntity.ok("UPI enabled"); // OK - success.
     }
	 
	 @PostMapping("/disable") // POST method
	//ResponseEntity - for HTTP response
	 public ResponseEntity<String> disableUpi(@RequestParam String phoneNumber) { // Parameter - Phone number
		
		 upiService.disableUpi(phoneNumber); // calls the disableUpi service
       
		 return ResponseEntity.ok("UPI disabled"); // OK - success.
     }
	 
	 @GetMapping("/balance") // GET method
	//ResponseEntity - for HTTP response
	 public ResponseEntity<BigDecimal> checkBalance(@RequestParam String phoneNumber) { // Parameter - Phone number
		
		 BigDecimal balance = upiService.checkBalance(phoneNumber); // calls the checkBalance service
	        
		 return ResponseEntity.ok(balance); // OK - success.
	    
	 }
	 
	 @PostMapping("/add-money") // POST method
	//ResponseEntity - for HTTP response
	 public ResponseEntity<String> addMoney(@RequestParam String phoneNumber, @RequestParam BigDecimal amount) { // Parameter - Phone number, amount
	 
		 upiService.addMoney(phoneNumber, amount); // calls the addMoney service
	     
		 return ResponseEntity.ok("Money added successfully"); // OK - success.
	   
	 }
	 
	 @PostMapping("/transfer") // POST method
	//ResponseEntity - for HTTP response
	// Parameter - sender phone number, Receiver phone number, amount
	 public ResponseEntity<String> transferMoney(@RequestParam String senderPhone, @RequestParam String receiverPhone, @RequestParam BigDecimal amount) {
	 
		 upiService.transferMoney(senderPhone, receiverPhone, amount); // calls the transferMoney service
	     
		 return ResponseEntity.ok("Transfer successful"); // OK - success.
	    
	 }
}
