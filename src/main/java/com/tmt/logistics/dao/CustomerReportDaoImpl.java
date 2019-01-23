package com.tmt.logistics.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tmt.logistics.bean.CustomerReportDetail;

public class CustomerReportDaoImpl implements CustomerReportDao {

	@Autowired
	DataSource dataSource;

	public List<CustomerReportDetail> retrieveVehicleData() {
		String sqlVehicle = "select vehicle_number, parent_id, role_id from vehicle_connector;";		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<CustomerReportDetail> vehicleReportList  = jdbcTemplate.query(sqlVehicle, new BeanPropertyRowMapper(CustomerReportDetail.class));		
		return vehicleReportList;
	}
	
	public List<CustomerReportDetail> retrieveCustomerData() {
		String sqlCustomer = "select handler_id, parent_id, customerName from customerdetails;";		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<CustomerReportDetail> customerReportList  = jdbcTemplate.query(sqlCustomer, new BeanPropertyRowMapper(CustomerReportDetail.class));		
		return customerReportList;
	}
	
	public List<CustomerReportDetail> retrieveLoginData() {
		String sqlLogin = "select handler_id, parent_id, role_id, username from login;";		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<CustomerReportDetail> loginReportList  = jdbcTemplate.query(sqlLogin, new BeanPropertyRowMapper(CustomerReportDetail.class));		
		return loginReportList;
	}
	
	public List<CustomerReportDetail> retrieveDriverData() {
		String sqlDriver = "select vehicle_number, driverName from driverdetails;";		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<CustomerReportDetail> driverReportList  = jdbcTemplate.query(sqlDriver, new BeanPropertyRowMapper(CustomerReportDetail.class));		
		return driverReportList;
	}
}
