package com.qa.hubspot.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;



import io.github.bonigarcia.wdm.WebDriverManager;
public class BasePage {

	WebDriver driver;

	Properties prop;

	ChromeOptions co;
	
	FirefoxOptions fo;

	/*
	 * This method will take the browser value from prop() method and will launch
	 * the browser accordingly
	 */
	public WebDriver init_driver(String browserName) {

		System.out.println("browser name is:" + browserName);

		if (browserName.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();
			
			if(prop.getProperty("incognito").equals("yes")) {
				
			co = new ChromeOptions();
			
			co.addArguments("--incognito");
	
			driver = new ChromeDriver(co);
			}
			else {
				driver = new ChromeDriver();
			}

		} else if (browserName.equalsIgnoreCase("firefox"))

		{
			WebDriverManager.firefoxdriver().setup();
			
			fo = new FirefoxOptions();
			
			if(prop.getProperty("headless").equals("yes"))
			{
				fo.addArguments("--headless");
				driver = new FirefoxDriver(fo);
				
			}
			else {
			driver = new FirefoxDriver();
			}

		}

		else if (browserName.equalsIgnoreCase("Safari"))

		{
			WebDriverManager.getInstance(SafariDriver.class).setup();

			driver = new SafariDriver();

		}

		else {

			System.out.println("Browser name" + browserName + "has not found");
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// driver.get(prop.); URL is being launching from Base class

		return driver;

	}

	/*
	 * This method will get the value of property file .This method will pass the
	 * value of different Keys in different method
	 */

	public Properties init_properties() {

		prop = new Properties();
		String path = null;
		String env = null;

		try {

			env = System.getProperty("env");

			if (env.equals("QA")) {
				path = "./src/main/java/com/qa/hubspot/config/config.qa.properties";
			}

			else if (env.equals("STG")) {
				path = "./src/main/java/com/qa/hubspot/config/config.stg.properties";
			}

		} catch (Exception e) {
			path = "./src/main/java/com/qa/hubspot/config/config.properties";
			
			System.out.println("Some issue with config properties...Please correct your config");
		}

		try {
			FileInputStream ip = new FileInputStream(path);
			prop.load(ip);

		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return prop;

	}
	
	

}
