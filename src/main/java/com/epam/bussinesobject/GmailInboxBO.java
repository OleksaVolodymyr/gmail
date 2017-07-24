package com.epam.bussinesobject;

import com.epam.pages.GmailInboxPage;

public class GmailInboxBO {

	public boolean isUndo() {
		GmailInboxPage gmailInboxPage = new GmailInboxPage();
		gmailInboxPage.selectCheckbox(3);
		gmailInboxPage.deleteSelectedMessage();
		gmailInboxPage.undoDeleteOperation();
		return gmailInboxPage.isMessageRestored();
	}
}
