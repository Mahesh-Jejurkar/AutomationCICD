package test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import abstractcomponents.OrderPage;
import pageobjects.CartPage;
import pageobjects.CheckoutPage;
import pageobjects.ConfirmationPage;
import pageobjects.ProductCatalogue;
import testcomponents.BaseTest;

public class SubmitOrderTest extends BaseTest{
	
	String expectedProduct = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = {"Purchase"})
	public void SubmitOrder(HashMap<String, String> input) throws Exception {
		ProductCatalogue productCatalogue  = landingPage.loginApplication(input.get("email"), input.get("password"));
		productCatalogue.displayProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationpage = checkoutPage.submitOrder();
		String actualConfirmMsg	= confirmationpage.getConfimationMsg();
		String expectedConfirmMsg = "THANKYOU FOR THE ORDER.";
		Assert.assertTrue(actualConfirmMsg.equalsIgnoreCase(expectedConfirmMsg));
	}
	
	@Test(dependsOnMethods = {"SubmitOrder"})
	public void orderHistoryTest() {
		ProductCatalogue productCatalogue  = landingPage.loginApplication("jejurkar.mahesh@gmail.com", "JMahesh@1990");
		OrderPage orderPage = productCatalogue.goToOrderPage();
		Boolean match = orderPage.verifyOrderDisplay(expectedProduct);
		Assert.assertTrue(match);
	}
	
	@DataProvider
	public Object[][] getData() throws Exception {
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//seleniumframeworkdesign//data//PurchaseOrder.json");
		return new Object [][] {{data.get(0)},{data.get(1)}};
	}
	
//	@DataProvider
//	public Object[][] getData() {
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email","jejurkar.mahesh@gmail.com");
//		map1.put("password", "JMahesh@1990");
//		map1.put("productName", "ZARA COAT 3");
//		
//		HashMap<Object, Object> map2 = new HashMap<Object, Object>();
//		map2.put("email","jejurkar.mahesh@gmail.com");
//		map2.put("password", "JMahesh@1990");
//		map2.put("productName", "IPHONE 13 PRO");
//		
//		return new Object [][] {{map1},{map2}};
//	}
	
	
//	@DataProvider
//	public Object[][] getData() {
//		return new Object [][] {{"jejurkar.mahesh@gmail.com", "JMahesh@1990", "ZARA COAT 3"},{"jejurkar.mahesh@gmail.com", "JMahesh@1990", "ADIDAS ORIGINAL"}};
//	}
}