package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.AppConstant;
import com.qa.hubspot.util.Credentials;
import com.qa.hubspot.util.ElementUtil;

public class LoginPage extends BasePage {

	WebDriver driver;
	ElementUtil ElementUtil;

	public LoginPage(WebDriver driver) {

		this.driver = driver;

		ElementUtil = new ElementUtil(driver);
	}

	By user = By.id("username");
	By pass = By.id("password");
	By login = By.id("loginBtn");
	By signUpLink = By.linkText("Sign up");
	By checkbox = By.xpath(
			"//span[@class='private-checkbox__icon private-checkbox__dash']//*[name()='path' and contains(@class,'private-ch')]");
	By Forgotpass = By.xpath("//span[contains(text(),'Show Password')]");
	By message = By.xpath("//i18n-string[contains(text(),\"Don't have an account?\")]");
	By signInWith_Google = By.xpath("//span[@class='buttonText' and contains(text(),'Sign')]");
	By privacy = By.xpath("//i18n-string[contains(text(),'Privacy Policy')]");
	By error = By.xpath("//h5[contains(text(),'That email')]");

	public String getTitle() {
		ElementUtil.waitForTitle(AppConstant.Login_Page_Title);
		return ElementUtil.doGetPageTitle();

	}

	public boolean checkSignUPLink() {

		return ElementUtil.doIsDisplayed(signUpLink);
	}

	/*
	 * this method will perform Login on Login page and will give object of Home
	 * page so that HomePageTest class can get driver
	 */
	public HomePage doLogin(Credentials Credentials) {
		
		ElementUtil.waitForElementVisible(user);

		ElementUtil.doSend(user, Credentials.getUsername());
		ElementUtil.doSend(pass, Credentials.getPassword());
		ElementUtil.doClick(login);

		return new HomePage(driver);

	}

	public boolean checkMessageLink() {

		return ElementUtil.doIsDisplayed(message);

	}

	public void clickForgotPassCheckBox() {

		ElementUtil.doClick(checkbox);
	}

	public boolean checkSignInWithGoogle() {

		return ElementUtil.doIsDisplayed(signInWith_Google);

	}

	public boolean clickPrivacyPolicyLink() {
		return ElementUtil.doIsDisplayed(privacy);

	}
	
	public boolean getError() {

		 return ElementUtil.getElement(error).isDisplayed();

	}

}