package com.epam.tets;

import com.epam.bussinesobject.GmailInboxBO;
import com.epam.bussinesobject.GmailLoginBO;
import com.epam.pages.GmailInboxPage;

public class App {
	
	public static void main(String[] args) {
		//WebDriver driver = WebDriverSingelton.getInstance();
		new GmailLoginBO().isLogined();
		 new GmailInboxBO().isUndo();
	}
}
