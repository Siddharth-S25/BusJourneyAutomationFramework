// Add this inside your Hooks class — call it right after driver.get(url)
// in your @Before setup, BEFORE any page object waits for elements.

package hooks;

import drivers.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DebugCapture {

    public static void captureDebugState(String label) {
        try {
            WebDriver driver = DriverFactory.getDriver();

            // 1. Screenshot of whatever actually rendered
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.createDirectories(Paths.get("screenshots"));
            Files.copy(src.toPath(), Paths.get("screenshots/" + label + "-debug.png"));

            // 2. Page title + URL (tells you if you got redirected / blocked page)
            System.out.println("DEBUG [" + label + "] Title: " + driver.getTitle());
            System.out.println("DEBUG [" + label + "] URL: " + driver.getCurrentUrl());

            // 3. Full page source (tells you if a CAPTCHA/consent wall is present)
            Files.createDirectories(Paths.get("target/debug"));
            Files.writeString(Paths.get("target/debug/" + label + "-source.html"),
                    driver.getPageSource());

        } catch (Exception e) {
            System.out.println("DEBUG capture failed: " + e.getMessage());
        }
    }
}
