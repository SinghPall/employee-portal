package com.employee.portal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.employee.portal.entity.Ticket;
@Configuration
public class EmployeeConfig {
	@Bean
	public Ticket getTicket() {
		return new Ticket();
	}
}
	