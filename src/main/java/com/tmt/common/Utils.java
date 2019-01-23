package com.tmt.common;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Utils {

	public static String showBean(Object bean, boolean showNulls) {
		if (bean == null)
			return null;
		StringBuilder sb = new StringBuilder(bean.getClass().getName())
				.append("[");
		try {
			BeanInfo bi = Introspector.getBeanInfo(bean.getClass());
			PropertyDescriptor[] pd = bi.getPropertyDescriptors();
			for (int i = 0; i < pd.length; i++) {
				if (!"class".equals(pd[i].getName())) {
					Object result = pd[i].getReadMethod().invoke(bean);
					if (showNulls || result != null) {
						sb.append(pd[i].getDisplayName()).append("=")
								.append(result);
						if (i == pd.length - 1)
							continue;
						sb.append(",");
					}
				}
			}
		} catch (Exception ex) {
		}

		return sb.append("]").toString();
	}

	public static int generate5DigtRandomNum(){
		 Random r = new Random();
		 int ran5DigtNum = r.nextInt(99999);
		 return ran5DigtNum;
	}
	
	private static String parent_id;
		
	public static void main(String... arg) {
		System.out.println(DataField.valueOf("fileSystemName"));
	}

	public static String getParent_id() {
		return parent_id;
	}

	public static void setParent_id(String parent_id) {
		Utils.parent_id = parent_id;
	}

	
	public static String getDateTime(){
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();	
		return dateFormat.format(date);
	}
}
