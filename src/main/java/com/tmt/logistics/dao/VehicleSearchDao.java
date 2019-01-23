package com.tmt.logistics.dao;

import net.sf.json.JSONArray;

public interface VehicleSearchDao {
	
	public JSONArray retrieveVehicleData(String vehicleNumber);
	
	
}
