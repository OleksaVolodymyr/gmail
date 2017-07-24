package com.epam.lab.gmail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;

import com.epam.properties.Property;
import com.epam.utils.WebDriverSingelton;

public class BaseTest {
	protected ThreadLocal<WebDriver> driver = null;
	private static Property prop = Property.getInstance();

	@BeforeMethod
	public void setupTest() {
		WebDriver dr = new ChromeDriver();
		driver.set(dr);
	}

	public WebDriver getDriver() {
		return driver.get();
	}

}
