package com.upitransaction.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

//Spring Configuration (This class contains bean definition ) like CORS rule.
@Configuration 
public class WebConfig {
	
	//To register the CORS rules ( Cross Origin Resource Sharing )
	public void addCorsMappings(CorsRegistry registry) {
		 registry.addMapping("/upitransaction/**") // any endpoint starts with upitransaction that will allow.
         .allowedOrigins("http://localhost:3000") // port 3000 will be allow here and except this port no other port will not be allowed.
         .allowedMethods("GET", "POST", "PUT", "DELETE") //This HTTP methods will be allowed here.
         .allowedHeaders("*"); // This allow HTTP Headers like content-type.
	}
}
