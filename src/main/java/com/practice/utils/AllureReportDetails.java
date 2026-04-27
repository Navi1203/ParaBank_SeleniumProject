package com.practice.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class AllureReportDetails {

    public static void writeTestDetails(){

        try {
            Properties properties = new Properties();

            String projectPath = System.getProperty("user.dir");
            FileInputStream fis  = new FileInputStream(projectPath + "/com/practice/config/Config.properties");
            properties.load(fis);

            properties.setProperty("Environment", ConfigReader.getValue("environment"));
            properties.setProperty("Browser", ConfigReader.getValue("browser"));
            properties.setProperty("HeadLess Mode", ConfigReader.getValue("headless"));

            FileWriter writer = new FileWriter("allure-reults/environment.properties");
            properties.store(writer,"Allure Environment Properties");

            LoggerUtils.info("Allure Test Details successfully written");

        } catch (IOException e) {
            LoggerUtils.error("Error in writing Allure Test Details");
            throw new RuntimeException(e);
        }
    }

//    public static void attachScreenShotToAllureReport(){
//
//        byte[] screenshot =null;
//
//       if(driver!=null){
//
//       }
//    }
}
