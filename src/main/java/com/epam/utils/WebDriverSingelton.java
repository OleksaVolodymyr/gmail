package com.epam.utils;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.epam.properties.Property;

public class WebDriverSingelton {
	private Logger LOG = Logger.getLogger(WebDriverSingelton.class);
	private WebDriver driver;
	private Property prop = Property.getInstance();
	private static ThreadLocal<WebDriverSingelton> pool = new ThreadLocal<>();

	public WebDriverSingelton() {
		LOG.info("create webdriver, thread id : " + Thread.currentThread().getId());
		System.setProperty(prop.getPropertiesValue("driver"), prop.getPropertiesValue("driverPath"));
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
		goToURL(prop.getPropertiesValue("url"));
	}

	public static synchronized WebDriver getInstance() {
		if (pool.get() == null) {
			pool.set(new WebDriverSingelton());
		}
		return pool.get().getDriver();
	}

	private void goToURL(String url) {
		LOG.info("go to url: " + url);
		getDriver().navigate().to(url);
	}

	public WebDriver getDriver() {
		return this.driver;
	}
}
