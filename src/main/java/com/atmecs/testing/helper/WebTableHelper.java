package com.atmecs.testing.helper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
/**
 * This class is used as common utility for web table reading and web table data position reading web table validation. 
 * @author ajith.periyasamy
 *
 */
public class WebTableHelper {
	WaitForElement waitobject=new WaitForElement();
	SeleniumHelper seleniumhelp=new SeleniumHelper();
	/**
	 * This rowCounter method is take the below parameters
	 * @param driver
	 * @param locator
	 * and count the no of rows in the table and send to the list
	 * 
	 * finally @return the length of the list
	 */
	public int rowCounter(WebDriver driver,String locator) {
		List<WebElement> countlist=waitobject.WaitForFluent(driver,locator).findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
		return countlist.size();
	}
	/**
	 * This columnCounter method take the below parameters
	 * @param driver
	 * @param locator
	 * and count the column in the table and stored in the web element list.
	 * finally @return the count of the list.
	 */
	public int columnCounter(WebDriver driver,String locator) {
		List<WebElement> countlist=waitobject.WaitForFluent(driver,locator).findElement(By.tagName("tbody")).findElement(By.tagName("tr")).findElements(By.tagName("td"));
		return countlist.size();
	}
	/**
	 * This tableDataReader method take the below parameters
	 * @param driver
	 * @param locator
	 * and read the values based on the row and column value.
	 * finally print the values. 
	 */
	public void tableDataReader(WebDriver driver,String locator) {
		int rowcount=rowCounter(driver,locator);
		int colcount=rowCounter(driver,locator);
		for(int initial=0; initial<=rowcount; initial++) {
			for(int count=0; count<=colcount; count++) {
				String tableData=waitobject.WaitForFluent(driver, locator).findElement(By.tagName("tbody")).findElements(By.tagName("tr")).get(initial).findElements(By.tagName("td")).get(count).getText();
				System.out.print(tableData+"\t |");
			}
			System.out.println();
		}
	}
	/**
	 * this tablePositionOfData method take the below parameters
	 * @param driver
	 * @param locator
	 * @param celldata
	 * and read the values from the web table.
	 * check the given values is equal to the  table value.
	 * if the values is equal 
	 * finally @return the position of row an column.
	 */
	public String tablePositionOfData(WebDriver driver,String locator,String celldata) {
		String tabledata,rowcolindex = null;
		int rowcount=rowCounter(driver,locator);
		int colcount=rowCounter(driver,locator);
		for(int initial=0; initial<=rowcount; initial++) {
			for(int count=0; count<=colcount; count++) {
				tabledata=waitobject.WaitForFluent(driver, locator).findElement(By.tagName("tbody")).findElements(By.tagName("tr")).get(initial).findElements(By.tagName("td")).get(count).getText();
				if(celldata.contentEquals(tabledata)) 
				{
					rowcolindex="rowindex = "+(initial+1)+" columnindex = "+(count+1);
				}
			}
		}
		return rowcolindex;
	}
	/**
	 * This getCellData method take the below parameters
	 * @param driver
	 * @param locator
	 * @param colnum
	 * @param rownum
	 * and find the value in the given row and column number
	 * finally @return the data. 
	 */
	public String getCellData(WebDriver driver,String locator,int colnum,int rownum) {
		String tabledata = null;
		int rowcount=rowCounter(driver,locator);
		int colcount=rowCounter(driver,locator);
		for(int initial=0; initial<=rowcount; initial++) {
			for(int count=0; count<=colcount; count++) {
				if(colnum==count && rownum==initial) {
					tabledata=waitobject.WaitForFluent(driver, locator).findElement(By.tagName("tbody")).findElements(By.tagName("tr")).get(initial).findElements(By.tagName("td")).get(count).getText();
				}
			}
		}
		return tabledata;
	}
	/**
	 * This getRowHeadData method take the below parameters
	 * @param driver
	 * @param locator
	 * and find the value in the header row and print array.
	 * finally @return the data.
	 */
	public void getRowHeadData(WebDriver driver,String locator) {
		String tabledata = null;
		int rowcount=rowCounter(driver,locator);
		int colcount=rowCounter(driver,locator);
		for(int initial=0; initial<=rowcount; initial++) {
			for(int count=0; count<=colcount; count++) {
					tabledata=waitobject.WaitForFluent(driver, locator).findElement(By.tagName("thead")).findElements(By.tagName("tr")).get(initial).findElements(By.tagName("th")).get(count).getText();
				System.out.println(tabledata+"\t |");
			}
		}
	}
	

}
