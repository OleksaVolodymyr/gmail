package com.epam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.epam.control.Button;
import com.epam.control.Label;
import com.epam.control.TextInput;
import com.epam.utils.WebDriverSingelton;

public class GmailLoginPage extends PageObject {

	@FindBy(xpath = "//input[@name='identifier']")
	private TextInput inputLogin;

	@FindBy(xpath = "//div[@id='identifierNext']")
	private Button buttonNext;

	@FindBy(xpath = "//input[@type='password']")
	private TextInput inputPassword;

	@FindBy(xpath = "//div[@id='passwordNext']")
	private Button passwordNext;

	@FindBy(xpath = "//span[@class='gb_8a gbii']")
	private Button picture;
	
	@FindBy(xpath = "//div[@class='gb_wb']")
	private Label userInfo;
	
	public void enterLoginAndSubmit(String login) {
		inputLogin.sendKeys(login);
		buttonNext.click();
	}

	public void enterPasswordAndSubmit(String login) {
		new WebDriverWait(WebDriverSingelton.getInstance(), 10)
		.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='password']")));
		inputPassword.sendKeys(login);
		passwordNext.click();
	}
	
	public boolean isLogged(String userName) {
		picture.click();	
		return userInfo.getText().equals(userName);
	}
}
