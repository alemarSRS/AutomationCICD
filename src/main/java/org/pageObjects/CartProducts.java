package org.pageObjects;

import org.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartProducts extends AbstractComponent {

    private WebDriver driver;

    public CartProducts(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = ".cartSection h3")
    List<WebElement> cartList;

    @FindBy(css = ".totalRow button")
    WebElement checkout;


    public List<WebElement> getCartProducts() {
        return cartList;
    }

    public boolean matchProducts(String product) {
        Boolean match = getCartProducts().stream().anyMatch(s -> s.getText().equalsIgnoreCase(product));
        return match;
    }

    public CheckoutProducts proceedToCheckout() {
        checkout.click();
        return new CheckoutProducts(driver);
    }
}
