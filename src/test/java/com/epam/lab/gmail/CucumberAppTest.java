package com.epam.lab.gmail;

import org.testng.Assert;

import com.epam.pages.GmailLoginPage;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

public class CucumberAppTest {
	GmailLoginPage loginPage = new GmailLoginPage();
	String login;
	String password;

	@Given("^ Login '(.*)' and password '(.*)'$")
	public void openBrowser(String login, String password) throws Throwable {
		this.login = login;
		this.password = password;
	}

	@When("^I logined on Gmail$")
	public void login() throws Throwable {
		loginPage.enterLoginAndSubmit(login);
		loginPage.enterPasswordAndSubmit(password);
	}

	@Then("^Login should be '(.*)'$")
	public void succes(String result) {
		Assert.assertTrue(loginPage.isLogged(result));
	}
}
