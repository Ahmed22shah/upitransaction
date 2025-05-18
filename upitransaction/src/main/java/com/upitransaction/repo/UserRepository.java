package com.upitransaction.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upitransaction.entity.User;

/* Data JPA repository interface
 * No need for SQL queries
 * By this repository can perform database operations.
 * By Specifying User entity class, this will allow to communicate with this user table.
 * Long - denoted primary key 
 */
public interface UserRepository extends JpaRepository<User, Long>{
	//Optional is used to avoid null pointer exception.
	Optional<User> findByPhoneNumber(String phoneNumber);
}
