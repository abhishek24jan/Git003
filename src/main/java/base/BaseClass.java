package base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class BaseClass {

    public static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(BaseClass.class);

    @BeforeMethod
    public void setup() {
        logger.info("Starting browser and navigating to  login page");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client/#/auth/login");
    }

    public String takeScreenshot(String testName) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = "./screenshots/" + testName + ".png";
        FileHandler.copy(src, new File(path));
        return path;
    }

    // static helper used by listener
    public static String takeScreenshotStatic(String testName) throws IOException {
        if (driver == null) {
            throw new IOException("Driver is null - cannot take screenshot");
        }
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = "./screenshots/" + testName + ".png";
        FileHandler.copy(src, new File(path));
        return path;
    }

    @AfterMethod
    public void teardown() {
        logger.info("Closing browser");
        if (driver != null) {
            driver.quit();
        }
    }
}
