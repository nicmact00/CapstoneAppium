package testAndroidTablet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

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
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class MB_03T extends AndroidTabletLaunch {

	AndroidDriver driver;
	AppiumDriver appiumDriver;
	ConfigFileReader cfg = new ConfigFileReader();
	String urlPath = cfg.getSpecificUrlProperties("driverUrl");

	@BeforeSuite
	public void beforeclass() {
		test = ExtentReport.generateExtentReport("Android Phone - MB_03T");
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
	public void transferAccount() throws MalformedURLException {

		UiAutomator2Options cap = new UiAutomator2Options();
		driver = new AndroidDriver(new URL(urlPath), cap);

		driver.findElement(By.id("com.androiddevelopment.mobile_banking:id/transferButton")).click();

	}

	@SuppressWarnings("deprecation")
	@Test(priority = 3, enabled = true)
	public void checkingToSavings() throws MalformedURLException {

		UiAutomator2Options cap = new UiAutomator2Options();
		driver = new AndroidDriver(new URL(urlPath), cap);

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.findElement(By.id("com.androiddevelopment.mobile_banking:id/TransferEditText")).sendKeys("1000");

		driver.findElement(By.id("com.androiddevelopment.mobile_banking:id/TransferSpinner")).click();

		driver.findElement(By.xpath("//android.widget.TextView[@index='0']")).click();

		driver.findElement(By.id("com.androiddevelopment.mobile_banking:id/TransferButton")).click();

		WebElement validateCheckingToSavings = driver.findElement(By.xpath("//*[@text='From Checking to Savings']"));
		// System.out.println(getTitle);
		if (validateCheckingToSavings.isDisplayed()) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
		
		if (validateCheckingToSavings.isDisplayed()) {
			test.log(LogStatus.PASS, "Checking to Savings", "success");
		} else {
			test.log(LogStatus.FAIL, "Checking to Savings", "failed");
		}
	}

	@SuppressWarnings("deprecation")
	@Test(priority = 3, enabled = false)
	public void savingsToChecking() throws MalformedURLException {
		UiAutomator2Options cap = new UiAutomator2Options();
		driver = new AndroidDriver(new URL(urlPath), cap);

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.findElement(By.id("com.androiddevelopment.mobile_banking:id/TransferEditText")).sendKeys("1000");
		driver.navigate().back();

		driver.findElement(By.id("com.androiddevelopment.mobile_banking:id/TransferSpinner")).click();

		driver.findElement(By.xpath("//android.widget.TextView[@index='1']")).click();

		driver.findElement(By.id("com.androiddevelopment.mobile_banking:id/TransferButton")).click();

		WebElement validateSavingsToCheking = driver.findElement(By.xpath("//*[@text='From Savings to Checking']"));
		// System.out.println(getTitle);
		if (validateSavingsToCheking.isDisplayed()) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
		if (validateSavingsToCheking.isDisplayed()) {
			test.log(LogStatus.PASS, "Savings to Checking", "success");
		} else {
			test.log(LogStatus.FAIL, "Savings to Checking", "failed");
		}

	}

	@AfterSuite
	public void afterclass() {
		ExtentReport.closeExtentReport();
	}
}
