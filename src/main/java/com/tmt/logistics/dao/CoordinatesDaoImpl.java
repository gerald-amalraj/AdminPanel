package com.tmt.logistics.dao;

/**
 *
 * @author Nagesh Chauhan
 */

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.tmt.common.Geocoder;
import com.tmt.logistics.bean.Coordinates;

public class CoordinatesDaoImpl implements CoordinatesDao {

	@Autowired
	DataSource dataSource;
	
	@Override
	public Coordinates getCoordinates(String vehicleNumber) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String getImei = "select imei from vehicle_connector where vehicle_number = ?;";
		String imeiNumber = (String)jdbcTemplate.queryForObject(getImei, new Object[] { vehicleNumber }, String.class);
		System.out.println("ImeiNumber >>>>> "+imeiNumber);
		String sql = "SELECT * FROM coordinates where imei='"+imeiNumber+"' order by id desc limit 1;";
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<Coordinates>() {

			@Override
			public Coordinates extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					Coordinates coordinate = new Coordinates();
					coordinate.setImei(rs.getString("imei"));
					coordinate.setLatitude(rs.getString("latitude"));
					coordinate.setLongitude(rs.getString("longitude"));
					try {
						coordinate.setAddress(Geocoder.getAddress(Double.parseDouble(coordinate.getLatitude()), Double.parseDouble(coordinate.getLongitude())));
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					coordinate.setSpeed(rs.getString("speed"));
					coordinate.setDate(rs.getString("date"));
					return coordinate;
				}
				return null;
			}
		});
	}
		
	
	
}
