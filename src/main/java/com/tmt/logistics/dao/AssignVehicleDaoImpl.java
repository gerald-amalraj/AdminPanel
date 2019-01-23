package com.tmt.logistics.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tmt.common.Utils;
import com.tmt.logistics.bean.AssignVehicle;

public class AssignVehicleDaoImpl implements AssignVehicleDao {

	@Autowired
	DataSource dataSource;

	
	@Override
	public AssignVehicle retrieveListData(AssignVehicle assgnVehicle) {
		//String sql = "select customerName from customerdetails where parent_id='"+assgnVehicle.getParent_id()+"';";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		System.out.println(assgnVehicle.getRole_id()+" ######################### "+assgnVehicle.getParent_id());
		String sql = "select username from login where status='A' and role_id = 2;";
		List<String> userList = (List<String>)jdbcTemplate.queryForList(sql, String.class);
		String sql1 = "";
		if(assgnVehicle.getRole_id().equals("1")){
			sql1 = "select vehicle_number from vehicle_connector where map_status='N'";	
		}else{
			sql1 = "select vehicle_number from vehicle_connector where map_status='N' and parent_id='"+assgnVehicle.getParent_id()+"';";	
		}
			
		List<String> vehicleList = (List<String>)jdbcTemplate.queryForList(sql1, String.class);
		String sql2 = "select imei from trackerdetails where status = 'Active' and imei not in (select imei from vehicle_connector) ;";
		List<String> imeiList = (List<String>)jdbcTemplate.queryForList(sql2, String.class);
		
		AssignVehicle assignVehicle = new AssignVehicle();
		//assignVehicle.setCustName(customerList);
		assignVehicle.setUserList(userList);
		assignVehicle.setVehicleNumber(vehicleList);
		assignVehicle.setImeiList(imeiList);
		return assignVehicle;
}
	
	public int updateCustVehicleMapping(AssignVehicle assgnVehicle){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		String loginSql = "select handler_id, role_id from login where username='"+assgnVehicle.getUserName()+"'";
		List<AssignVehicle> loginData = jdbcTemplate.query(loginSql, new BeanPropertyRowMapper(AssignVehicle.class));
				
		System.out.println("-----> "+Utils.showBean(assgnVehicle, true));
		
		String sql = "UPDATE vehicle_connector set map_status = 'Y', role_id = ?, parent_id = ?, imei= ? where vehicle_number = ?";
		
		System.out.println(((AssignVehicle)loginData.get(0)).getHandler_id()+"   ----------------------------> "+((AssignVehicle)loginData.get(0)).getRole_id());

		int result = jdbcTemplate.update(
				sql,
				new Object[] {((AssignVehicle)loginData.get(0)).getRole_id(), ((AssignVehicle)loginData.get(0)).getHandler_id(),  assgnVehicle.getInputImei(), assgnVehicle.getVehNumber()});

		return result;
	}
	
}
