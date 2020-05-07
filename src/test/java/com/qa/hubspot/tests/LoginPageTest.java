package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.AppConstant;
import com.qa.hubspot.util.Credentials;
import com.qa.hubspot.util.ElementUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Epic("Epic 1:Create Login Page")
@Feature("Create Login Page")
public class LoginPageTest {

	BasePage bP;
	Properties prop;
	WebDriver driver;
	LoginPage login;
	ElementUtil ElementUtil;
	Credentials userCredentials;

	@BeforeMethod
	public void setUP() {
		bP = new BasePage();
		prop = bP.init_properties();
		String browserName = prop.getProperty("browser");
		driver = bP.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		ElementUtil = new ElementUtil(driver);
		userCredentials = new Credentials(prop.getProperty("username"), prop.getProperty("passname"));
		login = new LoginPage(driver);

	}

	@Test(priority = 1)
    @Description ("Verify Login Page title ")
	@Severity(SeverityLevel.MINOR)
	public void verifyLoginPageTitleTest() throws InterruptedException {
		String title = login.getTitle();
		Assert.assertEquals(title, AppConstant.Login_Page_Title);
	}
	@Test(priority = 2)
	@Description ("Verify Sign up Link")
	@Severity(SeverityLevel.NORMAL)
	public void verifySignUpLinkTest() {
		boolean flag = login.checkSignUPLink();
		Assert.assertTrue(flag);
	}

	@Test(priority = 3)
	public void verifymessageTest() {
		boolean flag = login.checkMessageLink();
		Assert.assertTrue(flag);
	}

	@Test(priority = 4)
	public void verifyforgotPasswordCheckBoxTest() {
		login.clickForgotPassCheckBox();

	}

	@Test(priority = 5)
	public void verifySignInWithGoogleTest() {
		boolean flag = login.checkSignInWithGoogle();
		Assert.assertTrue(flag);
	}

	@Test(priority = 6)
	public void verifyPrivacyPolicyLinkTest() {
		boolean flag = login.clickPrivacyPolicyLink();
		Assert.assertTrue(flag);
	}

	/*
	 * this method is performing login however just to put assertion this methods is
	 * also using reference of HomePage class to verify user name
	 * 
	 */
	@Description ("Verify Login Page title ")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 7)
	public void VerifyLoginTest() {
		HomePage homepage = login.doLogin(userCredentials);

		String userName = homepage.getUserName();

		Assert.assertEquals(userName, prop.getProperty("user"));
	}

	@DataProvider
	public Object[][] getData() {

		Object data[][] = { { "abcgmail@gmail.com", "xxccW3@xcxcc" }, 
				{ "abcgmail.com", "xcxcxcc" },
				{ "abcgmail.com", "cxcxcccxxcx" }, 
				{ "", "cxx" }, };

		return data;

	}

	@Test(priority = 8, dataProvider = "getData", enabled = false)
	public void VerifyLoginTestNegative(String userN, String passW) {
		userCredentials.setUsername(userN);
		userCredentials.setPassword(passW);
		login.doLogin(userCredentials);
		Assert.assertTrue(login.getError());
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();

	}

}
