package testAndroidTablet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Android.AndroidTabletLaunch;
import Utils.ConfigFileReader;
import Utils.ExcelAccount;
import Utils.ExtentReport;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class MB_02T extends AndroidTabletLaunch {
	AndroidDriver driver;

	ConfigFileReader cfg = new ConfigFileReader();

	String urlPath = cfg.getSpecificUrlProperties("driverUrl");

	@BeforeSuite
	public void beforeclass() {
		test = ExtentReport.generateExtentReport("Android Phone - MB_02T");
	}

	@Test
	public void MobileBankingLauch() throws FileNotFoundException, IOException, ParseException {
		AndroidTabletLaunch.configureApp();
	}

	@Test(priority = 1)
	public void inputUsernameAndPassword() throws MalformedURLException, IOException {
		ExcelAccount.excelUsernamePassword();
	}

	@Test(priority = 2)
	public void checkingAccount() throws MalformedURLException {
		UiAutomator2Options cap = new UiAutomator2Options();

		driver = new AndroidDriver(new URL(urlPath), cap);

		driver.findElement(By.id("com.androiddevelopment.mobile_banking:id/checkingButton")).click();

	}

	@Test(priority = 3)
	public void depositAccount() throws MalformedURLException {
		UiAutomator2Options cap = new UiAutomator2Options();
		driver = new AndroidDriver(new URL(urlPath), cap);

		driver.findElement(By.id("com.androiddevelopment.mobile_banking:id/DepositEditText")).sendKeys("1000");
		driver.findElement(By.id("com.androiddevelopment.mobile_banking:id/DepositButton")).click();

		WebElement validateDeposit = driver.findElement(By.xpath("//*[@text='$6,000.00']"));
		// System.out.println(getTitle);
		if (validateDeposit.isDisplayed()) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
		if (validateDeposit.isDisplayed()) {
			test.log(LogStatus.PASS, "Deposit Amount Succeed, Account Balance Updated", "success");
		} else {
			test.log(LogStatus.FAIL, "Deposit Amount Failed", "failed");
		}

	}

	@AfterSuite
	public void afterclass() {
		ExtentReport.closeExtentReport();
	}
}
