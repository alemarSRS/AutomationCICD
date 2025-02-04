package org.pageObjects;

import org.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutProducts extends AbstractComponent {

    WebDriver driver;

    public CheckoutProducts(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[placeholder='Select Country'")
    private WebElement country;

    @FindBy(css = ".ta-item:nth-of-type(2)")
    private WebElement selectCountry;

    @FindBy(css = ".btnn.action__submit")
    private WebElement confirmOrder;

    By autoSuggestiveDropDown = By.cssSelector(".ta-results");


    public void selectCountry(String countryName) {
        Actions a = new Actions(driver);
        a.sendKeys(country, countryName).build().perform();
        waitForElementToAppear(autoSuggestiveDropDown);
        selectCountry.click();
    }

    public OrderConfirmation submitOrder() {
        confirmOrder.click();
        return new OrderConfirmation(driver);
    }
}
