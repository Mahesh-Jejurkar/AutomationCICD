package abstractcomponents;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage extends AbstractCompnent{
	
	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//*[contains(@class, 'table-bordered')]//tr//td[2]")
	List<WebElement> tableProductNames;


	public Boolean verifyOrderDisplay(String productName) {
		Boolean match =	tableProductNames.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
	}
}
