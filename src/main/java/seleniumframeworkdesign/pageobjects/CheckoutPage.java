package seleniumframeworkdesign.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumframeworkdesign.abstractcomponents.AbstractCompnent;

public class CheckoutPage extends AbstractCompnent{
	
	WebDriver driver;
	
	@FindBy(xpath = "//*[@class='form-group']//input")
	WebElement enterCountry;
	
	@FindBy(xpath = "//*[@class='form-group']//span")
	List<WebElement> selectCountry;
	
	@FindBy(xpath = "//*[contains(@class,'ta-results')]")
	WebElement selectResults;
	
	@FindBy(xpath = "//*[contains(@class,'action__submit')]")
	WebElement submit;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	public void selectCountry(String countryName) {
		enterCountry.sendKeys(countryName);
		waitForElementToAppear(selectResults);
		int countryCount = selectCountry.size();
		
		for(int i=1; i<=countryCount; i++) {
			String country = driver.findElement(By.xpath("//*[@class='form-group']//button["+i+"]//span")).getText();
			if(country.equals("India")) {
				driver.findElement(By.xpath("//*[@class='form-group']//button["+i+"]//span")).click();
				break;
			}
		}
	}
	
	public ConfirmationPage submitOrder() {
		submit.click();
		return new ConfirmationPage(driver);
	}
}
