package alemar;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions
        (features = "src/test/java/cucumber",
        glue = "alemarStepDef",
        plugin = {"pretty",
                "junit:target/cucumber-reports/cucumber-report.xml",
                "json:target/cucumber-reports/cucumber-report.json",
                "html:target/cucumber-reports/cucumber-report.html"},
        monochrome = true,
        tags = "@credentials_env")
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
