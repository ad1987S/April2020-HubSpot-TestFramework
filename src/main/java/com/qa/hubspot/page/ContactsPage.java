package com.qa.hubspot.page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.ElementUtil;
import com.qa.hubspot.util.JavaScriptUtil;
public class ContactsPage extends BasePage {

	WebDriver driver;
	ElementUtil elementUtil;
	JavaScriptUtil jsUtil;

	By createContactButton = By.xpath("(//button[@type='button']//span[text()='Create contact'])[position()=1]");
	By createContactFormButton = By.xpath("(//button[@type='button']//span[text()='Create contact'])[position()=2]");

	By email = By.xpath("//input[@data-field='email']");
	
	By firstName = By.xpath("//input[@data-field='firstname']");
	By lastName = By.xpath("//input[@data-field='lastname']");
	By jobTitle = By.xpath("//input[@data-field='jobtitle']");

	public ContactsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);
		
	}

	public String getContactsPageTitle() {
		// jsUtil.checkPageIsReady();
		elementUtil.waitForTitle("Contacts");
		return elementUtil.doGetPageTitle();
	}

	//@Step("Create new contact with {0}, {1}, {2}, {3}")
	public void createNewContact(String mail, String FN, String LN, String jobtitle) {
		
		elementUtil.waitForElementPresent(createContactButton);
		elementUtil.doClick(createContactButton);

	
		elementUtil.waitForElementPresent(email);
		elementUtil.doSend(email,mail);
		
		elementUtil.waitForElementPresent(firstName);
		elementUtil.doSend(firstName,FN);

		elementUtil.waitForElementPresent(lastName);
		elementUtil.doSend(lastName,LN);
		
		elementUtil.waitForElementPresent(jobTitle);
		elementUtil.doSend(jobTitle,jobtitle);

		
		jsUtil.clickElementByJS(elementUtil.getElement(createContactFormButton));

	}

}