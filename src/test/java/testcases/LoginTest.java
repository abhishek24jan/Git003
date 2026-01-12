package testcases;

import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.*;

import base.BaseClass;
import pages.LoginPage;
import utils.ExcelUtils;
import utils.ExtentManager;
import utils.RetryAnalyzer;
import utils.TestListener;

import com.aventstack.extentreports.*;
import org.testng.annotations.Listeners;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Listeners({TestListener.class})
public class LoginTest extends BaseClass {

    ExtentReports report;
    ExtentTest test;
    private static final Logger logger = LogManager.getLogger(LoginTest.class);

    @BeforeTest
    public void startReport() {
        report = ExtentManager.createInstance();
    }

    @Test(dataProvider = "LoginData", dataProviderClass = ExcelUtils.class, retryAnalyzer = RetryAnalyzer.class)
    public void verifyInvalidLogin(HashMap<String, String> dataMap) {

        test = report.createTest("Invalid Login Test: " + dataMap.get("email"));
        logger.info("Running test for: " + dataMap.get("email"));

        System.out.println("Print hello");
        System.out.println("Print hello2");
        
        LoginPage lp = new LoginPage(driver);
        lp.login(dataMap.get("email"), dataMap.get("password"));

        try {
            String errorMsg = lp.getErrorMessage();
            test.info("Error Message: " + errorMsg);
            logger.info("Captured error message: " + errorMsg);

            Assert.assertTrue(
                errorMsg.contains("isn't connected") || errorMsg.contains("Incorrect email or password."),
                "Validation failed: Error message not shown!"
            );

            test.pass("Invalid credentials validated successfully!");
            logger.info("Test passed for: " + dataMap.get("email"));

        } catch (Exception e) {
            test.fail("Test Failed: " + e.getMessage());
            logger.error("Test threw exception: " + e.getMessage());
            Assert.fail();
        }
    }

    @AfterTest
    public void endReport() {
        report.flush();
    }
}
