package testAndroidTablet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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

	
	ConfigFileReader cfg = new ConfigFileReader();
	String urlPath = cfg.getSpecificUrlProperties("driverUrl");
	
	public void generateScreenShots(String info, String status) throws IOException {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "/AndroidTestScreenshots/" + "ScreenShots" + dateName + ".png";
        if(status == "PASS") {
            test.log(LogStatus.PASS, info + test.addScreenCapture(destination));
        }else {
            test.log(LogStatus.FAIL, info + test.addScreenCapture(destination));
        }
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
    }

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
		test.log(LogStatus.PASS, "Login Successfully!");
	}

	@Test(priority = 2)
	public void transferAccount() throws MalformedURLException {

		UiAutomator2Options cap = new UiAutomator2Options();
		driver = new AndroidDriver(new URL(urlPath), cap);

		driver.findElement(By.id("com.androiddevelopment.mobile_banking:id/transferButton")).click();
		test.log(LogStatus.INFO, "Transfer Button Click Successfully!");
	}

	@SuppressWarnings("deprecation")
	@Test(priority = 3, enabled = false)
	public void checkingToSavings() throws IOException {

		UiAutomator2Options cap = new UiAutomator2Options();
		driver = new AndroidDriver(new URL(urlPath), cap);

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.findElement(By.id("com.androiddevelopment.mobile_banking:id/TransferEditText")).sendKeys("1000");
		driver.navigate().back();

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
			generateScreenShots("Checking to Savings is displayed", "PASS");
		} else {
			test.log(LogStatus.FAIL, "Checking to Savings", "failed");
		}
	}

	@SuppressWarnings("deprecation")
	@Test(priority = 3, enabled = true)
	public void savingsToChecking() throws IOException {
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
			generateScreenShots("Savings to Checking is displayed", "PASS");
		} else {
			test.log(LogStatus.FAIL, "Savings to Checking", "failed");
		}

	}

	@AfterSuite
	public void afterclass() {
		ExtentReport.closeExtentReport();
	}
}
