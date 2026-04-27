package com.practice.pageobjects;

import com.practice.utils.DriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.practice.utils.BaseUtils;
import com.practice.utils.LoggerUtils;
import static com.practice.utils.BaseUtils.*;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = DriverUtils.getDriver();
    }
    BaseUtils baseUtils = new BaseUtils(driver);
    By accountOverviewTitle = By.xpath("//div[@id='showOverview']//h1[@class='title']");

    public void verifyLoginSuccessful(){
        verifyTextUsingContains(accountOverviewTitle, "Accounts Overview");
    }

}
