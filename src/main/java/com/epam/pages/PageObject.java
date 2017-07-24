package com.epam.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.epam.utils.ExtendedFieldDecorator;
import com.epam.utils.WebDriverSingelton;

public class PageObject {

	public PageObject(WebDriver driver) {
		PageFactory.initElements(new ExtendedFieldDecorator(driver), this);
	}

	public PageObject() {
		this(WebDriverSingelton.getInstance());
	}
}
