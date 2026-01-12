package utils;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports createInstance() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        return extent;
    }
}
