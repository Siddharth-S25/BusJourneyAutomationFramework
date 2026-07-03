package hooks;

import drivers.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.ConfigReader;

public class Hooks {

    @Before
    public void setup() {

        DriverFactory.initDriver();

        DriverFactory.getDriver().get(
                ConfigReader.getProperty("baseUrl"));

        // TEMPORARY - captures screenshot + page source right after load
        // so we can see exactly what CI actually rendered.
        // Remove once the timeout issue is diagnosed.
        DebugCapture.captureDebugState("homepage-load");
    }

    @After
    public void tearDown() {

        DriverFactory.quitDriver();
    }
}