package com.epam.lab.gmail;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.epam.bussinesobject.GmailInboxBO;
import com.epam.bussinesobject.GmailLoginBO;
import com.epam.model.UserModel;
import com.epam.utils.WebDriverSingelton2;

public class AppTest extends BaseTest{

	GmailLoginBO gmailLoginBO = new GmailLoginBO();
	GmailInboxBO gmailInboxBO = new GmailInboxBO();

	@Test(/*threadPoolSize = 2, invocationCount = 1,*/ dataProvider = "users")
	public void test(String login, String password) {
		Assert.assertTrue(gmailLoginBO.isLogined(new UserModel(login, password)));
		Assert.assertTrue(gmailInboxBO.isUndo());
	}

	@DataProvider(name = "users",parallel=true)
	public Object[][] getUserParam() {
		return new Object[][] { { "smtp.epam.gr3", "taepamgr3" }, { "oleksavolodymyr", "rizon19845019930809" } };
	}

	@AfterClass
	public void tearDown() throws Exception {
		WebDriverSingelton2.getDriver().quit();
	}
}
