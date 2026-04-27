package com.practice.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.practice.utils.LoggerUtils;
import org.testng.Assert;

import java.time.Duration;


public class BaseUtils {

    static WebDriver driver;
    public BaseUtils(WebDriver driver){
        this.driver = DriverUtils.getDriver();
    }

   static int explicitWait = Integer.parseInt(ConfigReader.getValue("explicitWait"));

    public static void waitUntilElementVisible(By locator){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWait));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public static void clickButton(By locator){
        try{
            waitUntilElementVisible(locator);
            driver.findElement(locator).click();
        }
        catch (Exception e){
            LoggerUtils.error("Issue in finding the Element: "+locator);
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void enterValue(By locator, String value){
        try{
            waitUntilElementVisible(locator);
            driver.findElement(locator).sendKeys(value);
            LoggerUtils.info("Entered Password Successfully");
        }
        catch (Exception e){
            LoggerUtils.error("Issue in finding the Element: "+locator);
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void verifyTextUsingContains(By locator,String value){
        try{
            WebElement element = driver.findElement(locator);
            Assert.assertTrue(element.getText().contains(value));
            LoggerUtils.info("Text Asserted using contains successfully");
        }
        catch (Exception e){
            LoggerUtils.error("Text doesn't contains: "+value);
            throw new AssertionError(e.getMessage());
        }
    }

    public static void verifyTextUsingEquals(By locator, String value){
        try{
            WebElement element = driver.findElement(locator);
            Assert.assertEquals(element, value);
            LoggerUtils.info("Text Asserted using equals successfully");
        }
        catch (Exception e){
            LoggerUtils.error("Text assertion fails! "+value);
            throw new AssertionError(e.getMessage());
        }
    }


}
