package com.practice.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DriverUtils {

    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initializeDriver(String executionMode, String browser) throws MalformedURLException {

        WebDriver webdriver = null;
        boolean isHeadless = Boolean.parseBoolean(ConfigReader.getValue("headless"));

        switch (browser.toLowerCase()) {

            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();

                if (isHeadless) {
                    chromeOptions.addArguments("--headless=new");
                    chromeOptions.addArguments("--disable-gpu");
                    chromeOptions.addArguments("--window-size=1920,1080");
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                }

                chromeOptions.addArguments("--start-maximized");

                if (executionMode.equalsIgnoreCase("grid")) {
                    driver.set(new RemoteWebDriver(
                            new URL(ConfigReader.getValue("gridUrl")), chromeOptions
                    ));
                    LoggerUtils.info("🌐 Running Chrome on Grid");
                } else {
                    webdriver = new ChromeDriver(chromeOptions);
                    driver.set(webdriver);
                }
                break;

            case "safari":
                SafariOptions safariOptions = new SafariOptions();

                if (executionMode.equalsIgnoreCase("grid")) {
                    driver.set(new RemoteWebDriver(
                            new URL(ConfigReader.getValue("gridUrl")), safariOptions
                    ));
                    LoggerUtils.info("🌐 Running Safari on Grid");
                } else {
                    driver.set(new SafariDriver());
                    LoggerUtils.info("🌐 Running Safari Locally");
                }
                break;

            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(
                Integer.parseInt(ConfigReader.getValue("implicitWait"))
        ));
    }
    public static WebDriver getDriver(){
        return driver.get();
    }

    public static void quitDriver(){

    }
}
