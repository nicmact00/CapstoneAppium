package testAndroidPhone;


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

import Android.AndroidPhoneLaunch;
import Utils.ConfigFileReader;
import Utils.ExcelAccount;
import Utils.ExtentReport;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class MB_01 extends AndroidPhoneLaunch{

	AndroidDriver driver;

	ConfigFileReader cfg = new ConfigFileReader();
	
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
		test = ExtentReport.generateExtentReport("Android Phone - MB_01");
	}

	@Test
	public void MobileBankingLauch() throws FileNotFoundException, IOException, ParseException {
		AndroidPhoneLaunch.configureApp();
	}

	@Test(priority = 1)
	public void inputUsernameAndPassword() throws MalformedURLException, IOException {
		ExcelAccount.excelUsernamePassword();
		test.log(LogStatus.PASS, "Login Successfully!");
		
	}

	@Test(priority = 2)
	public void checkingAccount() throws IOException {

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
		generateScreenShots("Checking Account found", "PASS");
			//test.log(LogStatus.PASS, "Checking Account found", "success");
		} else {
			test.log(LogStatus.FAIL, "Checking Account not found", "failed");
		}

	}

	@AfterSuite
	public void afterclass() {
		ExtentReport.closeExtentReport();
	}

}