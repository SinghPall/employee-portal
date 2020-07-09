package com.employee.portal.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.employee.portal.entity.Employee;
import com.employee.portal.entity.Ticket;
import com.employee.portal.exception.ResourceNotFoundException;
import com.employee.portal.service.EmployeeService;
import com.employee.portal.service.TicketService;

@Controller
@RequestMapping("/employee")
@Validated
public class EmployeePortalController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeePortalController.class);
	@Value("${spring.message}")
	private String msg;
	@Autowired
	TicketService ticketService;
	@Autowired
	EmployeeService empService;

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("msg", this.msg);
		return "login";
	}
	@GetMapping("/signup")
	public String signup(Model model) {
		Employee emp = new Employee();
		model.addAttribute("emp", emp);
		model.addAttribute("msg", "Welcome to Portal");
		return "signup";
	}

	@PostMapping("/signup")
	public String createEmployee(@Valid Employee emp, Model model) {
		LOGGER.info("Inside createEmployee - {}", emp);
		Long id = empService.validateEmployee(emp.getName(), emp.getPass());
		System.out.println("id+++++++++++++++++>>>>>>>>"+id);
		if(null != id) {
			model.addAttribute("errormsg", "Employee already registered");
			return "login";
		}
		empService.save(emp);
		model.addAttribute("msg", "Employee registered successfully");	
		return "login";
	}

	@PostMapping("/login")
	public String getWelcomePage(Model model, @RequestParam String name, @RequestParam String pass) throws ResourceNotFoundException {
		LOGGER.info("Inside getWelComePage - {}", name);
		Long id = empService.validateEmployee(name, pass);

		if(id == null) {
			model.addAttribute("errormsg", "Invalid Credentials");
			return "login";
		}
		List<Ticket> tickets = getTicketByEmployeeId(id);
		Ticket ticket = new Ticket();
		ticket.setEmpId(id);
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		model.addAttribute("ticket", ticket);
		model.addAttribute("tickets", tickets);
		return "details";
	}
	@PostMapping("/ticket")
	public String createTicket(Model model, @Valid Ticket ticket) throws ResourceNotFoundException {
		LOGGER.info("Inside createTicket");
		ticketService.save(ticket);
		List<Ticket> tickets = getTicketByEmployeeId(ticket.getEmpId());
		//model.addAttribute("name", name);
		model.addAttribute("tickets", tickets);
		return "details";
	}
	
	@GetMapping("/{empid}")
	public String getEmployeeById(@PathVariable(value = "empid")@NonNull Long id) throws ResourceNotFoundException{
		LOGGER.info("Getting emp by id - {}", id);
		Employee e = empService.getEmpById(id); // need to add into model to render on view
		LOGGER.info("Getting emp - {}", e);
		return "employee";
	}
	//@GetMapping("/ticket/{id}")
	public List<Ticket> getTicketByEmployeeId(@NonNull Long empid) throws ResourceNotFoundException{
		List<Ticket> list = ticketService.fetchTicketByEmpId(empid);
		return list;
	}

	@PostMapping("/update")
	public String update(Model model, @Valid Ticket ticket) throws ResourceNotFoundException {
		LOGGER.info("Inside update");
		ticketService.update(ticket);
		List<Ticket> tickets = getTicketByEmployeeId(ticket.getEmpId());
		model.addAttribute("tickets", tickets);
		return "details";
	}
	@PostMapping("/delete")
	public String delete(Model model, @Valid Ticket ticket) throws ResourceNotFoundException {
		LOGGER.info("Inside delete");
		ticketService.delete(ticket.getId());
		
		List<Ticket> tickets = getTicketByEmployeeId(ticket.getEmpId());
		model.addAttribute("tickets", tickets);
		return "details";
	}
}
