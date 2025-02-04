package alemar.TestCases;

import alemar.TestComponents.BaseTest;
import alemar.TestComponents.Retry;
import org.pageObjects.CartProducts;
import org.pageObjects.ProductCatalogue;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ErrorValidationsTest extends BaseTest {
    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void LoginErrorValidation() throws IOException, InterruptedException {

        landingPage.loginApplication("alex91.marciuc@gmail.com", "TeraXiaomi14T");
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
    }

    @Test
    public void productErrorValidation() throws IOException {
        String productName = "IPHONE 13 PRO";
        ProductCatalogue productCatalogue = landingPage.loginApplication(
                "alex92.marciuc@gmail.com", "TereaXiaomi14T");
        productCatalogue.addProductToCart(productName);
        CartProducts cartPro = productCatalogue.accessCart();
        Boolean match = cartPro.matchProducts("IPHONE 14 PRO");
        Assert.assertFalse(match);
    }
}
