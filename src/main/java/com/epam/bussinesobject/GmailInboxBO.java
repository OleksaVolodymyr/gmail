package com.epam.bussinesobject;

import com.epam.pages.GmailInboxPage;

public class GmailInboxBO {

	public boolean isUndo() {
		GmailInboxPage gmailInboxPage = new GmailInboxPage();
		gmailInboxPage.selectThreeCheckbox();
		gmailInboxPage.deleteSelectedMessage();
		gmailInboxPage.undoDeleteOperation();
		return true;
	}
}
