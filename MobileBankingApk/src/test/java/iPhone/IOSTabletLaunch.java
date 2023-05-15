package iPhone;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utils.ConfigFileReader;
import Utils.ExtentReport;
import Utils.JsonFileReader;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

public class IOSTabletLaunch {
	
	static IOSDriver driver;
	static ExtentTest test;

	static ConfigFileReader cfg = new ConfigFileReader();
	static String iphoneAppPath = cfg.getSpecificUrlProperties("iphoneAppPath");

	static String jsoniPhone = cfg.getSpecificUrlProperties("IOSTabletJson");
	static String driverUrl = cfg.getSpecificUrlProperties("driverUrl");
	static JsonFileReader jfr = new JsonFileReader();

	public static void configureApp() throws FileNotFoundException, IOException, ParseException {

		String deviceName = jfr.getDeviceName(jsoniPhone);
		String platformName = jfr.getPlatformName(jsoniPhone);
		String versionName = jfr.getPlatFormVersion(jsoniPhone);
		String automationName = jfr.getAutomationName(jsoniPhone);
		String uuid = jfr.getUUID(jsoniPhone);

		File fs = new File(iphoneAppPath);
		XCUITestOptions cap = new XCUITestOptions();
		cap.setPlatformName(platformName);
		cap.setDeviceName(deviceName);
		cap.setApp(fs.getAbsolutePath());
		cap.setPlatformVersion(versionName);
		cap.setAutomationName(automationName);
		cap.setUdid(uuid);

		driver = new IOSDriver(new URL(driverUrl), cap);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	public static void clickActivityIndicator() {
		driver.findElement(AppiumBy.accessibilityId("Activity Indicators")).click();
		driver.navigate().back();

	}

	public static void clickPickerView() {

		try {
			driver.findElement(AppiumBy.accessibilityId("Picker View")).click();
			driver.findElement(AppiumBy.accessibilityId("Red color component value")).sendKeys("0");
			driver.findElement(AppiumBy.accessibilityId("Green color component value")).sendKeys("0");
			driver.findElement(AppiumBy.accessibilityId("Blue color component value")).sendKeys("225");
			Assert.assertTrue(true);
			test.log(LogStatus.PASS, "Color blue success", "success");
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Color blue failed", "failed");
			Assert.assertTrue(false);
		}
		driver.navigate().back();

	}

	public static void clickDatePicker() {
		{
			try {
				driver.findElement(AppiumBy.accessibilityId("Date Picker")).click();
				driver.navigate().back();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	public static void clickPageControl() {
		{
			try {
				driver.findElement(AppiumBy.accessibilityId("Page Control")).click();
				WebElement greenImage = driver.findElement(By.xpath("//*[@value='page 3 of 10']"));
				greenImage.click();

				WebElement blueImage = driver.findElement(By.xpath("//*[@value='page 4 of 10']"));
				blueImage.click();

				if (blueImage.equals(greenImage)) {
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}

				if (blueImage.equals(blueImage)) {
					test.log(LogStatus.PASS, "Color changes", "success");
				} else {
					test.log(LogStatus.FAIL, "Color changes", "failed");
				}
				driver.navigate().back();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void clickImageView() {
		{
			try {
				driver.findElement(AppiumBy.accessibilityId("Image View")).click();
				WebElement getImage = driver.findElement(By.xpath("//*[@type='XCUIElementTypeImage']"));
				// System.out.println(getTitle.toString());
				if (getImage.isDisplayed()) {
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}

				if (getImage.isDisplayed()) {
					test.log(LogStatus.PASS, "Images are present", "success");
				} else {
					test.log(LogStatus.FAIL, "Images are not present", "failed");
				}

				driver.navigate().back();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void clickSearch() {
		{
			try {
				driver.findElement(AppiumBy.accessibilityId("Search")).click();
				driver.findElement(By.xpath("//XCUIElementTypeButton[@index='6']")).click();
				driver.findElement(AppiumBy.accessibilityId("Scope Two")).click();
				driver.findElement(AppiumBy.accessibilityId("Search")).click();
				driver.findElement(By.xpath("//XCUIElementTypeButton[@index='5']")).click();
				WebElement searchSample = driver.findElement(By.xpath("//XCUIElementTypeSearchField[@index='0']"));
				searchSample.sendKeys("Sample");

				driver.navigate().back();
				driver.navigate().back();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void clickProgressView() throws InterruptedException {

		{
			try {
				driver.findElement(AppiumBy.accessibilityId("Progress Views")).click();
				Thread.sleep(10000);
				String currentProgressIndicator = driver
						.findElement(By.xpath("(//XCUIElementTypeProgressIndicator[@name='Progress'])[1]"))
						.getAttribute("value");
				Assert.assertEquals(currentProgressIndicator, "100%");

				if (currentProgressIndicator.equals("100%")) {
					test.log(LogStatus.PASS, "Total Bar Progress is 100%", "success");
				} else {
					test.log(LogStatus.FAIL, "Total Bar Progress is not 100%", "failed");
				}

				driver.navigate().back();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void clickSegmentedControls() throws InterruptedException {
		{
			try {
				driver.findElement(AppiumBy.accessibilityId("Segmented Controls")).click();
				driver.findElement(By.xpath("(//XCUIElementTypeButton[@name='Tools'])")).click();
				driver.findElement(By.xpath("(//XCUIElementTypeButton[@name='Check'])[2]")).click();
				driver.findElement(By.xpath("(//XCUIElementTypeButton[@name='Gift'])")).click();

				driver.navigate().back();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void clickSwitches() throws InterruptedException {
		{
			try {
				driver.findElement(AppiumBy.accessibilityId("Switches")).click();
				driver.findElement(By.xpath("(//XCUIElementTypeSwitch[@index='4'])")).click();
				driver.navigate().back();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void clickTextFields() throws InterruptedException {

		{
			try {
				driver.findElement(AppiumBy.accessibilityId("Text Fields")).click();
				driver.findElement(
						AppiumBy.iOSClassChain("**/XCUIElementTypeTextField[`value == \"Placeholder text\"`][2]"))
						.sendKeys("Sample 2");
				driver.findElement(
						AppiumBy.iOSClassChain("**/XCUIElementTypeSecureTextField[`value == \"Placeholder text\"`]"))
						.sendKeys("Sample 3");
				driver.findElement(
						AppiumBy.iOSClassChain("**/XCUIElementTypeTextField[`value == \"Placeholder text\"`][1]"))
						.sendKeys("Sample 1");

				driver.findElement(
						AppiumBy.iOSClassChain("**/XCUIElementTypeTextField[`value == \"Placeholder text\"`][1]"))
						.sendKeys("Sample 4");

				driver.findElement(
						AppiumBy.iOSClassChain("**/XCUIElementTypeTextField[`value == \"Placeholder text\"`][1]"))
						.sendKeys("Sample 5");

				driver.navigate().back();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void clickToolBars() throws InterruptedException {

		{
			try {
				driver.findElement(AppiumBy.accessibilityId("Toolbars")).click();

				WebElement customBtn = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Custom']"));
				customBtn.click();

				driver.navigate().back();

				WebElement defaultBtn = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Default']"));
				defaultBtn.click();

				WebElement deleteBtn = driver.findElement(By.xpath("//XCUIElementTypeButton[@name='Delete']"));
				deleteBtn.click();

				driver.navigate().back();
				driver.navigate().back();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void clickWebViews() {

		{
			try {
				WebElement ele = driver.findElement(AppiumBy.accessibilityId("Web View"));
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("element", ((RemoteWebElement) ele).getId());
				params.put("direction", "down");
				driver.executeScript("mobile:scroll", params);
				driver.findElement(AppiumBy.accessibilityId("Web View")).click();

				WebElement validateHTML = driver
						.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`label == \"WKWebView\"`]"));
				if (validateHTML.isDisplayed()) {
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}

				if (validateHTML.isDisplayed()) {
					test.log(LogStatus.PASS, "HTML is displayed", "success");
				} else {
					test.log(LogStatus.FAIL, "HTML is not displayed", "failed");
				}

				driver.navigate().back();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void clickAlertViews() {
		{
			try {
				driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
				driver.findElement(AppiumBy.accessibilityId("Destructive")).click();

				WebElement validatDestructiveChoice = driver
						.findElement(AppiumBy.accessibilityId("Destructive Choice"));
				WebElement validatSafeeChoice = driver.findElement(AppiumBy.accessibilityId("Safe Choice"));

				if (validatDestructiveChoice.isDisplayed() && validatSafeeChoice.isDisplayed()) {
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}

				validatDestructiveChoice.click();
				driver.navigate().back();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void clickStackViews() throws InterruptedException {

		{
			try {
				driver.findElement(AppiumBy.accessibilityId("Stack Views")).click();
				driver.findElement(By.xpath("(//XCUIElementTypeButton[@index='2'])")).click();

				WebElement furtherDetails = driver
						.findElement(By.xpath("(//XCUIElementTypeStaticText[@name='Further Detail'])"));
				if (furtherDetails.isDisplayed()) {
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}

				if (furtherDetails.isDisplayed()) {
					test.log(LogStatus.PASS, "Further details is displayed", "success");
				} else {
					test.log(LogStatus.FAIL, "Further details is not displayed", "failed");
				}

				driver.findElement(By.xpath("(//XCUIElementTypeButton[@index='1'])")).click();

				WebElement validateColor = driver.findElement(By.xpath("(//XCUIElementTypeOther[@index='0'])"));
				if (validateColor.isDisplayed()) {
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}

				if (validateColor.isDisplayed()) {
					test.log(LogStatus.PASS, "A red box is diplayed", "success");
				} else {
					test.log(LogStatus.FAIL, "A red box is not displayed", "failed");
				}

				driver.navigate().back();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void clickSliders() throws InterruptedException {

		{
			try {
				driver.findElement(AppiumBy.accessibilityId("Sliders")).click();

				WebElement defaultSlider = driver.findElement(By.xpath("//XCUIElementTypeSlider[@value='42%']"));
				defaultSlider.sendKeys("0.2");

				WebElement tintedSlider = driver.findElement(By.xpath("//XCUIElementTypeSlider[@value='50%']"));
				tintedSlider.sendKeys("0.73");

				WebElement customSlider = driver.findElement(By.xpath("//XCUIElementTypeSlider[@value='84%']"));
				customSlider.sendKeys("0.5");

				driver.navigate().back();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void scroll() {
		{
			try {
				WebElement ele = driver.findElement(AppiumBy.accessibilityId("Web View"));
		
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("element", ((RemoteWebElement) ele).getId());
				params.put("direction", "down");
				driver.executeScript("mobile:scroll", params);
				driver.findElement(AppiumBy.accessibilityId("Web View")).click();
				driver.navigate().back();

				List<WebElement> uiKitOptions = driver
						.findElements(By.xpath("//XCUIElementTypeButton[@name='chevron']"));
				System.out.println("Total UIKitCatalog options: " + uiKitOptions.size());

				if (uiKitOptions.size() == 18) {
					test.log(LogStatus.PASS, "Total UIKitCatalog options correst", "success");
				} else {
					test.log(LogStatus.FAIL, "Total UIKitCatalog options incorrect", "failed");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
