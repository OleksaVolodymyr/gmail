package com.epam.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.epam.control.Button;
import com.epam.control.CheckBox;
import com.epam.control.Label;
import com.epam.control.TableRow;
import com.epam.model.MessageModel;
import com.epam.utils.WebDriverSingelton;

public class GmailInboxPage extends PageObject {
	private static final Logger LOG = Logger.getLogger(GmailInboxPage.class);

	@FindBy(xpath = "//div[@class='T-Jo-auh']")
	private List<CheckBox> checkbox;

	@FindBy(xpath = "//div[@class='ar9 T-I-J3 J-J5-Ji']")
	private Button deleteButton;

	@FindBy(xpath = "//span[@id='link_undo']")
	private Button undoButtron;

	@FindBy(xpath = "//span[@class='bofITb']")
	private Label undoDelete;

	@FindBy(xpath = "//tr[contains(@class,'zA')]")
	private List<TableRow> elements;

	private List<TableRow> selectedMessage = null;

	public List<TableRow> getSelectedMessage() {
		return selectedMessage;
	}

	public void findMessage(MessageModel messageModel, int amount) {
		LOG.info("find " + amount + " message using template : " + messageModel);
		int count = 0;
		selectedMessage = new ArrayList<>();
		for (TableRow message : elements) {
			new WebDriverWait(WebDriverSingelton.getInstance(), 30)
					.until(d -> d.findElement(By.xpath(".//div[@class='yW']")));
			String fromWho = message.findElement(By.xpath(".//div[@class='yW']")).getText().trim();
			// new WebDriverWait(WebDriverSingelton.getInstance(), 20)
			// .until(d -> d.findElement(By.xpath(".//span[@class='bog']")));
			String subject = message.findElement(By.xpath(".//span[@class='bog']")).getText().trim();
			// new WebDriverWait(WebDriverSingelton.getInstance(), 30)
			// .until(d -> d.findElement(By.xpath(".//span[@class='y2']")));
			String text = message.findElement(By.xpath(".//span[@class='y2']")).getText().trim();
			if (fromWho.equals(messageModel.getFromWho()) && subject.equals(messageModel.getSubject())
					&& text.equals(messageModel.getMessage())) {
				selectedMessage.add(message);
				count++;
			}
			if (count >= amount) {
				break;
			}
		}
	}

	public void selectMessage() {
		LOG.info("click message checkBox");
		for (TableRow selected : selectedMessage) {
			CheckBox checkBox = new CheckBox(selected.findElement(By.xpath(".//div[@class='T-Jo-auh']")));
			if (!checkBox.isSelected()) {
				checkBox.click();
			}
		}
	}

	public void deleteSelectedMessage() {
		LOG.info("click delete button");
		deleteButton.click();
	}

	public void undoDeleteOperation() {
		new WebDriverWait(WebDriverSingelton.getInstance(), 20)
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[@id='link_undo']")));
		undoButtron.click();
	}

	public boolean isMessageRestored() {
		return undoDelete.isDisplayed();
	}

	public String getMessage() {
		return undoDelete.getText();
	}

}
