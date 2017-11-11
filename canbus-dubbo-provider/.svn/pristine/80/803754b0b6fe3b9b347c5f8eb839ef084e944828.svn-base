package com.cpgps.canbus.common.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
/**
 * @author wangshuai01@e6yun.com 2017年5月12日
 */
public class PropertiesUtil {
	
	public PropertiesUtil(){}
	private static Properties props = new Properties(); 
	
	static{
		try {
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application-tablespace.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getValue(String key){
		return props.getProperty(key);
	}

    public static void updateProperties(String key,String value) {    
            props.setProperty(key, value); 
    }
    
    public static void main(String[] args) {
		System.out.println(getValue("tablespace.endPoint"));
	}
}
