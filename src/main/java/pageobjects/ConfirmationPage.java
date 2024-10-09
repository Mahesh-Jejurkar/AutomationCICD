package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponents.AbstractCompnent;

public class ConfirmationPage extends AbstractCompnent{
			
	WebDriver driver;
	
	@FindBy(xpath="//*[contains(@class,'hero-primary')]")
	WebElement actualConfirmMsg;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	} 
	
	
	public String getConfimationMsg() {
		return actualConfirmMsg.getText();
	}
}
