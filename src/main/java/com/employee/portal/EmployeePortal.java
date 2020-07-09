package com.employee.portal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.employee.portal.entity"})
public class EmployeePortal {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeePortal.class);
	
	public static void main(String[] args) {
		SpringApplication.run(EmployeePortal.class, args);
		LOGGER.info("In main class");
	}
}
