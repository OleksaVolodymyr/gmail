package com.epam.pages;

import java.util.List;

import org.openqa.selenium.support.FindBy;

import com.epam.control.Button;
import com.epam.control.CheckBox;
import com.epam.control.Label;

public class GmailInboxPage extends PageObject {
	@FindBy(xpath = "//div[@class='T-Jo-auh']")
	private List<CheckBox> checkbox;

	@FindBy(xpath = "//div[@class='ar9 T-I-J3 J-J5-Ji']")
	private Button deleteButton;

	@FindBy(xpath = "//span[@id='link_undo']")
	private Button undoButtron;

	@FindBy(xpath = "//span[@class='bofITb']")
	private Label undoDelete;

	public void selectCheckbox(int count) {
		for (int i = 1; i <= count; i++) {
			if (!checkbox.get(i).isSelected()) {
				checkbox.get(i).click();
			}
		}
	}

	public void deleteSelectedMessage() {
		deleteButton.click();
	}

	public void undoDeleteOperation() {
		undoButtron.click();
	}

	public boolean isMessageRestored() {
		return undoDelete.isDisplayed();
	}
}
