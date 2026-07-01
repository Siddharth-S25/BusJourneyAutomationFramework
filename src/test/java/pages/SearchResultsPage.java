package pages;

import drivers.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import utilities.LoggerLoad;

public class SearchResultsPage {

    WebDriver driver;

    public SearchResultsPage() {
        driver = DriverFactory.getDriver();
    }

    /**
     * Wait until the results page is loaded
     */
    public void waitForResultsPage() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.urlContains("/bus_search"));
    }

    /**
     * Verify search results are displayed
     */
    public boolean verifySearchResultsDisplayed() {

        waitForResultsPage();

        return driver.getCurrentUrl().contains("/bus_search");
    }

    /**
     * Get current URL
     */
    public String getCurrentURL() {

        return driver.getCurrentUrl();
    }

    /**
     * Count buses displayed
     */
    public int getBusCount() {


            waitForResultsPage();

            List<WebElement> buses = driver.findElements(
                    By.xpath("//div[starts-with(@id,'service-operator-agent-name-')]"));

       // LoggerLoad.logger.info("Bus Count : {}", buses.size());

            return buses.size();



    }

}