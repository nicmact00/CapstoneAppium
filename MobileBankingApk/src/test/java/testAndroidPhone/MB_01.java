package testAndroidPhone;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Android.AndroidPhoneLaunch;
import Utils.ConfigFileReader;
import Utils.ExcelAccount;
import Utils.ExtentReport;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.Assert;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MB_01 extends AndroidPhoneLaunch{

	AndroidDriver driver;

	ConfigFileReader cfg = new ConfigFileReader();

	@BeforeSuite
	public void beforeclass() {
		test = ExtentReport.generateExtentReport("Android Phone - MB_01");
	}

	@Test
	public void MobileBankingLauch() throws FileNotFoundException, IOException, ParseException {
		AndroidPhoneLaunch.configureApp();
	}

	@Test(priority = 1)
	public void inputUsernameAndPassword() throws MalformedURLException, IOException {
		ExcelAccount.excelUsernamePassword();
	}

	@Test(priority = 2)
	public void checkingAccount() throws MalformedURLException {

		UiAutomator2Options cap = new UiAutomator2Options();

		String urlpath = cfg.getSpecificUrlProperties("driverUrl");
		driver = new AndroidDriver(new URL(urlpath), cap);

		driver.findElement(By.id("com.androiddevelopment.mobile_banking:id/checkingButton")).click();

		WebElement getTitle = driver.findElement(By.xpath("//*[@text='Checking Account']"));
		// System.out.println(getTitle.toString());
		if (getTitle.isDisplayed()) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
		
		if (getTitle.isDisplayed()) {
			test.log(LogStatus.PASS, "Checking Account found", "success");
		} else {
			test.log(LogStatus.FAIL, "Checking Account not found", "failed");
		}

	}

	@AfterSuite
	public void afterclass() {
		ExtentReport.closeExtentReport();
	}

}
