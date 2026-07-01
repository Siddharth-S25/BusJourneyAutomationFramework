package reports;

import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ScreenshotUtil;

public class ExtentHooks {

    public static ExtentTest test;

    @Before
    public void beforeScenario(Scenario scenario) {

        test = ExtentManager
                .getInstance()
                .createTest(scenario.getName());

    }

//    @After
//    public void afterScenario(Scenario scenario) {
//
//        if (scenario.isFailed()) {
//
//            test.fail("Scenario Failed");
//
//        } else {
//
//            test.pass("Scenario Passed");
//
//        }
//
//        ExtentManager.getInstance().flush();
//
//    }

    @After
    public void afterScenario(Scenario scenario) {

        if (scenario.isFailed()) {

            String screenshotPath =
                    ScreenshotUtil.captureScreenshot(scenario.getName());

            test.fail("Scenario Failed");

            try {

                test.addScreenCaptureFromPath(screenshotPath);

            } catch (Exception e) {

                e.printStackTrace();

            }

        } else {

            test.pass("Scenario Passed");

        }

        ExtentManager.getInstance().flush();
    }

}
