package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {
                "stepdefinitions",
                "hooks",
                "reports"
        },
        plugin = {
                "pretty",
                "html:target/cucumber-report.html"
        },
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

        /**
         * Reads the "browser" parameter from testng.xml and sets it as a
         * System property so DriverFactory.initDriver() can pick it up.
         *
         * @Optional("chrome") means if no parameter is passed (e.g. running
         * a single test directly in IntelliJ without testng.xml), it falls
         * back to Chrome automatically — you never get a null browser.
         */
        @Parameters("browser")
        @BeforeClass
        public void setBrowser(@Optional("chrome") String browser) {
                System.setProperty("browser", browser);
        }
}