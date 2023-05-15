package testiPhoneAndTablet;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;

import Utils.ExtentReport;
import iPhone.iPhoneLaunch;

public class iPhoneTestUI extends iPhoneLaunch{

	@BeforeSuite
	public void beforeclass() {
		test = ExtentReport.generateExtentReport("Iphone Extent");
	}

	@Test(priority = 1)
	public void UI_1() throws FileNotFoundException, IOException, ParseException {
		iPhoneLaunch.configureApp();
		iPhoneLaunch.scroll();
	}

	@Test(priority = 2)
	public void UI_2() throws FileNotFoundException, IOException, ParseException {
		iPhoneLaunch.clickActivityIndicator();

	}

	@Test(priority = 3)
	public void UI_3() throws FileNotFoundException, IOException, ParseException {

		iPhoneLaunch.clickDatePicker();

	}

	@Test(priority = 4)
	public void UI_4() throws FileNotFoundException, IOException, ParseException {

		iPhoneLaunch.clickImageView();

	}

	@Test(priority = 5)
	public void UI_5() throws FileNotFoundException, IOException, ParseException {

		iPhoneLaunch.clickPageControl();

	}

	@Test(priority = 6)
	public void UI_6() throws FileNotFoundException, IOException, ParseException {

		iPhoneLaunch.clickPickerView();

	}

	@Test(priority = 7)
	public void UI_7() throws FileNotFoundException, IOException, ParseException, InterruptedException {

		iPhoneLaunch.clickProgressView();

	}

	@Test(priority = 8)
	public void UI_8() throws FileNotFoundException, IOException, ParseException, InterruptedException {

		iPhoneLaunch.clickSearch();

	}

	@Test(priority = 9)
	public void UI_9() throws FileNotFoundException, IOException, ParseException, InterruptedException {

		iPhoneLaunch.clickSegmentedControls();
	}

	@Test(priority = 10)
	public void UI_010() throws FileNotFoundException, IOException, ParseException, InterruptedException {

		iPhoneLaunch.clickSliders();

	}

	@Test(priority = 11)
	public void UI_011() throws FileNotFoundException, IOException, ParseException, InterruptedException {

		iPhoneLaunch.clickStackViews();
	}

	@Test(priority = 12)
	public void UI_012() throws FileNotFoundException, IOException, ParseException, InterruptedException {

		iPhoneLaunch.clickSwitches();
	}

	@Test(priority = 13)
	public void UI_013() throws FileNotFoundException, IOException, ParseException, InterruptedException {

		iPhoneLaunch.clickTextFields();
	}

	@Test(priority = 14)
	public void UI_014() throws FileNotFoundException, IOException, ParseException, InterruptedException {

		iPhoneLaunch.clickToolBars();

	}

	@Test(priority = 15)
	public void UI_015() throws FileNotFoundException, IOException, ParseException, InterruptedException {

		iPhoneLaunch.clickWebViews();

	}

	@Test(priority = 16)
	public void UI_020() throws FileNotFoundException, IOException, ParseException, InterruptedException {

		iPhoneLaunch.clickAlertViews();

	}

	@AfterSuite
	public void afterclass() {
		ExtentReport.closeExtentReport();
	}
}
