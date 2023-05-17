package testAndroidTablet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class MB_02T extends AndroidTabletLaunch {
	AndroidDriver driver;

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
		test = ExtentReport.generateExtentReport("Android Phone - MB_02T");
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
	public void checkingAccount() throws MalformedURLException {
		UiAutomator2Options cap = new UiAutomator2Options();

		driver = new AndroidDriver(new URL(urlPath), cap);

		driver.findElement(By.id("com.androiddevelopment.mobile_banking:id/checkingButton")).click();

	}

	@Test(priority = 3)
	public void depositAccount() throws IOException {
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
			generateScreenShots("Deposit Amount found and Current Balance changed", "PASS");
			//test.log(LogStatus.PASS, "Deposit Amount Succeed, Account Balance Updated", "success");
		} else {
			test.log(LogStatus.FAIL, "Deposit Amount Failed", "failed");
		}

	}

	@AfterSuite
	public void afterclass() {
		ExtentReport.closeExtentReport();
	}
}
