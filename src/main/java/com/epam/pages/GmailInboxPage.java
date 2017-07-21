package com.epam.pages;

import java.util.List;

import org.openqa.selenium.support.FindBy;

import com.epam.control.Button;
import com.epam.control.CheckBox;

public class GmailInboxPage {
	@FindBy(xpath = "//div[@class='T-Jo-auh']")
	private List<CheckBox> checkbox;

	@FindBy(xpath = "//div[@class='ar9 T-I-J3 J-J5-Ji']")
	private Button deleteButton;

	@FindBy(xpath = "//span[@id='link_undo']")
	private Button undoButtron;

	public void selectThreeCheckbox() {
		listSize();
		for (int i = 1; i <= 3; i++) {
			if (!checkbox.get(i).isSelected()) {
				checkbox.get(i).click();
			}
		}
	}
	public void listSize() {
		System.out.println(checkbox.size());
	}
	public void deleteSelectedMessage() {
		deleteButton.click();
	}

	public void undoDeleteOperation() {
		undoButtron.click();
	}
}
