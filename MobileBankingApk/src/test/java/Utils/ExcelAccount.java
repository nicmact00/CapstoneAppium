package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;

import com.relevantcodes.extentreports.LogStatus;

import Android.AndroidPhoneLaunch;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class ExcelAccount extends AndroidPhoneLaunch {

	static AndroidDriver driver;

	static ConfigFileReader cfg = new ConfigFileReader();

	public static void excelUsernamePassword() throws IOException {

		UiAutomator2Options cap = new UiAutomator2Options();

		String urlPath = cfg.getSpecificUrlProperties("driverUrl");
		driver = new AndroidDriver(new URL(urlPath), cap);

		String excelUser = cfg.getSpecificUrlProperties("excelUser");
		File fil = new File(excelUser);
		
		FileInputStream fis = new FileInputStream(fil);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Sheet1");

		// Excel username and password
		Integer usernameExcel = (int) sheet.getRow(1).getCell(0).getNumericCellValue();
		Integer passwordExcel = (int) sheet.getRow(1).getCell(1).getNumericCellValue();

		driver.findElement(By.id("com.androiddevelopment.mobile_banking:id/usernameEditText"))
				.sendKeys(Integer.toString(usernameExcel));
		driver.findElement(By.id("com.androiddevelopment.mobile_banking:id/passwordEditText"))
				.sendKeys(Integer.toString(passwordExcel));
		driver.findElement(By.id("com.androiddevelopment.mobile_banking:id/loginButton")).click();
		
		
	}
}
