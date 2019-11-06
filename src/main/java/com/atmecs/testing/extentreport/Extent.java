package com.atmecs.testing.extentreport;
import java.io.File;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.atmecs.testing.constants.FileConstants;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * This the Extent report class is for generate  the report for the project in web application manner 
 * and give pie chart of project application with Test failures and Test passes with the screenshot.  
 * @author ajith.periyasamy
 */
public class Extent implements ITestListener{
	public ExtentReports extentObject = new ExtentReports(FileConstants.EXTENT_OUPUT_PATH, true);;
	public ExtentTest extentlogger;
	/**
	 * This startReport is method is used to load the configuration files
	 * and create the Extent.html file for Extent report.
	 */
	public Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}
	/**
	 * This getScreenshot method takes below parameters:
	 * @param driver
	 * @param testname
	 * and return the screenshot image destination path as a String .
	 * @return
	 * @throws Exception
	 */
	public static String getScreenshot(WebDriver driver, String testname) throws Exception {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = FileConstants.SCREENSHOT_PATH + testname+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	@Override
	public void onTestStart(ITestResult result) {
		extentlogger=extentObject.startTest(result.getMethod().getMethodName()+":"+Thread.currentThread().getName());
		extentlogger.log(LogStatus.INFO, "Test Starting..."+result.getMethod().getMethodName());
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		for(String logmessage:Reporter.getOutput(result)) {
			extentlogger.log(LogStatus.INFO, logmessage);
		}
		extentlogger.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());
		String screenshotPath;
		try {
			screenshotPath = Extent.getScreenshot(ThreadPool.get(), result.getName());
			extentlogger.log(LogStatus.PASS, extentlogger.addScreenCapture(screenshotPath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	@Override
	public void onTestFailure(ITestResult result) {
		extentlogger.log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getName()); 
		extentlogger.log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getThrowable());
		String screenshotPath;
		try {
			screenshotPath = Extent.getScreenshot(ThreadPool.get(), result.getName());
			extentlogger.log(LogStatus.FAIL, extentlogger.addScreenCapture(screenshotPath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		for(String logmessage:Reporter.getOutput(result)) {
			extentlogger.log(LogStatus.INFO, logmessage);
		}
		extentlogger.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		
	}
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onFinish(ITestContext context) {
		extentObject.endTest(extentlogger);
		extentObject.flush();
	}
}