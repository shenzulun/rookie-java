package org.magen.rookie.factory;

import org.magen.rookie.service.ICommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class ServiceFactory {
	
	@Autowired
	@Qualifier("CommonService")
	private static ICommonService commonService;
	
	public static ICommonService getCommonService(){
		return commonService; 
	}
}
