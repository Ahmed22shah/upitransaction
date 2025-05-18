package com.upitransaction.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upitransaction.entity.Transaction;
import com.upitransaction.entity.User;

/* Data JPA repository interface
 * No need for SQL queries
 * By this repository can perform databse operations.
 * By Specifying Transaction entity, this will allow to communicate with this table.
 * Long - denoted primary key 
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	//findBySender - the Spring will create a query to find the sender ( Custom query ).
	//List - Used to get all the collections of data.
	List<Transaction> findBySender(User sender);
}
