package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.AppConstant;
import com.qa.hubspot.util.ElementUtil;

public class HomePage extends BasePage {

	WebDriver driver;
	ElementUtil ElementUtil;

	public HomePage(WebDriver driver) {

		this.driver = driver;
		ElementUtil = new ElementUtil(driver);
	}

	By AccountName = By.xpath("//span[contains(text(),'Ape')]");

	By header = By.xpath("//*[contains(text(),'Restart demo')]");

	By pagetitle = By.xpath("//span[contains(text(),'Create dashboard')]");

	By mainContactsLink = By.id("nav-primary-contacts-branch");
	By childContactsLink = By.id("nav-secondary-contacts");

	public String getTitle() {

		ElementUtil.waitForTitle(AppConstant.Home_Page_Title);
		String title = ElementUtil.doGetPageTitle();

		return title;

	}

	public String getUserName() {

		return ElementUtil.getElement(AccountName).getText();

	}

	public boolean getHeader() {

		return ElementUtil.getElement(header).isDisplayed();

	}

	public void clickOnContacts() {
		ElementUtil.waitForElementPresent(mainContactsLink);
		ElementUtil.doClick(mainContactsLink);

		ElementUtil.waitForElementPresent(childContactsLink);
		ElementUtil.doClick(childContactsLink);

	}

	public ContactsPage goToContactsPage() {
		clickOnContacts();
		return new ContactsPage(driver);
	}

}
