package com.practice.stepdefinitions;

import com.practice.pageobjects.HomePage;
import com.practice.pageobjects.LoginPage;
import com.practice.utils.ConfigReader;
import com.practice.utils.DriverUtils;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashSet;

public class StepDefinitions extends DriverUtils {

    WebDriver driver;

    public StepDefinitions() {
        this.driver = DriverUtils.getDriver();
    }

    LoginPage loginPage = new LoginPage(driver);
    HomePage homePage = new HomePage(driver);

    @Given("User landed in a home page")
    public void UserLandedInAHomePage() {
        driver.get(ConfigReader.getValue("ApplicationURL"));
    }

    @Then("I enter the username")
    public void iEnterTheUsername() {
        loginPage.enterUsername();
    }

    @Then("I enter the password")
    public void iEnterThePassword() {
        loginPage.enterPassword();
    }

    @And("I click on login button")
    public void iClickOnLoginButton() {
        loginPage.clickLoginButton();
    }

    @Then("verify login is successful")
    public void verifyLoginIsSuccessful() {
        homePage.verifyLoginSuccessful();
    }
}
