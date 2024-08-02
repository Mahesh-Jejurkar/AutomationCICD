package seleniumframeworkdesign.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws Exception {
		WebDriverManager.chromedriver().setup();		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		String expectedProduct = "ZARA COAT 3";
		
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");
		
		driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("jejurkar.mahesh@gmail.com");
		driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys("JMahesh@1990");
		driver.findElement(By.xpath("//input[@id='login']")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='toast-container']")));
		
		List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class,'card-body')]/h5/b"));
		int itemCount = products.size();
		System.out.println(itemCount);
		
		for(WebElement p : products) {
			String name = p.getText();
			System.out.println(name);
		}
		
		for(int i=1; i<=itemCount; i++) {
			Thread.sleep(2000);
			String pname = driver.findElement(By.xpath("//*[@id='products']/div[1]/div[2]/div["+i+"]/div/div/h5")).getText();
			if(pname.equals(expectedProduct)) {
				driver.findElement(By.xpath("//*[@id='products']/div[1]/div[2]/div["+i+"]/div/div/button[2]")).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='toast-container']"))); 
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='toast-container']")));
			}
		}
		
		driver.findElement(By.xpath("//*[contains(text(), ' Cart ')]")).click();
		
		List<WebElement> cartProducts = driver.findElements(By.xpath("//*[@class='cart']//h3"));
		
		Boolean match =	cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(expectedProduct));
		System.out.println(match);
		Assert.assertTrue(match);
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[text()='Checkout']")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@class='form-group']//input")).sendKeys("India");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'ta-results')]")));
		
		Thread.sleep(2000);
		List<WebElement> selectCountry = driver.findElements(By.xpath("//*[@class='form-group']//span"));
		int countryCount = selectCountry.size();
		
		for(int i=1; i<=countryCount; i++) {
			String country = driver.findElement(By.xpath("//*[@class='form-group']//button["+i+"]//span")).getText();
			System.out.println(country);
			if(country.equals("India")) {
				driver.findElement(By.xpath("//*[@class='form-group']//button["+i+"]//span")).click();
			}
		}
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[contains(@class,'action__submit')]")).click();
		String actualConfirmMsg = driver.findElement(By.xpath("//*[contains(@class,'hero-primary')]")).getText();
		String expectedConfirmMsg = "THANKYOU FOR THE ORDER.";
		
		Assert.assertTrue(actualConfirmMsg.equalsIgnoreCase(expectedConfirmMsg));
		
		
		Thread.sleep(5000);
		driver.close();
	}
}