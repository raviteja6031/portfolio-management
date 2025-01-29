package com.customer.portal.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerPortalService {

	private static Logger logger = LoggerFactory.getLogger(CustomerPortalService.class);
	
	public Map<String,String> convertToMap(String[] name,String[] counts){
		logger.info("Inside CustomerPortalService -> convertToMap");
		Map<String, String> map=new HashMap<>();
		int v=0;
		String[] count=new String[name.length];
		for(int j=0;j<counts.length;j++) {
			if(!counts[j].equals("0")) {
				count[v++]=counts[j];
			}
			
		}
		for(int i=0;i<count.length;i++) {
			map.put(name[i], count[i]);
		}
		logger.info("Outside CustomerPortalService -> convertToMap");
		return map;
		
	}
}
