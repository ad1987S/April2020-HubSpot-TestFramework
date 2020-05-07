package com.qa.hubspot.util;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.base.BasePage;

public class ElementUtil extends BasePage {

	WebDriver driver;
	WebDriverWait wait;
	JavaScriptUtil js;

	public ElementUtil(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 20);
		 js= new JavaScriptUtil(driver);
	}

	/*
	 * this method is used to create the webElement on the basis of BY locator
	 * 
	 * @Param locator
	 * 
	 * @Return element
	 */

	public WebElement getElement(By locator) {

		WebElement element = null;

		try {

			element = driver.findElement(locator);
			js.flash(element);
			
		} catch (Exception e) {
			System.out.println("Some Exception got occured while creating the Web Element");
		}
		return element;
	}

	/*
	 * this method is used to click on Web element
	 */

	public void doClick(By locator) {

		try {

			driver.findElement(locator).click();
		} catch (Exception e) {
			System.out.println("Some Exception got occured while clicking on the Web Element");
		}
	}

	public void doSend(By locator, String value) {

		try {

			WebElement ele = getElement(locator);
			ele.clear();
			ele.sendKeys(value);

		} catch (Exception e) {
			System.out.println("Some Exception got occured while entering values in a field ");
		}
	}

	public boolean doIsDisplayed(By locator) {

		try {

			return driver.findElement(locator).isDisplayed();

		} catch (Exception e) {
			System.out.println("Some Exception got occured while displaying element ");
		}
		return false;
	}

	public String doGetText(By locator) {

		try {

			return driver.findElement(locator).getText();

		} catch (Exception e) {
			System.out.println("Some Exception got occured while getting Text ");
		}
		return null;
	}

	public String doGetPageTitle() {

		try {

			return driver.getTitle();

		} catch (Exception e) {
			System.out.println("Some Exception got occured while getting page title");
		}
		return null;
	}

	public boolean waitForElementVisible(By Locator) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(Locator));

		return true;
	}
	public boolean waitForElementPresent(By locator) {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return true;
	}

	public boolean waitForTitle(String title) {

		wait.until(ExpectedConditions.titleIs(title));

		return true;
	}

	public void getDropDownValues(By locator) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		System.out.println("total values in drop down: " + optionsList.size());
		for (int i = 0; i < optionsList.size(); i++) {
			System.out.println(optionsList.get(i).getText());
		}
	}

	public void selectDropDown(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(value);

	}

	/**
	 * 
	 * @param locator
	 * @param index
	 */
	public void selectDropDown(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	/**
	 * 
	 * @param locator
	 * @return
	 */
	public List<String> getDropDownValuesList(By locator) {
		List<String> ar = new ArrayList<String>();
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		for (int i = 0; i < optionsList.size(); i++) {
			ar.add(optionsList.get(i).getText());
		}

		return ar;

	}

	/**
	 * this method is used to select the value from the drop down - without using
	 * Select class in Selenium
	 * 
	 * @param locator
	 * @param locatorValue
	 * @param value
	 */
	public void doSelectValueFromDropDownWithOutSelect(String locator, String locatorValue, String value) {
		List<WebElement> valuesList = null;
		if (locator.equals("xpath")) {
			valuesList = driver.findElements(By.xpath(locatorValue));
		} else if (locator.equals("css")) {
			valuesList = driver.findElements(By.cssSelector(locatorValue));
		}

		System.out.println(valuesList.size());

		for (int i = 0; i < valuesList.size(); i++) {
			if (valuesList.get(i).getText().equals(value)) {
				valuesList.get(i).click();
				break;
			}
		}

	}
}
