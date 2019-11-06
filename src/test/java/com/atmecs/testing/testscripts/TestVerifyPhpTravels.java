package com.atmecs.testing.testscripts;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import com.atmecs.testing.constants.FileConstants;
import com.atmecs.testing.helper.SeleniumHelper;
import com.atmecs.testing.helper.ValidaterHelper;
import com.atmecs.testing.helper.WaitForElement;
import com.atmecs.testing.testbase.TestBase;
import com.atmecs.testing.utils.PropertiesReader;

public class TestVerifyPhpTravels extends TestBase
{
SeleniumHelper seleniumhelp=new SeleniumHelper();
PropertiesReader propread=new PropertiesReader();
WaitForElement waitobject=new WaitForElement();
ValidaterHelper validate=new ValidaterHelper();
	@Test
	public void verifyPhpTravels() throws IOException {
		Properties prop1=propread.KeyValueLoader(FileConstants.LOCATORS_PATH);
		driver.manage().window().maximize();
		waitobject.waitForPageLoadTime(driver);
		waitobject.waitForElementToBeClickable(driver,prop1.getProperty("loc.menu.flights"));
		seleniumhelp.mouseOver(driver,prop1.getProperty("loc.menu.flights"));
		seleniumhelp.clickElement(driver,prop1.getProperty("loc.menu.flights") );
		for(int count=0; count<2; count++)
		{
			seleniumhelp.clickElement(driver,prop1.getProperty("loc.btn.positiveadults"));
			seleniumhelp.clickElement(driver,prop1.getProperty("loc.menu.flights"));
		}
		JavascriptExecutor js= (JavascriptExecutor)driver;
		String text=js.executeScript("return document.querySelector(\"div.col-4:nth-child(1) input\").value;").toString();
		System.out.println(text);
		validate.assertValidater(text, "3");
		for(int count=0; count<2; count++)
		{
			seleniumhelp.clickElement(driver,prop1.getProperty("loc.btn.positivechild"));
			seleniumhelp.clickElement(driver,prop1.getProperty("loc.menu.flights"));
		}
		String text1=js.executeScript("return document.querySelector(\"div.col-4:nth-child(2) input\").value;").toString();
		System.out.println(text1);
	}
}
