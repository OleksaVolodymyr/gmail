package com.epam.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverSingelton {
	
	private static WebDriver driver;

	private WebDriverSingelton() {

	}

	public static WebDriver getInstance() {
		if (driver == null) {
			System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			goToURL("https://mail.google.com/mail/");
			

		}
		return driver;
	}
	
	public static void goToURL(String url) {
		getInstance().navigate().to(url);
	}
}
