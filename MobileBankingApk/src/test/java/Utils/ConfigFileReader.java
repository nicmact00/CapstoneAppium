package Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
	
	Properties properties;
	String propertyFilePath = "./src/test/java/Config/configuration.properties";
	
	public ConfigFileReader() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at "+propertyFilePath);
		}
	}
	
	public long getImplicitlyWait() {
		String implicitlyWait = properties.getProperty("implicitlyWait");
		if (implicitlyWait != null) {
			return Long.parseLong(implicitlyWait);
		} else {
			throw new RuntimeException("implicitlyWait not specified in the Configuration.properties");
		}
	}
	
	 public String getSpecificUrlProperties(String configRequest) {
	        String requestOutput = properties.getProperty(configRequest);
	        if(requestOutput != null) return requestOutput;
	        else throw new RuntimeException("Request not found in Configuration file at " + propertyFilePath);
	    }
		
	
	
	public String getDriverUrl(String propertyName) {
		String value = properties.getProperty(propertyName);
		if (value != null) {
			return value;
		} else {
			throw new RuntimeException(propertyName+" not specified in the Configuration.properties");
		}
		
	}

}