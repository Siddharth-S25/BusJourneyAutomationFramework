package drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.ConfigReader;

import java.time.Duration;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver =
            new ThreadLocal<>();

    public static void initDriver() {

        String browser =
                ConfigReader.getProperty("browser");

        if (browser.equalsIgnoreCase("chrome")) {

            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();

            // Mild, standard fingerprint adjustments. These do not attempt to
            // defeat IP-reputation-based blocking (e.g. Cloudflare bot-management
            // on CI runner IPs) — see README "Known Limitations" — but they are
            // reasonable, honest defaults for any automated Chrome session.
            options.addArguments("--disable-blink-features=AutomationControlled");
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                    "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36");

            // Headless is enabled automatically on CI, OR when explicitly
            // requested locally via -Dheadless=true. This means CI never
            // silently runs in non-headless mode even if someone forgets
            // to pass the flag.
            boolean isCI = System.getenv("CI") != null;
            boolean headlessRequested =
                    Boolean.parseBoolean(System.getProperty("headless", "false"));

            if (isCI || headlessRequested) {
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage"); // prevents Chrome crashes on low /dev/shm CI runners
            }

            driver.set(new ChromeDriver(options));
        }

        getDriver().manage().window().maximize();

        getDriver().manage().timeouts().implicitlyWait(
                Duration.ofSeconds(
                        Integer.parseInt(
                                ConfigReader.getProperty(
                                        "implicitWait"))));
    }

    public static WebDriver getDriver() {

        return driver.get();
    }

    public static void quitDriver() {

        if (driver.get() != null) {

            getDriver().quit();

            driver.remove();
        }
    }
}