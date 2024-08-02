package seleniumframeworkdesign.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import seleniumframeworkdesign.pageobjects.CartPage;
import seleniumframeworkdesign.pageobjects.ProductCatalogue;
import seleniumframeworkdesign.testcomponents.BaseTest;
import seleniumframeworkdesign.testcomponents.Retry;

public class ErrorValidationTest extends BaseTest{
	
	@Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void loginErrorValidation() {
		landingPage.loginApplication("jejurkar.mahesh@gmail.com", "JMahesh@19900");
		String expectedErrorMsg = "Incorrect email or password.";
		String actualErrorMsg = landingPage.getErrorMessage();
		Assert.assertEquals(expectedErrorMsg, actualErrorMsg);
	}
	
	@Test
	public void productErrorValidation() throws Exception {
		String expectedProduct = "ZARA COAT 33";
		ProductCatalogue productCatalogue  = landingPage.loginApplication("jejurkar.mahesh@gmail.com", "JMahesh@1990");
		productCatalogue.displayProductList();
		productCatalogue.addProductToCart(expectedProduct);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(expectedProduct);
		Assert.assertFalse(match);
	}
}
