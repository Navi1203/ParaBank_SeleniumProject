package com.practice.utils;

import io.cucumber.core.gherkin.Step;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShotUtils {


    public static void takeScreenShot(Scenario scenario) throws IOException {

        WebDriver driver = DriverUtils.getDriver();

        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String scenarioName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");
        String timestamp = String.valueOf(System.currentTimeMillis());
        String folderPath = "src/test/resources/screenshots/" + date + "/" + scenarioName + "/";

        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();   // ✅ creates all parent directories
        }

        TakesScreenshot ts = (TakesScreenshot)driver;
        File screenshot = ts.getScreenshotAs(OutputType.FILE);
        Path destination = Paths.get(folderPath+timestamp+ ".png");
        Files.copy(screenshot.toPath(),destination);
        LoggerUtils.info("📸 Screenshot saved: " + destination);
    }


}
