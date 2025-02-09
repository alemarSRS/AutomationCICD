@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

  Background:
    Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive test of Submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples:
      | name                     | password       | productName |
      | alex92.marciuc@gmail.com | TereaXiaomi14T | IPHONE      |

  @Regression
  Scenario Outline: Positive test of Submitting the order with secured credentials
    Given Logs in as an <customer>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples:
      | customer     | productName |
      | CUSTOMER_ONE | IPHONE      |
      | CUSTOMER_TWO | ADIDAS      |
