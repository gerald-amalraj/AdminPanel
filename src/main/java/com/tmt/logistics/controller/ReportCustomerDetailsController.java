package com.tmt.logistics.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tmt.logistics.bean.CustomerReportDetail;
import com.tmt.logistics.dao.CustomerReportDao;

@Controller
public class ReportCustomerDetailsController{
	
	@Autowired
	CustomerReportDao customerReportDao;
	
	Map<String, String> vehicleConnector;
	Map<String, String> customerdetails;
	Map<String, String> logindetails;
	Map<String, String> driverdetails;
	
	@RequestMapping("/customerReport")
	public ModelAndView getCustomerReportScreen() {	
		System.out.println("Begins.................");
		List<CustomerReportDetail> vehicleReportDetail = customerReportDao.retrieveVehicleData();		
		vehicleConnector = constructMapObject(vehicleReportDetail, true, false, false, false);
		System.out.println("vehicleConnector Passed.................");
		List<CustomerReportDetail> customerReportDetail = customerReportDao.retrieveCustomerData();
		customerdetails  = constructMapObject(customerReportDetail, false, true, false, false);
		System.out.println("customerdetails Passed.................");
		List<CustomerReportDetail> loginReportDetail = customerReportDao.retrieveLoginData();
		logindetails = constructMapObject(loginReportDetail, false, false, true, false);
		System.out.println("logindetails Passed.................");
		List<CustomerReportDetail> driverReportDetail = customerReportDao.retrieveDriverData();
		driverdetails = constructMapObject(driverReportDetail, false, false, false, true);	
		System.out.println("driverdetails Passed.................");
		//List<CustomerReportDetail> customerReportDetailList = constructBeanObject(vehicleConnector, customerdetails, logindetails, driverdetails);
		List<CustomerReportDetail> customerReportDetailList = customerReportMappedObj(vehicleConnector, customerdetails, logindetails, driverdetails);
		return new ModelAndView("customerReport", "customerReportDetailList", customerReportDetailList);
	}
	
	private Map<String, String> constructMapObject(List<CustomerReportDetail> customerReportDetail, boolean vehicleFlag, boolean customerFlag, boolean loginFlag, boolean driverFlag){
		
		Map<String, String> mapObject = new HashMap<String, String>();				
		
		for(CustomerReportDetail customerData : customerReportDetail){
			if(vehicleFlag)
				mapObject.put(customerData.getVehicle_number(), customerData.getParent_id()+"#"+customerData.getRole_id());	
			else if(customerFlag) {
				System.out.println(customerData.getHandler_id()+" =============="+customerData.getParent_id()+"#"+customerData.getCustomerName());
				mapObject.put(customerData.getHandler_id(), customerData.getParent_id()+"#"+customerData.getCustomerName());	
			}else if(loginFlag)
				mapObject.put(customerData.getHandler_id(), customerData.getParent_id()+"#"+customerData.getUsername()+"#"+customerData.getRole_id());
			else if(driverFlag)
				mapObject.put(customerData.getVehicle_number(), customerData.getDriverName());
		}
		
		return mapObject;
	}
	
	/*public List<CustomerReportDetail> constructBeanObject(Map<String, String> vehicleConnector, Map<String, String> customerDetails, Map<String, String> loginDetails, Map<String, String> driverDetails){
		List<CustomerReportDetail> customerReportDataList = new LinkedList<CustomerReportDetail>();
		//System.out.println("******* "+customerDetails);
		//System.out.println("@@@@@@@* "+vehicleConnector);
		Iterator iter = vehicleConnector.entrySet().iterator();		
		while(iter.hasNext()){
			String[] brokerData = null;
			String user         = null;
			String brokerName   = null;
			String parent_id    = null;
			String ownerName    = null;
			Map.Entry<String, String> mapIter = (Map.Entry<String, String>) iter.next();
			String vehicleNumber              = mapIter.getKey();
			String handler_id                 = mapIter.getValue();
			String driverName                 = driverDetails.get(vehicleNumber);		
			if(customerDetails.get(handler_id) != null){
			String[] spitToken = customerDetails.get(handler_id).split("#");
			 parent_id   = spitToken[0];
			 ownerName   = spitToken[1];
			}else
				parent_id = handler_id;
			
			if(loginDetails.get(parent_id) == null){
				brokerData  = customerDetails.get(parent_id).split("#");
				user        = loginDetails.get(brokerData[0]);				
				brokerName  = ownerName ;
				ownerName   = brokerData[1];
			}else
				user        = loginDetails.get(parent_id);
						
			CustomerReportDetail custReportData = new CustomerReportDetail();
			custReportData.setVehicle_number(vehicleNumber);
			custReportData.setDriverName(driverName);
			custReportData.setOwnerName(ownerName);
			custReportData.setBrokerName(brokerName);
			custReportData.setUsername(user);
			
			System.out.println("vehicleNumber :::  "+vehicleNumber + " OwnerName ::: "+ownerName + " BrokerName ::: "+brokerName + " UserName ::: "+user);
			customerReportDataList.add(custReportData);
		}
		return customerReportDataList;
	}*/
	
	public List<CustomerReportDetail> customerReportMappedObj(Map<String, String> vehicleConnector, Map<String, String> customerDetails, Map<String, String> loginDetails, Map<String, String> driverDetails){
	List<CustomerReportDetail> customerReportDataList = new LinkedList<CustomerReportDetail>();
	Iterator iter = vehicleConnector.entrySet().iterator();		
	while(iter.hasNext()){
		TokenBean tokenBean;
		CustomerReportDetail custReportData = new CustomerReportDetail();
		Map.Entry<String, String> mapIter = (Map.Entry<String, String>) iter.next();
		String vehicleNumber              = mapIter.getKey();
		String vehicleConnectorValue      = mapIter.getValue();
		System.out.println("vehicleConnectorValue:: "+vehicleConnectorValue);
		custReportData.setVehicle_number(vehicleNumber);
		
		String[] spitToken = vehicleConnectorValue.split("#");
		String parent_id   = spitToken[0];
		int role_id        = Integer.parseInt(spitToken[1]);
		
		if(role_id > 1){			
			getList(loginDetails);
			System.out.println("%%%%%%%%%%%%%% "+parent_id);
		String[] loginDetailsValue = loginDetails.get(parent_id).split("#");
		
		//String login_parent_id   = loginDetailsValue[0];
		String login_parent_id   = parent_id;
		String login_user_name   = loginDetailsValue[1];
		System.out.println(login_parent_id+" -------------- "+login_user_name);	
		int login_role_id     = Integer.parseInt(loginDetailsValue[2]);
		System.out.println("login_role_id:: "+login_role_id);	
		
		if(login_role_id == 3){
			custReportData.setBrokerName(login_user_name);			
			tokenBean = getTokenBean(loginDetails, login_parent_id);						
			custReportData.setOwnerName(tokenBean.getUser_name());				
			tokenBean = getTokenBean(loginDetails, tokenBean.getParent_id());						
			custReportData.setUsername(tokenBean.getUser_name());
		}else if(login_role_id == 2){
			custReportData.setOwnerName(login_user_name);
			System.out.println("login_role_id:2: "+login_parent_id);
			tokenBean = getTokenBean(loginDetails, login_parent_id);						
			custReportData.setUsername(tokenBean.getUser_name());			
		}
		}else if(role_id == 1){
			tokenBean = getTokenBean(loginDetails, parent_id);			
			custReportData.setUsername(tokenBean.getUser_name());
		}		
		customerReportDataList.add(custReportData);				
	}
	
	return customerReportDataList;
	}
	
	private void getList(Map<String,String> getDetails) {
		
		Iterator iter = getDetails.entrySet().iterator();
		
		while(iter.hasNext()) {
			Map.Entry<String, String>  mapIter = (Map.Entry)iter.next();
			System.out.println("&&&&&&&&&&&: "+mapIter.getKey());
			System.out.println("###########: "+mapIter.getValue());
		}
	}
	
	private TokenBean getTokenBean(Map<String, String> loginDetails, String parent_id){
		TokenBean tokenBean = new TokenBean();
		String[] retOwnerValue = loginDetails.get(parent_id).split("#");
		tokenBean.setParent_id(retOwnerValue[0]);
		tokenBean.setUser_name(retOwnerValue[1]);
		return tokenBean;
	}
	
	
	public class TokenBean{
		private String parent_id;
		private String user_name;
		private int role_id;
		
		public String getParent_id() {
			return parent_id;
		}
		public void setParent_id(String parent_id) {
			this.parent_id = parent_id;
		}
		public String getUser_name() {
			return user_name;
		}
		public void setUser_name(String user_name) {
			this.user_name = user_name;
		}
		public int getRole_id() {
			return role_id;
		}
		public void setRole_id(int role_id) {
			this.role_id = role_id;
		}		
	}
}
