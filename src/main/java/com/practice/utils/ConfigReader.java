package com.practice.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class ConfigReader {

        public static Properties properties;

        static {
            loadProperties();
        }

        private static void loadProperties(){
            try{
                FileInputStream fis = new FileInputStream("src/main/java/com/practice/config/Config.properties");
                properties = new Properties();
                properties.load(fis);
                fis.close();
            }
            catch (IOException e) {
                throw new RuntimeException("config.properties file not found! " + e.getMessage());
            }
        }

        public static String getValue(String key){

            String envValue = System.getenv(key);
            if (envValue != null && !envValue.isEmpty()) {
                return envValue;
            }
            String value = properties.getProperty(key);
            if(value==null){
                throw new RuntimeException("Key '" + key + "' not found in config.properties or it is null");
            }
            return value.trim();
        }
}
