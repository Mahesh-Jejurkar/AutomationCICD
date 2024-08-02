package seleniumframeworkdesign.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumframeworkdesign.abstractcomponents.AbstractCompnent;

public class ProductCatalogue extends AbstractCompnent{
	
	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[contains(@class,'card-body')]/h5/b")
	List<WebElement> products;
	
	
	@FindBy(xpath = "//*[@id='toast-container']")
	WebElement toastMsg;
	
	public List<WebElement> getProductList() {
		waitForElementToDisappear(toastMsg);
		return products;
	}
	
	public void displayProductList() {
		for(WebElement p : getProductList()) {
			System.out.println(p.getText());
		}
	}
	
	public void addProductToCart(String expectedProduct) throws Exception {
		int productCount = getProductList().size();
		for(int i=1; i<=productCount; i++) {
			String pname = driver.findElement(By.xpath("//*[@id='products']/div[1]/div[2]/div["+i+"]/div/div/h5")).getText();
			if(pname.equals(expectedProduct)) {
				driver.findElement(By.xpath("//*[@id='products']/div[1]/div[2]/div["+i+"]/div/div/button[2]")).click();
			}
		}
		//waitForElementToAppear(toastMsg);
		//waitForElementToDisappear(toastMsg);
		Thread.sleep(3000);
	}
	
}
