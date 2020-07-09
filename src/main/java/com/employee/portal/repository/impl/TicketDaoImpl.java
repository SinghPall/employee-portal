package com.employee.portal.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.employee.portal.entity.Ticket;
import com.employee.portal.repository.TicketRepositoryCustom;

@Repository
public class TicketDaoImpl implements TicketRepositoryCustom{
	private static final Logger LOGGER = LoggerFactory.getLogger(TicketDaoImpl.class);
	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Ticket> findAllTicketByEmpId(Long id) {
		LOGGER.info("Getting all ticket for given empid - {}", id);
		return jdbcTemplate.query("select * from ticket where emp_id = ?", 
				new Object[] {id}, new TicketRowMapper());
	}
	public int update(final Ticket ticket) {
        Object[] params = { ticket.getEmpId(), ticket.getType(), ticket.getDesc(), ticket.getId()};
        int[] types = {Types.LONGVARCHAR, Types.VARCHAR, Types.VARCHAR, Types.LONGVARCHAR};
		return jdbcTemplate.update("update Ticket set empId = ?, type=?, desc =? where id =?", 
					params, types);
	}
	class TicketRowMapper implements RowMapper<Ticket>{
		public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException{
			Ticket ticket = new Ticket();
			ticket.setId(rs.getLong("id"));
			ticket.setEmpId(rs.getLong("emp_id"));
			ticket.setType(rs.getString("type"));
			ticket.setDesc(rs.getString("desc"));
			return ticket;
		}
	}
}

