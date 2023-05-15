package Utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentReport {

	static ExtentTest test;
	static ExtentReports report;

	static String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

	public static ExtentTest generateExtentReport(String nameExtent) {

		File file = new File(nameExtent + " - " + dateName);
		file.mkdir();
		report = new ExtentReports(nameExtent + " - " + dateName + "/ExecutionReport.html");
		test = report.startTest("Capstone Extent Report " + nameExtent);
		return test;
	}

	public static void closeExtentReport() {
		report.endTest(test);
		report.flush();
	}

}
