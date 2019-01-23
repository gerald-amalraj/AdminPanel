package com.tmt.logistics.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tmt.logistics.bean.ResultBean;
import com.tmt.logistics.dao.AgentDao;
import com.tmt.logistics.dao.VehicleSearchDao;

@Controller
public class VehicleSearchController {
	
 @Autowired
VehicleSearchDao vehicleSearchDao;
 
 @Autowired 
AgentDao agentDao;	
	

 @RequestMapping("/vehicleSearch")
	public ModelAndView vehicleSearch() {			
			return new ModelAndView("VehicleSearch", "", null);
	}
 
 @RequestMapping("/locateVehicle")
	public ModelAndView locateVehicle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 Map<String, JSONArray> map;	
	 System.out.println("----Locate Vehicle-------> ");
		String vehicleNumber = request.getParameter("vehicleNumber").trim().toUpperCase();
		System.out.println("Vehicle Number :: "+vehicleNumber);
		String imeiNumber = agentDao.retrieveIMEINumber(vehicleNumber);
		 if(imeiNumber == null || "".equals(imeiNumber)){
			 ResultBean resultMsg = new ResultBean();
			 resultMsg.setErrorMessage("Vehicle Number "+vehicleNumber+" is un-available to track!");
			 resultMsg.setShowResult("0");
			 resultMsg.setPageUrl("/DevTracker/vehicleSearch");
			 return new ModelAndView("ResultPage", "resultMsg", resultMsg);
			}else{
				JSONArray jObj = vehicleSearchDao.retrieveVehicleData(imeiNumber);				
				map = new HashMap<String, JSONArray>();
				map.put("coordinates", jObj);
			}
		
		
		return new ModelAndView("VehicleSearch", "map", map);
	}
}
