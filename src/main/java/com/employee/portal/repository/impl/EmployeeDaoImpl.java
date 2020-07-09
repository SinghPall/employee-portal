package com.employee.portal.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.employee.portal.entity.Employee;
import com.employee.portal.repository.EmployeeRepositoryCustom;
@Repository
public class EmployeeDaoImpl implements EmployeeRepositoryCustom{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public Long findEmployeeByName(String name, String pass) {
		/*return jdbcTemplate.query("select * from ticket where empid = ?", 
				new Object[] {id}, new TicketRowMapper());*/
		List<Employee> emp = jdbcTemplate.query("select * from employee where name=? and pass=?", 
				new Object[] {name, pass}, new EmployeeRowMapper());
		if(null != emp && emp.size() > 0)
			return emp.get(0).getId();
		return null;
	}
	class EmployeeRowMapper implements RowMapper<Employee>{

		@Override
		public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
			Employee emp = new Employee();
			emp.setId(rs.getLong("id"));
			emp.setName(rs.getString("name"));
			emp.setPass(rs.getString("pass"));
			return emp;
		}
		
	}
}
