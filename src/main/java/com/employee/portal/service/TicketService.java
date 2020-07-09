package com.employee.portal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.portal.entity.Ticket;
import com.employee.portal.exception.ResourceNotFoundException;
import com.employee.portal.repository.TicketRepository;
import com.employee.portal.repository.impl.TicketDaoImpl;

@Service
public class TicketService {

	@Autowired
	TicketRepository ticketDao;
	@Autowired 
	TicketDaoImpl ticketDaoImpl;
	
	public void save(final Ticket ticket) {
		ticketDao.save(ticket);
	}
	public void delete(final Long id) {
		ticketDao.deleteById(id);
	}
	public void update(final Ticket ticket) {
		ticketDaoImpl.update(ticket);
	}
	public List<Ticket> fetchAllTickets(){
		List<Ticket> tickets = new ArrayList<>();
		ticketDao.findAll().forEach(t -> tickets.add(t));
		return tickets;
	}
	public List<Ticket> fetchTicketByEmpId(Long empId) throws ResourceNotFoundException{
		return ticketDaoImpl.findAllTicketByEmpId(empId);
	}
}
