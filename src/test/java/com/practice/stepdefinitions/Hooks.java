package com.practice.stepdefinitions;

import com.practice.utils.ConfigReader;
import com.practice.utils.DriverUtils;
import com.practice.utils.LoggerUtils;
import io.cucumber.java.After;
import io.cucumber.java.*;
import io.cucumber.java.Scenario;
import java.io.IOException;

import static com.practice.utils.AllureReportDetails.writeTestDetails;
import static com.practice.utils.ScreenShotUtils.takeScreenShot;


public class Hooks extends DriverUtils {



    @Before
    public void setUp() throws IOException {

        String executionMode = System.getenv("EXECUTION_MODE") != null
                ? System.getenv("EXECUTION_MODE")
                : ConfigReader.getValue("executionMode");

        String browser = System.getProperty("browser") != null
                ? System.getProperty("browser")
                : ConfigReader.getValue("browser");

        LoggerUtils.info("🌐 Browser: " + browser);
        LoggerUtils.info("⚙️ Execution Mode: " + executionMode);

        initializeDriver(executionMode,browser);
        LoggerUtils.info("🚀 Test Started");
    }

    @AfterStep
    public void attachEvidenceAndGenerateReports(Scenario scenario) throws IOException {
        takeScreenShot(scenario);

    }

    @After
    public void tearDown() {
        LoggerUtils.info("🏁 Test Finished");
        writeTestDetails();
        DriverUtils.quitDriver();
    }

}
