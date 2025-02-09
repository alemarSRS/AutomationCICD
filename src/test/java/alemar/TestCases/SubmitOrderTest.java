package alemar.TestCases;

import alemar.TestComponents.BaseTest;
import org.pageObjects.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    String productName = "IPHONE";

    @Test(dataProvider = "getData", groups = {"PurchaseTest"})
    public void submitOrder(HashMap<String, String> input) throws IOException {
        ProductCatalogue productCatalogue = landingPage.loginApplication(
                input.get("email"), input.get("password"));
        productCatalogue.addProductToCart(input.get("product"));
        CartProducts cartPro = productCatalogue.accessCart();
        Boolean match = cartPro.matchProducts(input.get("product"));
        Assert.assertTrue(match);
        CheckoutProducts checkout = cartPro.proceedToCheckout();
        checkout.selectCountry("ro");
        OrderConfirmation order = checkout.submitOrder();
        String confirmMessage = order.getConfirmationText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(
                "THANKYOU FOR THE ORDER."));
    }

    //To verify iphone is displayed
    @Test(dependsOnMethods = {"submitOrder"})
    public void OrderHistory() {
        ProductCatalogue productCatalogue = landingPage.loginApplication(
                "alex92.marciuc@gmail.com", "TereaXiaomi14T");
        OrderPage ordersPage = productCatalogue.goToOrdersPage();
        Assert.assertTrue(ordersPage.VerifyProductDisplay(productName));
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") +
                "//src//test//java//data//PurchaseOrder.json");
        return new Object[][]{{data.get(0)}, {data.get(1)}};
    }

}
