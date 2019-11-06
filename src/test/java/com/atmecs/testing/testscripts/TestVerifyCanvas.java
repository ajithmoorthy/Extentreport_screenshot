package com.atmecs.testing.testscripts;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import com.atmecs.testing.constants.FileConstants;
import com.atmecs.testing.helper.SeleniumHelper;
import com.atmecs.testing.helper.ValidaterHelper;
import com.atmecs.testing.helper.WaitForElement;
import com.atmecs.testing.logreports.LogReporter;
import com.atmecs.testing.testbase.TestBase;
import com.atmecs.testing.utils.PropertiesReader;

public class TestVerifyCanvas  extends TestBase{
	SeleniumHelper seleniumhelp=new SeleniumHelper();
	PropertiesReader propread=new PropertiesReader();
	WaitForElement waitobject=new WaitForElement();
	ValidaterHelper validate=new ValidaterHelper();
	LogReporter log=new LogReporter();
	@Test
	public void verifyCanvas() throws IOException, InterruptedException 
	{
		Properties prop1=propread.KeyValueLoader(FileConstants.LOCATORS_PATH);
		waitobject.waitForPageLoadTime(driver);
		log.logReportMessage("jdhghhg");
		waitobject.waitForElementToBeClickable(driver, prop1.getProperty("loc.txtfield.fromloc"));
		log.logReportMessage("ajith");
		seleniumhelp.sendKeys(prop1.getProperty("loc.txtfield.fromloc"), driver, "Chennai (All Locations)");
		log.logReportMessage("moorthy");
		seleniumhelp.sendKeys(prop1.getProperty("loc.txtfield.toloc"), driver, "Trichy");
		seleniumhelp.sendKeysEnter(prop1.getProperty("loc.txtfield.toloc"),driver, Keys.TAB);
		seleniumhelp.clickElement(driver,prop1.getProperty("loc.date.from"));
		seleniumhelp.clickElement(driver,prop1.getProperty("loc.day.from"));
		//seleniumhelp.sendKeysEnter(prop1.getProperty("loc.date.from"),driver, Keys.TAB);
		//seleniumhelp.clickElement(driver, prop1.getProperty("loc.day.to"));
		//seleniumhelp.sendKeysEnter(prop1.getProperty("loc.date.to"),driver, Keys.TAB);
//		WebElement element=waitobject.WaitForFluent(driver, prop1.getProperty("loc.btn.search"));
//		Actions action=new Actions(driver);
//		action.moveToElement(element).perform();
//		action.moveToElement(element).click().build().perform();
//		seleniumhelp.scrollPageMethod(driver,prop1.getProperty("loc.btn.viewseats"));
//		seleniumhelp.clickElement(driver,prop1.getProperty("loc.btn.viewseats"));
//		seleniumhelp.scrollPageMethod(driver, prop1.getProperty("loc.canvas.lower"));
//		seleniumhelp.mouseOver(driver,prop1.getProperty("loc.canvas.lower"));
//		action.moveByOffset(100,217).click().perform();
	}
}
