package testiPhoneAndTablet;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import Utils.ExtentReport;
import iPhone.IOSTabletLaunch;

public class IOSTabletTestUI extends IOSTabletLaunch{

	@BeforeSuite
	public void beforeclass() {
		test = ExtentReport.generateExtentReport("IOS Tablet Extent");
	}

	@Test(priority = 1)
	public void UI_1() throws FileNotFoundException, IOException, ParseException {
		IOSTabletLaunch.configureApp();
		IOSTabletLaunch.scroll();
	}

	@Test(priority = 2)
	public void UI_2() throws FileNotFoundException, IOException, ParseException {
		IOSTabletLaunch.clickActivityIndicator();

	}

	@Test(priority = 3)
	public void UI_3() throws FileNotFoundException, IOException, ParseException {

		IOSTabletLaunch.clickDatePicker();

	}

	@Test(priority = 4)
	public void UI_4() throws FileNotFoundException, IOException, ParseException {

		IOSTabletLaunch.clickImageView();

	}

	@Test(priority = 5)
	public void UI_5() throws FileNotFoundException, IOException, ParseException {

		IOSTabletLaunch.clickPageControl();

	}

	@Test(priority = 6)
	public void UI_6() throws FileNotFoundException, IOException, ParseException {

		IOSTabletLaunch.clickPickerView();

	}

	@Test(priority = 7)
	public void UI_7() throws FileNotFoundException, IOException, ParseException, InterruptedException {

		IOSTabletLaunch.clickProgressView();

	}

	@Test(priority = 8)
	public void UI_8() throws FileNotFoundException, IOException, ParseException, InterruptedException {

		IOSTabletLaunch.clickSearch();

	}

	@Test(priority = 9)
	public void UI_9() throws FileNotFoundException, IOException, ParseException, InterruptedException {

		IOSTabletLaunch.clickSegmentedControls();
	}

	@Test(priority = 10)
	public void UI_010() throws FileNotFoundException, IOException, ParseException, InterruptedException {

		IOSTabletLaunch.clickSliders();

	}

	@Test(priority = 11)
	public void UI_011() throws FileNotFoundException, IOException, ParseException, InterruptedException {

		IOSTabletLaunch.clickStackViews();
	}

	@Test(priority = 12)
	public void UI_012() throws FileNotFoundException, IOException, ParseException, InterruptedException {

		IOSTabletLaunch.clickSwitches();
	}

	@Test(priority = 13)
	public void UI_013() throws FileNotFoundException, IOException, ParseException, InterruptedException {

		IOSTabletLaunch.clickTextFields();
	}

	@Test(priority = 14)
	public void UI_014() throws FileNotFoundException, IOException, ParseException, InterruptedException {

		IOSTabletLaunch.clickToolBars();

	}

	@Test(priority = 15)
	public void UI_015() throws FileNotFoundException, IOException, ParseException, InterruptedException {

		IOSTabletLaunch.clickWebViews();

	}

	@Test(priority = 16)
	public void UI_020() throws FileNotFoundException, IOException, ParseException, InterruptedException {

		IOSTabletLaunch.clickAlertViews();

	}

	@AfterSuite
	public void afterclass() {
		ExtentReport.closeExtentReport();
	}
}
