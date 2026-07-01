package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.HomePage;
import pages.SearchResultsPage;
import utils.Log;
import reports.ExtentHooks;

public class BusBookingSteps {

    HomePage homePage = new HomePage();
    SearchResultsPage resultsPage = new SearchResultsPage();

    @Given("user launches AbhiBus application")
    public void launchApplication() {

      Log.logger.info("Application launched successfully.");
        ExtentHooks.test.info("Application launched successfully.");

    }

//    @When("user enters source city as {string}")
//    public void enterSourceCity(String source) {
//
//        homePage.enterSourceCity(source);

        @When("user enters source city as {string}")
        public void enterSourceCity(String city) {

        homePage.enterSourceCity(city);

        homePage.enterSourceCity(city);


        Log.logger.info("Source City : {}", city);

        ExtentHooks.test.info("Source City : " + city);
    }

//    @When("user enters destination city as {string}")
//    public void enterDestinationCity(String destination) {
//
//        homePage.enterDestinationCity(destination);
//    }

    @When("user enters destination city as {string}")
    public void enterDestinationCity(String city) {

        homePage.enterDestinationCity(city);

        Log.logger.info("Destination City : {}", city);

        ExtentHooks.test.info("Destination City : " + city);
    }

    @When("user selects today's date")
    public void selectTodaysDate() {

        homePage.selectJourneyDate();

     //   homePage.selectToday();

        Log.logger.info("Today's date selected.");

        ExtentHooks.test.info("Today's date selected.");
    }

    @Then("search results should be displayed")
    public void verifySearchResults() {

        Assert.assertTrue(
                resultsPage.verifySearchResultsDisplayed(),
                "Search results are NOT displayed");

        Log.logger.info("Search results displayed successfully.");

        ExtentHooks.test.pass("Search results displayed successfully.");
    }

    @Then("URL should contain source and destination")
    public void verifyURL() {

        String url = resultsPage.getCurrentURL();


        Log.logger.info("Current URL : {}", url);
        ExtentHooks.test.info("Current URL : " + url);

        Assert.assertTrue(url.toLowerCase().contains("pune"));
        Assert.assertTrue(url.toLowerCase().contains("bengaluru"));
    }

    @Then("minimum buses should be displayed")
    public void verifyBusCount() {

        int busCount = resultsPage.getBusCount();

        Log.logger.info("Bus Count : {}", busCount);

        ExtentHooks.test.info("Bus Count : " + busCount);

        Assert.assertTrue(busCount > 0);
    }
}