package org.pageObjects;

import org.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractComponent {

    private WebDriver driver;

    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "tr td:nth-child(3)")
    private List<WebElement> productNames;

    @FindBy(css = ".totalRow button")
    WebElement checkout;

    public Boolean VerifyProductDisplay(String product) {
        Boolean match = productNames.stream().anyMatch(s -> s.getText().equalsIgnoreCase(product));
        return match;
    }
}
