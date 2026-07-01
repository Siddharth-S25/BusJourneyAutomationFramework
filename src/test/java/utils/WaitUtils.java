package utils;

import drivers.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    public static void waitForElementVisible(WebElement element) {

        WebDriverWait wait =
                new WebDriverWait(
                        DriverFactory.getDriver(),
                        Duration.ofSeconds(20));

        wait.until(
                ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementClickable(WebElement element) {

        WebDriverWait wait =
                new WebDriverWait(
                        DriverFactory.getDriver(),
                        Duration.ofSeconds(20));

        wait.until(
                ExpectedConditions.elementToBeClickable(element));
    }
    public static void waitForElement(By locator) {

        WebDriverWait wait =
                new WebDriverWait(
                        DriverFactory.getDriver(),
                        Duration.ofSeconds(20));

        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}