package com.jmfl.blinktrade.adminportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackages = {
		"com.jmfl.blinktrade.service",
		"com.jmfl.blinktrade.dao",
		"com.jmfl.blinktrade.adminportal",
		"com.jmfl.blinktrade.config"
})
public class AdminPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminPortalApplication.class, args);
	}

}
