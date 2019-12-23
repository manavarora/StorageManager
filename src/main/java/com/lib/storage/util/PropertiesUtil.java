package com.lib.storage.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public  class PropertiesUtil {
	
	private  Properties properties= null;
	
	public PropertiesUtil(String propertFile) {


    	try (FileInputStream propertiesLoader = new FileInputStream(propertFile)){
    		properties = new Properties();
			properties.load(propertiesLoader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
		
	}
	
	public String getProperty(String property)
	{
		return properties.getProperty(property);
	}
}