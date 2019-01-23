package com.tmt.logistics.dao;

import java.util.List;

import javax.sql.DataSource;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tmt.logistics.bean.Coordinates;

public class VehicleSearchDaoImpl implements VehicleSearchDao {

	@Autowired
	DataSource dataSource;

	@Override
	public JSONArray retrieveVehicleData(String imeiNumber) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);	
		//String sqlCoordinates = "SELECT * FROM coordinates WHERE id = (SELECT MAX(id) FROM coordinates WHERE imei='"+imeiNumber+"');";
		String sqlCoordinates = "SELECT * FROM coordinates WHERE imei='"+imeiNumber+"';";
		List<Coordinates> coordinatesList  = jdbcTemplate.query(sqlCoordinates, new BeanPropertyRowMapper(Coordinates.class));
		System.out.println("@@@@@@@@@@@@@@@@@@@ "+coordinatesList.size());
		JSONArray myArray = new JSONArray();
		myArray.addAll(coordinatesList);
		
		return myArray;
	}	
}
