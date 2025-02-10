package alemar;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "classpath:features",
        plugin = {"pretty", "json:reports/cucumber-report.json", "html:reports/cucumber-reports.html"},
        monochrome = true,
        tags = "Regression")
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
