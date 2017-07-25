package com.epam.bussinesobject;

import com.epam.model.MessageModel;
import com.epam.pages.GmailInboxPage;

public class GmailInboxBO {
	private GmailInboxPage gmailInboxPage;

	public GmailInboxBO() {
		this.gmailInboxPage = new GmailInboxPage();
	}

	public void findMessage(MessageModel model, int amount) throws InterruptedException {
		Thread.sleep(500);
		gmailInboxPage.findMessage(model, amount);
	}

	public void selectMessage() {
		gmailInboxPage.selectMessage();
	}

	public boolean isMessageFound(int amount) {
		return gmailInboxPage.getSelectedMessage().size() == amount;
	}

	public void deleteMessage() {
		gmailInboxPage.deleteSelectedMessage();
	}

	public boolean isMessageDeleted() {
		return gmailInboxPage.getSelectedMessage().isEmpty();
	}

	public void restoreDeletedMessage() {
		gmailInboxPage.undoDeleteOperation();
	}

	public boolean isMessageAboutRestoreDisplayed() {
		return gmailInboxPage.isMessageRestored() && gmailInboxPage.getMessage().equals("Your action has been undone.");
	}

}
