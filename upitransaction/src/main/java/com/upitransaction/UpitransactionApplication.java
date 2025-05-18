package com.upitransaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // does the work of three annotation ( Configuration, EnableAutoConfiguration, ComponentScan)
public class UpitransactionApplication {

	//main class in java
	public static void main(String[] args) {
		/*
		 * Embedded Tomcat Server.
		 * Bootstrap the spring boot application
		 */
		SpringApplication.run(UpitransactionApplication.class, args);
	}

}
