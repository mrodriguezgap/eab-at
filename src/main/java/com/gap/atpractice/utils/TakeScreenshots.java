package com.gap.atpractice.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * Created by auto on 24/04/17.
 */
public class TakeScreenshots {

    /**
     * This method takes a screenshot and stores it in format FILE on the specified path
     * @param driver Selenium Web Driver
     * @param path Path where the file will be saved
     */
    public static void takescreenshot(WebDriver driver, String path) {

        // Create a timestamp with current date and time
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        // Create file
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            // Save file
            FileUtils.copyFile(source, new File(path + " " + timestamp));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        //SeleniumBase example = new SeleniumBase();
    }

    //private static void getScreenshotAs(){}
}
