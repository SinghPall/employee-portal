package com.employee.portal.repository;

import java.util.List;
import com.employee.portal.entity.Ticket;

public interface TicketRepositoryCustom {
	List<Ticket> findAllTicketByEmpId(Long id); 
}
