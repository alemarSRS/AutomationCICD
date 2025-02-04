package alemar.TestCases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pageObjects.LandingPage;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {

    public static void main(String[] args) throws InterruptedException {

	//new comment added
        String productName = "IPHONE 13 PRO";
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/client");
        LandingPage landingPage = new LandingPage(driver);

        driver.findElement(By.id("userEmail")).sendKeys("alex92.marciuc@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("TereaXiaomi14T");
        driver.findElement(By.id("login")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
//        products.stream().filter(s -> s.getText().contains("IPHONE 13"))
//                        .forEach(s -> {
//                            WebElement button = s.findElement(By.cssSelector(".btn.w-10.rounded"));
//                            button.click();
//                        });

        WebElement prod = products.stream().filter(s ->
                s.findElement(By.cssSelector("b")).getText()
                        .equals("IPHONE 13 PRO")).findFirst().orElse(null);
        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        //ng-animating for loading animation
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean match = cartProducts.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);
        driver.findElement(By.cssSelector(".totalRow button")).click();

        //valid as in video
        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country'"))
                , "Ro").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();

//      made by me but wtf is not working
//        driver.findElement(By.cssSelector(".form-group input")).sendKeys("Ro");
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
//        List<WebElement> countries = driver.findElements(By.cssSelector(".ta-item"));
//        countries.stream().filter(s -> s.getText().equalsIgnoreCase("Romania"))
//                .findFirst().orElseThrow(() -> new NoSuchElementException("Romania not found"))
//                .click();

        driver.findElement(By.cssSelector(".btnn.action__submit")).click();
        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(
                "THANKYOU FOR THE ORDER."));

    }
}
