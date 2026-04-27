package com.practice.pageobjects;


import com.practice.utils.BaseUtils;
import com.practice.utils.ConfigReader;
import com.practice.utils.DriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.practice.utils.LoggerUtils;

public class LoginPage extends BaseUtils {

    WebDriver driver;

    public LoginPage(WebDriver driver){
        super(driver);
        this.driver = DriverUtils.getDriver();
    }

    By usernameTextBox = By.xpath("//input[@name='username']");
    By passwordTextBox = By.xpath("//input[@name='password']");
    By loginButton = By.xpath("//input[@type='submit']");

    public void enterUsername(){

        enterValue(usernameTextBox, ConfigReader.getValue("PARA_BANK_USERNAME"));
        LoggerUtils.info("Username entered successfully");
    }

    public void enterPassword(){
        enterValue(passwordTextBox,ConfigReader.getValue("PARA_BANK_PASSWORD"));
        LoggerUtils.info("Password entered successfully");
    }

    public void clickLoginButton(){
       try{
           clickButton(loginButton);
           LoggerUtils.info("Login button Clicked Successfully");
       } catch (RuntimeException e) {
           throw new RuntimeException(e);
       }
    }
}
