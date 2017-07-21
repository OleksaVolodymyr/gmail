package com.epam.bussinesobject;

import com.epam.pages.GmailLoginPage;

public class GmailLoginBO {
		
	public boolean isLogined() {
		
		GmailLoginPage gmailLoginPage = new GmailLoginPage();
		gmailLoginPage.enterLoginAndSubmit("smtp.epam.gr3");
		gmailLoginPage.enterPasswordAndSubmit("taepamgr3");
		
		return true; 
	}
}
