package utils;

import drivers.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtil {

    public static String captureScreenshot(String scenarioName) {

        WebDriver driver = DriverFactory.getDriver();

        TakesScreenshot ts = (TakesScreenshot) driver;

        File source = ts.getScreenshotAs(OutputType.FILE);

        String timestamp =
                LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        String destination =
                "screenshots/" +
                        scenarioName.replaceAll(" ", "_")
                        + "_" + timestamp + ".png";

        try {

            FileUtils.copyFile(source, new File(destination));

        } catch (IOException e) {

            e.printStackTrace();
        }

        return destination;
    }
}