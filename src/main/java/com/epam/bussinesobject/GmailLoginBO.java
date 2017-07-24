package com.epam.bussinesobject;

import com.epam.model.UserModel;
import com.epam.pages.GmailLoginPage;

public class GmailLoginBO {

	public boolean isLogined(UserModel user) {
		GmailLoginPage gmailLoginPage = new GmailLoginPage();
		gmailLoginPage.enterLoginAndSubmit(user.getLogin());
		gmailLoginPage.enterPasswordAndSubmit(user.getPassword());
		return gmailLoginPage.isLogged("smtp.epam.gr3@gmail.com");
	}
}
