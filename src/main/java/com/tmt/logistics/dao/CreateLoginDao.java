package com.tmt.logistics.dao;



import java.util.List;

import com.tmt.logistics.bean.CreateLogin;

public interface CreateLoginDao {	
	
	public List<String> getCustomerList();
	public int insertLoginData(CreateLogin createLogin);
	
}
