package com.practice.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.practice.stepdefinitions"},
        tags = "",
        monochrome = true,
        dryRun = false,
        plugin = {
                "pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "html:target/cucumber-reports/report.html",
                "json:target/cucumber-reports/report.json",
                "rerun:target/rerun/rerun.txt"
        }
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios () {
        return super.scenarios ();
    }
}