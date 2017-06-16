package com.gap.atpractice.listener;

import com.gap.atpractice.testSuites.TestSuiteBase;
import com.gap.atpractice.utils.TakeScreenshots;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Created by manuel on 08/06/17.
 */
public class TestListener implements ITestListener {

    private String FILE_PATH = "./src/main/resources/screenshots/";

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        // Implement a method that takes a screenshot
       WebDriver driver = ((TestSuiteBase)(iTestResult.getInstance())).getDriver();
       TakeScreenshots.takescreenshot(driver, FILE_PATH);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        // Gets stack trace error
        iTestResult.getThrowable().getMessage();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
