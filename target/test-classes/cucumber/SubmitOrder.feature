@tag
Feature: Purchase the order from eCommerce website
	
	Background:
		Given I landed on eCommerce page

	@Regression
  Scenario Outline: Positive test of submitting the order
    Given Logged in with username <Username> and password <Password>
    When I added product <ProductName> to cart
    And Checkout <ProductName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page

    Examples: 
      |	Username								|	Password		|ProductName	|
      |jejurkar.mahesh@gmail.com|JMahesh@1990	|ZARA COAT 3	|