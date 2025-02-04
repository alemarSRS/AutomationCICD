package alemarStepDef;

import alemar.TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.pageObjects.*;
import org.testng.Assert;

import java.io.IOException;

public class StepDefinitionImpl extends BaseTest {

    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    public OrderConfirmation order;

    @Given("I landed on Ecommerce Page")
    public void I_landed_on_Ecommerce_Page() throws IOException {
        landingPage = launchApp();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_username_and_password(String username, String password) {
        productCatalogue = landingPage.loginApplication(username, password);
    }

    @When("^I add product (.+) to Cart$")
    public void i_add_product_to_cart(String productName) {
        productCatalogue.addProductToCart(productName);
    }

    @When("^Checkout (.+) and submit the order$")
    public void checkout_submit_order(String productName) {

        CartProducts cartPro = productCatalogue.accessCart();
        Boolean match = cartPro.matchProducts(productName);
        Assert.assertTrue(match);
        CheckoutProducts checkout = cartPro.proceedToCheckout();
        checkout.selectCountry("ro");
        order = checkout.submitOrder();
    }

    @Then("{string} message is displayed on ConfirmationPage")
    public void message_displayed_confirmationPage(String string) {

        String confirmMessage = order.getConfirmationText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
    }

    @Then("{string} message is displayed")
    public void something_message_is_displayed(String string) {
        Assert.assertEquals(string, landingPage.getErrorMessage());
        driver.close();
    }
}
