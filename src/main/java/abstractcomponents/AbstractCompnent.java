package abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageobjects.CartPage;

public class AbstractCompnent {
	
	WebDriver driver;
	public AbstractCompnent(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(xpath = "//*[contains(text(), ' Cart ')]")
	WebElement cartHeader;
	
	@FindBy(xpath = "//*[contains(text(), 'ORDERS')]")
	WebElement orderHeader;
	
	
	public CartPage goToCartPage() {
		cartHeader.click();
		
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public OrderPage goToOrderPage() {
		orderHeader.click();
		
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}

	public void waitForElementToAppear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementToDisappear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

}
