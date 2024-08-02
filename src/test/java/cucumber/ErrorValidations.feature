@tag
Feature: Error validation
  
  @ErrorValidation
  Scenario Outline: Negative test of user logged in
    Given I landed on eCommerce page
    When Logged in with username <Username> and password <Password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | Username  									| Password		 |
      | jejurkar.mahesh@gmail.com	 	| JMahesh@123 |