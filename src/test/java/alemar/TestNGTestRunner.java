package alemar;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/cucumber",
        glue = "alemarStepDef",
        plugin = {"pretty", "json:reports/cucumber-report.json", "html:reports/cucumber-reports.html"},
        monochrome = true,
        tags = "@credentials_env")
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
