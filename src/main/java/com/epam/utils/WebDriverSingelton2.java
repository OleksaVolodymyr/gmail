package com.epam.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.epam.properties.Property;

public class WebDriverSingelton2 {

	private static WebDriver driver;
	private static Property prop = Property.getInstance();

	private WebDriverSingelton2() {

	}

	private static WebDriver getInstance() {
		if (driver == null) {
			System.setProperty(prop.getPropertiesValue("driver"), prop.getPropertiesValue("driverPath"));
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			goToURL(prop.getPropertiesValue("url"));
		}
		return driver;
	}

	private static ThreadLocal<WebDriver> pool = new ThreadLocal<WebDriver>() {
		@Override
		protected WebDriver initialValue() {
			return getInstance();
		}
	};

	private static void goToURL(String url) {
		getInstance().navigate().to(url);
	}

	public static WebDriver getDriver() {
		return pool.get();
	}
}
