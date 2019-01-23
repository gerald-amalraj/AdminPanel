package com.tmt.logistics.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tmt.common.Utils;
import com.tmt.logistics.bean.CreateLogin;

public class CreateLoginDaoImpl implements CreateLoginDao {

	@Autowired
	DataSource dataSource;
	
	public List<String> getCustomerList() {
		String sql = "select customerName from customerdetails";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<String> customerList = (List<String>)jdbcTemplate.queryForList(sql, String.class);
		return customerList;
	}

	@Override
	public int insertLoginData(CreateLogin createLogin) {
		String sql = "INSERT INTO login "
				+ "(username, password, confirm_password, phone, alt_phone, email, name, address, role_id, handler_id, parent_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		int result = jdbcTemplate.update(
				sql,
				new Object[] {createLogin.getUserName(), createLogin.getPassword(), createLogin.getConfirmPassword(),
						createLogin.getPhone(), createLogin.getMobile(), createLogin.getEmail(), createLogin.getName(), createLogin.getAddress(), createLogin.getRole_id(), Utils.generate5DigtRandomNum(), createLogin.getParent_id()});
				
		return result;
	}
	
	
	

}
