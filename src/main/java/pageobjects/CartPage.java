package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponents.AbstractCompnent;

public class CartPage extends AbstractCompnent{
	
	WebDriver driver;
	
	@FindBy(xpath = "//*[@class='cart']//h3")
	List<WebElement> cartProducts;
	
	@FindBy(xpath = "//*[text()='Checkout']")
	WebElement checkout;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	public Boolean verifyProductDisplay(String expectedProduct) {
		Boolean match =	cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(expectedProduct));
		return match;
	}
	
	public CheckoutPage goToCheckout() {
		checkout.click();
		return new CheckoutPage(driver);
	}
}
