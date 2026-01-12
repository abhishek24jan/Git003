package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import base.BaseClass;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestListener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Starting test: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test failed: " + result.getName());
        // take screenshot
        try {
            String name = result.getName();
            String path = BaseClass.takeScreenshotStatic(name);
            logger.info("Saved screenshot: " + path);
        } catch (IOException e) {
            logger.error("Screenshot capture failed: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("Test skipped: " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) { }

    @Override
    public void onStart(ITestContext context) {
        logger.info("Test Suite started");
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Test Suite finished");
    }
}
