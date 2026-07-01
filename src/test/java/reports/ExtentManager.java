package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

            ExtentSparkReporter spark =
                    new ExtentSparkReporter("test-output/ExtentReport.html");

            spark.config().setDocumentTitle("Bus Journey Automation");

            spark.config().setReportName("Automation Test Report");

            extent = new ExtentReports();

            extent.attachReporter(spark);

            extent.setSystemInfo("Tester", "Your Name");
            extent.setSystemInfo("Framework", "Selenium Java");
            extent.setSystemInfo("BDD", "Cucumber");
            extent.setSystemInfo("Browser", "Chrome");
        }

        return extent;
    }
}