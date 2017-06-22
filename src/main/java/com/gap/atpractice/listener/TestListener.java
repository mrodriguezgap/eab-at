package com.gap.atpractice.listener;

import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.model.TestCase;
import com.gap.atpractice.testLinkAccess.TestLinkAccess;
import com.gap.atpractice.testSuites.TestSuiteBase;
import com.gap.atpractice.utils.TakeScreenshots;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by manuel on 08/06/17.
 */
public class TestListener implements ITestListener {

    private static final String FILE_PATH = "./src/main/resources/screenshots/";

    private TestLinkAccess testLinkAccess;

    private String url;
    private String devKey;
    private int testCaseID;
    private int testBuildID;
    private String testBuildName;
    private String testBuildNotes;
    private int testPlanID;
    private int testProjectID;
    private int version;
    private int platformID;
    private int order;
    private int urgency;

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println(String.format("%s : %s", "Successfully executed test", iTestResult.getTestName()));
        initParameters(iTestResult);
        if (checkExistingTestCase() == null) {
            addTestCaseToTestPlan();
        }
        updateTestCaseSuccess();
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        WebDriver driver = ((TestSuiteBase) (iTestResult.getInstance())).getDriver();
        TakeScreenshots.takescreenshot(driver, FILE_PATH);

        System.out.println(String.format("%s : %s", "Test execution failed", iTestResult.getTestName()));
        initParameters(iTestResult);
        if (checkExistingTestCase() != null) {
            addTestCaseToTestPlan();
        }
        updateTestCaseFailure();
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
        System.out.println("Starting tests...");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("Finishing tests...");
    }


    private void updateTestCaseSuccess() {
        try {
            testLinkAccess.updateTestCaseExecution(testCaseID, null, testPlanID,
                    ExecutionStatus.PASSED, testBuildID, testBuildName, testBuildNotes, false, "",
                    0, "", null, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateTestCaseFailure() {
        try {
            testLinkAccess.updateTestCaseExecution(testCaseID, null, testPlanID,
                    ExecutionStatus.FAILED, testBuildID, testBuildName, testBuildNotes, false, "",
                    0, "", null, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addTestCaseToTestPlan() {
        try {
            testLinkAccess.addTestCaseToTestPlan(testProjectID, testPlanID, testCaseID, version, platformID,
                    order, urgency);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private TestCase checkExistingTestCase() {
        TestCase testCase = null;
        try {
            testCase = testLinkAccess.checkExistingTestCase(testPlanID, testCaseID, testBuildID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return testCase;
    }

    private void initParameters(ITestResult iTestResult) {
        this.url = ((TestSuiteBase) (iTestResult.getInstance())).getTestLinkURL();
        this.devKey = ((TestSuiteBase) (iTestResult.getInstance())).getTestLinkKey();
        this.testCaseID = ((TestSuiteBase) (iTestResult.getInstance())).getTestCaseID();
        this.testBuildID = ((TestSuiteBase) (iTestResult.getInstance())).getTestBuildID();
        this.testBuildName = ((TestSuiteBase) (iTestResult.getInstance())).getTestBuildName();
        this.testBuildNotes = ((TestSuiteBase) (iTestResult.getInstance())).getTestBuildNotes();
        this.testPlanID = ((TestSuiteBase) (iTestResult.getInstance())).getTestPlanID();
        this.testProjectID = ((TestSuiteBase) (iTestResult.getInstance())).getTestProjectID();
        this.version = ((TestSuiteBase) (iTestResult.getInstance())).getTestCaseVersion();
        this.platformID = 0;
        this.order = 1;
        this.urgency = 1;

        try {
            this.testLinkAccess = setupTestLinkAccess(url, devKey);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private TestLinkAccess setupTestLinkAccess(String url, String devKey) throws MalformedURLException {
        return new TestLinkAccess(new URL(url), devKey);
    }
}
