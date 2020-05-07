package com.qa.hubspot.util;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

	Properties prop;
	ChromeOptions co;
	FirefoxOptions fo;

	public OptionsManager(Properties prop) {

		this.prop = prop;
	}

	public ChromeOptions getChromeOptions() {
		
		co = new ChromeOptions();
		if (prop.getProperty("incognito").equals("yes")) co.addArguments("--incognito");

		if (prop.getProperty("headless").equals("yes")) co.addArguments("--headless");
			
		return co;
	}

	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();

		if (prop.getProperty("headless").equals("no"));
		return fo;
	}

}
