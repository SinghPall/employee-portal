package com.employee.portal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.portal.entity.Employee;
import com.employee.portal.exception.ResourceNotFoundException;
import com.employee.portal.repository.EmployeeRepository;
import com.employee.portal.repository.impl.EmployeeDaoImpl;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeDao;
	@Autowired
	EmployeeDaoImpl employeeDaoImpl;
	
	public void save(final Employee emp) {
		employeeDao.save(emp);
	}
	public List<Employee> getAllEmployee(){
		List<Employee> employees = new ArrayList<>();
		employeeDao.findAll().forEach(e -> employees.add(e));
		return employees;
	}
	public Employee getEmpById(Long id) throws ResourceNotFoundException {
		Employee emp = employeeDao.findById(id)
								.orElseThrow(() -> new ResourceNotFoundException("Employee not present with : "+ id));
		return emp;
	}
	public Long validateEmployee(String name, String pass) {
		return employeeDaoImpl.findEmployeeByName(name, pass);
	}
}
