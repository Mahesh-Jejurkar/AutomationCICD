package seleniumframeworkdesign.stepDefinitions;

import org.testng.Assert;

import io.cucumber.java.en.*;
import seleniumframeworkdesign.pageobjects.CartPage;
import seleniumframeworkdesign.pageobjects.CheckoutPage;
import seleniumframeworkdesign.pageobjects.ConfirmationPage;
import seleniumframeworkdesign.pageobjects.LandingPage;
import seleniumframeworkdesign.pageobjects.ProductCatalogue;
import seleniumframeworkdesign.testcomponents.BaseTest;

public class StepDefinitionImp extends BaseTest {
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationpage;

	@Given("I landed on eCommerce page")
	public void i_landed_on_eCommerce_page() throws Exception {
		landingPage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password) {
		productCatalogue = landingPage.loginApplication(username, password);
	}

	@When("^I added product (.+) to cart$")
	public void i_added_product_to_cart(String productName) throws Exception {
		productCatalogue.displayProductList();
		productCatalogue.addProductToCart(productName);
	}

	@When("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_order(String productName) {
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		confirmationpage = checkoutPage.submitOrder();
	}

	@Then("{string} message is displayed on confirmation page")
	public void message_is_displayed_on_confirmation_page(String string) {
		String actualConfirmMsg	= confirmationpage.getConfimationMsg();
		String expectedConfirmMsg = string;
		Assert.assertTrue(actualConfirmMsg.equalsIgnoreCase(expectedConfirmMsg));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void something_message_is_displayed(String string) {
		String expectedErrorMsg = string;
		String actualErrorMsg = landingPage.getErrorMessage();
		Assert.assertEquals(expectedErrorMsg, actualErrorMsg);
		driver.close();
	}
}
