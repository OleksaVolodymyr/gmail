package com.epam.utils;

import org.openqa.selenium.WebElement;

import com.epam.control.IElement;

public class WrapperFactory {
	public static IElement createInstance(Class<IElement> clazz, WebElement element) {
		try {
			return clazz.getConstructor(WebElement.class).newInstance(element);
		} catch (Exception e) {
			throw new AssertionError("WebElement can't be represented as " + clazz);
		}
	}
}
