package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.AppConstant;
import com.qa.hubspot.util.Credentials;

public class HomePageTest {

	BasePage bP;
	Properties prop;
	WebDriver driver;
	LoginPage login;
	Credentials userCredentials;
	HomePage HP;

	@BeforeMethod
	public void setUP() throws InterruptedException {
		bP = new BasePage();
		prop = bP.init_properties();
		String browserName = prop.getProperty("browser");
		driver = bP.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		login = new LoginPage(driver);
		userCredentials = new Credentials(prop.getProperty("username"), prop.getProperty("passname"));
		HP = login.doLogin(userCredentials);
	

	}

	@Test(priority = 1)

	public void verifyHomePageTitleTest() {

		String title = HP.getTitle();
		Assert.assertEquals(title, AppConstant.Home_Page_Title);
	}

	@Test(priority = 2)
	public void verifyAccountNameTest() {
		String accountname = HP.getUserName();
		Assert.assertEquals(accountname, "Ape");
	}

	@Test(priority = 3)
	public void verifyHeaderTest() {
		boolean header = HP.getHeader();
		Assert.assertTrue(header);
	}

	@AfterMethod
	public void teardown() {

		driver.quit();
	}

}
