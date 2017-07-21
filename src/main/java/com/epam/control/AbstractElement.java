package com.epam.control;

import org.openqa.selenium.WebElement;

public abstract class AbstractElement implements IElement {

	protected WebElement webElement;

	public AbstractElement(WebElement webElement) {
		this.webElement = webElement;
	}
}
