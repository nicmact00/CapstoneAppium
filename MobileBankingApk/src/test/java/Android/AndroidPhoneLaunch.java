package Android;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.SessionNotCreatedException;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utils.ConfigFileReader;
import Utils.JsonFileReader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class AndroidPhoneLaunch extends JsonFileReader {

	public static ExtentTest test;
	static AndroidDriver ad;

	static ConfigFileReader cfg = new ConfigFileReader();
	static String jsonPhone = cfg.getSpecificUrlProperties("androidPhoneJson");
	static JsonFileReader jfr = new JsonFileReader();

	static String apkPath = cfg.getSpecificUrlProperties("apkPath");

	public static void configureApp() throws FileNotFoundException, IOException, ParseException {
		String filePath = jsonPhone;
		String deviceName = jfr.getDeviceName(filePath);
		String platformName = jfr.getPlatformName(filePath);
		String versionName = jfr.getPlatFormVersion(filePath);
		String automationName = jfr.getAutomationName(filePath);

		File fil = new File(apkPath);
		File fs = new File(fil, "mobilebanking.apk");
		UiAutomator2Options cap = new UiAutomator2Options();

		cap.setDeviceName(deviceName);
		cap.setApp(fs.getAbsolutePath());
		cap.setPlatformVersion(versionName);
		cap.setAutomationName(automationName);
		cap.setPlatformName(platformName);

		try {
			ad = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
			ad.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			test.log(LogStatus.INFO, "Mobile Banking application launched successfully.");

		} catch (SessionNotCreatedException e) {
			System.out.println("Session could not be created. Please check the capabilities and device settings.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
