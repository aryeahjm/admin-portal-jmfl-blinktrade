package com.jmfl.blinktrade.adminportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.spring6.SpringTemplateEngine;

@SpringBootApplication(scanBasePackages = {"com.jmfl.blinktrade.service","com.jmfl.blinktrade.dao","com.jmfl.blinktrade.adminportal","com.jmfl.blinktrade.config"})
public class AdminportalApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminportalApplication.class, args);
	}

	@GetMapping()
	public String getFun(){
		return "home";
	}

}
