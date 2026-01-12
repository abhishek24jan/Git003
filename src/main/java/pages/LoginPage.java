package pages;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    WebDriver driver;

    @FindBy(id = "userEmail")
    WebElement txt_email;

    @FindBy(id = "userPassword")
    WebElement txt_password;

    @FindBy(name = "login")
    WebElement btn_login;

    @FindBy(css = "#toast-container")
    WebElement invalidMsg;

    
   
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(String email, String password) {
        txt_email.clear();
        txt_email.sendKeys(email);
        txt_password.clear();
        txt_password.sendKeys(password);
        btn_login.click();
    }

    public String getErrorMessage() {
    	 try {
    	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
    	        wait.until(ExpectedConditions.visibilityOf(invalidMsg));

    	        return invalidMsg.getText().trim();
    	    } catch (Exception e) {
    	        return "";   // never return null
    	    }
    }
}
