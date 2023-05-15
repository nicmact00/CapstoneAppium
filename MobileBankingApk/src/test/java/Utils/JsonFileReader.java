package Utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonFileReader {
	
	static ConfigFileReader cfg = new ConfigFileReader();
	static String jsonPhone = cfg.getSpecificUrlProperties("androidPhoneJson");

        
        // Specify the path to your JSON file
        String filePath = jsonPhone;
        

    JSONParser parser = new JSONParser();

	public String getDeviceName(String filePath) throws FileNotFoundException, IOException, ParseException {
		Object obj = parser.parse(new FileReader(filePath));
		JSONObject jsonObject = (JSONObject) obj;
		String deviceName = (String) jsonObject.get("appium:deviceName");
		return deviceName;
	}
	
	public String getPlatformName(String filePath) throws FileNotFoundException, IOException, ParseException {
		Object obj = parser.parse(new FileReader(filePath));
		JSONObject jsonObject = (JSONObject) obj;
		String deviceName = (String) jsonObject.get("platformName");
		return deviceName;
	}
	
	public String getPlatFormVersion(String filePath) throws FileNotFoundException, IOException, ParseException {
		Object obj = parser.parse(new FileReader(filePath));
		JSONObject jsonObject = (JSONObject) obj;
		String deviceName = (String) jsonObject.get("appium:platformVersion");
		return deviceName;
	}
	
	public String getAutomationName(String filePath) throws FileNotFoundException, IOException, ParseException {
		Object obj = parser.parse(new FileReader(filePath));
		JSONObject jsonObject = (JSONObject) obj;
		String deviceName = (String) jsonObject.get("appium:automationName");
		return deviceName;
	}
	
	public String getUUID(String filePath) throws FileNotFoundException, IOException, ParseException {
		Object obj = parser.parse(new FileReader(filePath));
		JSONObject jsonObject = (JSONObject) obj;
		String deviceName = (String) jsonObject.get("appium:Uuid");
		return deviceName;
	}
}
