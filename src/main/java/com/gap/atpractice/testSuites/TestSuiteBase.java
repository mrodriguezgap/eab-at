package com.gap.atpractice.testSuites;

import com.gap.atpractice.selenium.SeleniumBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

/**
 * Created by auto on 25/05/17.
 */
public class TestSuiteBase extends SeleniumBase {

    //protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browserName"})
    public void setup(String browser) {
        super.setup(browser, false);
    }

    @AfterMethod(alwaysRun = true)
    public void finish() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        super.quitDriver();
    }

    // Old init methods used for testing pageObjects *****

//    private WebDriver initWithCapabilities(String browser) throws Exception {
//        try {
//            return super.setup(browser, true);
//        } catch (Exception e) {
//            throw e;
//        }
//    }
//
//    private WebDriver initWithoutCapabilities(String browser) throws Exception {
//        try {
//            return super.setup(browser, false);
//        } catch (Exception e) {
//            throw e;
//        }
//    }

}
