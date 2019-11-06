package com.atmecs.testing.helper;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import com.atmecs.testing.constants.FileConstants;

/**
 * This SeleniumHelper class is used to help or provide the predefined program  or method for the Selenium automation.
 * class contains the object of the By class  like xpath,id,name etc.
 * this class contains the drop down method
 * clickElementmethod
 * sendKeys method
 * mouseOver method
 * mouseoverClick method
 * scrollPage method
 * login method 
 * @author ajith.periyasamy
 */


public class SeleniumHelper {
	WaitForElement wait=new WaitForElement();
	By by;
	/**
	 * This matchElement method take input as below parameters:
	 * @param locators
	 * and perform the separate the locators and options.
	 * using that locators create the Object of By class
	 * and @return by object.
	 */
	public By matchElement(String locators) {
		String[] input=locators.split(",");
		switch(input[0].toUpperCase())
		{
		case "XPATH":
			by=By.xpath(input[1]);
			break;
		case "ID":
			by=By.id(input[1]);
			break;
		case "NAME":
			by=By.name(input[1]);
			break;
		case "CSS":
			by=By.cssSelector(input[1]);
			break;
		case "CLASS":
			by=By.className(input[1]);
			break;
		case "LINK_TEXT":
			by=By.linkText(input[1]);
			break;
		case "PARTIAL_LINK_TEXT":
			by=By.partialLinkText(input[1]);
			break;
		case "TAG_NAME":
			by=By.tagName(input[1]);
			break;
		}
		return by;
	}
	/**
	 * This clickElement method take the below inputs  
	 * @param webdriver
	 * @param locator
	 * and perform the click Operation.
	 */
	public void clickElement(WebDriver webdriver,String locator) {
		try {
			WebElement element=wait.WaitForFluent(webdriver, locator);
			element.click();
		}catch (Exception e) {
			System.out.println("Element is not available or not clickable");
		}
	}
	/**
	 * This sendKeys method take the input of below parameters.
	 * @param locator
	 * @param webdriver
	 * @param value
	 * and send the value to the webElements using the sendKeys method.
	 */
	public void sendKeys(String locator, WebDriver webdriver,String value) {
		try {
			WebElement element=wait.WaitForFluent(webdriver, locator);
			element.sendKeys(value);
		}catch (Exception e) {
			System.out.println("Element is not available or not clickable");
		}
	}
	/**
	 *This mouseOver method take the below parameters:
	 * @param locator
	 * @param webdriver
	 * and perform the mouse over operation on the specific webElement using the Action class method moveToElement and perform.
	 */
	public void mouseOver(WebDriver webdriver,String locator) {	
		try {
			Actions actions = new Actions(webdriver);
			WebElement element=wait.WaitForFluent(webdriver, locator);
			actions.moveToElement(element).perform();
		}catch (Exception e) {
			System.out.println("Element is not available or not clickable");
		}
	}
	/**
	 * This dropDown method take the parameters:
	 * @param locators
	 * @param webdriver
	 * @param index
	 * and perform the selection operation on a specific drop down web element using the Select class.
	 */
	public void dropDown(String locators, WebDriver webdriver, int index) {
		try {
			WebElement element=wait.WaitForFluent(webdriver, locators);
			Select select = new Select(element);
			select.selectByIndex(index);
		}catch (Exception e) {
			System.out.println("Element is not available or not clickable");
		}	
	}
	/**
	 * THis winHandler takes the below parameter:
	 * @param driver
	 * and handle the browser window during the web automation driver want to move the another page.
	 * this method uses the getwindowHandles method to handle the Window.  
	 *  and @return the driver object.
	 */
	public WebDriver winHandler(WebDriver driver) {
		String window_array[]=new String[5];
		Set<String> windows=driver.getWindowHandles();
		int initial=0;
		for (String win:windows)
		{
			window_array[initial]=win;
		}
		driver=driver.switchTo().window(window_array[0]);
		return driver;
	}
	/**
	 * This loginMethod method take the below parameters.
	 * @param webdriver
	 * @param loc_username
	 * @param loc_password
	 * @param submit
	 * @param input_Username
	 * @param input_Password
	 * and perform the login operation using the sendKeys,clickElement method. 
	 */
	public void loginMethod(WebDriver webdriver,String loc_username,String loc_password,String loc_submit,String input_Username,String input_Password) {
		sendKeys(loc_username,webdriver, input_Username);
		sendKeys(loc_password,webdriver, input_Password);
		clickElement(webdriver, loc_submit);	
	}
	/**
	 * This sendKeysDropDowm method take the below parameters
	 * @param webdriver
	 * @param locator
	 * @param optionRoot
	 * @param value
	 * and perform the select operation from the drop down menu using the Action class and methods.
	 */
	public void sendKeysDropDown(WebDriver webdriver,String locator,String optionRoot,String value) {
		try {
			sendKeys(locator,webdriver,value);
			Actions action=new Actions(webdriver);
			@SuppressWarnings("deprecation")
			Wait<WebDriver> wait = new FluentWait<WebDriver>(webdriver)
			.withTimeout(FileConstants.FLUENT_WAIT, TimeUnit.SECONDS)
			.pollingEvery(FileConstants.FLUENT_POLL, TimeUnit.SECONDS)
			.ignoring(NoSuchElementException.class);
			List<WebElement> elementlist = wait.until(new Function<WebDriver, List<WebElement>>() 
			{
				public List<WebElement> apply(WebDriver driver) {
					return driver.findElements(matchElement(locator));
				}
			});
			for(WebElement  element:elementlist) {
				System.out.println(element.getText());
				if(element.getText().equalsIgnoreCase(value)) 
				{
					action.moveToElement(element).perform();
					element.click();
				}
			}}
		catch(Exception e){
			System.out.println("Element is not available or not clickable");
		}
	}
	/**
	 *This scrollPage method take the below:
	 * @param driver
	 * and scroll the web page using java script Executor.
	 */
	public void scrollPage(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,10000)");
	}
	/**
	 *This scrollPage method take the below:
	 * @param driver
	 * and scroll the web page using java script Executor.
	 */
	public void scrollPage(WebDriver driver,int index) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,"+index+")");
	}
	/**
	 * This scrollPageMethod  method take input as below parameters:
	 * @param driver
	 * @param locator
	 * and perform scroll operation in web page using the javascriptExecutor. 
	 */
	public void scrollPageMethod(WebDriver driver,String locator) {
		try {
			JavascriptExecutor js = ((JavascriptExecutor) driver);
			WebElement element=wait.WaitForFluent(driver, locator);
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			scrollPage(driver, -200);
		}catch(Exception e){
			System.out.println("Element is not available or not clickable");
		}
	}
	/**
	 * This ElementPresent method take the below parameters:
	 * @param driver
	 * @param locator
	 * and check the element is present or displayed 
	 * and @return the boolean true or false.
	 */
	public boolean ElementPresent(WebDriver driver,String locator) 
	{
		boolean bool = false;
		try {
			wait.waitForElementToBeClickable(driver, locator);
			bool=driver.findElement(matchElement(locator)).isDisplayed();
		}
		catch(Exception e){
			System.out.println("Element is not available or not clickable");
		}
		return bool;
	}
	/**
	 * This sendKeysEnter method take the input of below parameters.
	 * @param locator
	 * @param webdriver
	 * @param enter
	 * and send the value to the webElements using the sendKeys method.
	 */
	public void sendKeysEnter(String locator, WebDriver webdriver,Keys enter) {
		try {
			WebElement element=wait.WaitForFluent(webdriver, locator);
			element.sendKeys(enter);
		}catch (Exception e) {
			System.out.println("Element is not available or not clickable");
		}
	}
}
