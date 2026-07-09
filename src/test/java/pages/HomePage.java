package pages;

import drivers.DriverFactory;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;

public class HomePage {

    WebDriver driver;

    public HomePage() {

        driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }

    // Source City
    @FindBy(xpath = "//input[@placeholder='Leaving From']")
    WebElement sourceCity;

    // Destination City
    @FindBy(xpath = "//input[@placeholder='Going To']")
    WebElement destinationCity;

    // Journey Date
    @FindBy(xpath = "//a[text()=\"Today\"]")
    WebElement todayLink;

    // ===========================

    public void enterSourceCity(String city) {

        WaitUtils.waitForElementVisible(sourceCity);

        sourceCity.click();
        sourceCity.clear();
        sourceCity.sendKeys(city);

        try {
            Thread.sleep(1500);   // Temporary
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        sourceCity.sendKeys(Keys.ARROW_DOWN);
        sourceCity.sendKeys(Keys.ENTER);
    }

    public void enterDestinationCity(String city) {

        WaitUtils.waitForElementVisible(destinationCity);

        destinationCity.click();
        destinationCity.clear();
        destinationCity.sendKeys(city);

        try {
            Thread.sleep(1500);   // Temporary
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        destinationCity.sendKeys(Keys.ARROW_DOWN);
        destinationCity.sendKeys(Keys.ENTER);
    }

    public void selectJourneyDate() {

        WaitUtils.waitForElementClickable(todayLink);

        todayLink.click();

    }


}