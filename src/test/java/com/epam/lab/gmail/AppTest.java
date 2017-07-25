package com.epam.lab.gmail;

import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.epam.bussinesobject.GmailInboxBO;
import com.epam.bussinesobject.GmailLoginBO;
import com.epam.model.MessageModel;
import com.epam.model.UserModel;
import com.epam.model.UsersModel;
import com.epam.properties.Property;
import com.epam.utils.Parser;
import com.epam.utils.WebDriverSingelton;

public class AppTest {
	private MessageModel model;
	private Property prop = Property.getInstance();
	private int amount;

	@BeforeTest
	public void setUp() {
		this.model = new MessageModel(prop.getPropertiesValue("fromWho"), prop.getPropertiesValue("subject"),
				prop.getPropertiesValue("message"));
		this.amount = 3;
	}

	@Test(threadPoolSize = 5, dataProvider = "users")
	public void test(UserModel userModel) throws InterruptedException {
		GmailLoginBO gmailLoginBO = new GmailLoginBO();
		GmailInboxBO gmailInboxBO = new GmailInboxBO();
		Assert.assertTrue(gmailLoginBO.isLogined(userModel));
		gmailInboxBO.findMessage(model, amount);
		Assert.assertTrue(gmailInboxBO.isMessageFound(amount));
		gmailInboxBO.selectMessage();
		gmailInboxBO.deleteMessage();
		gmailInboxBO.findMessage(model, amount);
		Assert.assertTrue(gmailInboxBO.isMessageDeleted());
		gmailInboxBO.restoreDeletedMessage();
		Assert.assertTrue(gmailInboxBO.isMessageAboutRestoreDisplayed());

	}

	@DataProvider(name = "users", parallel = true)
	public Iterator<UserModel> getDataFromXML() {
		return Parser.XMLParse("users.xml", new UsersModel()).getUsers().iterator();
	}

	@DataProvider(name = "usersXLSX", parallel = true)
	public Iterator<UserModel> getDataFromXLSX() {
		return Parser.XLSXParse("users.xlsx", new UserModel()).iterator();
	}

	@DataProvider(name = "usersCSV", parallel = true)
	public Iterator<UserModel> getDataFromCSV() {
		return Parser.CSVParse("users.csv", new UserModel(), ",").iterator();
	}

	@AfterMethod
	public void close() {
		WebDriverSingelton.getInstance().quit();
	}

}
