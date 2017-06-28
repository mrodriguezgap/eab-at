package com.gap.atpractice.listener;

import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.model.TestCase;
import com.gap.atpractice.testLinkAccess.TestLinkAccess;
import com.gap.atpractice.testSuites.TestSuiteBase;
import com.gap.atpractice.utils.TakeScreenshots;
import org.openqa.selenium.WebDriver;
import org.testng.*;

/**
 * Created by manuel on 08/06/17.
 */
public class TestListener implements ITestListener {

    private static final String FILE_PATH = "./src/main/resources/screenshots/";

    private TestLinkAccess testLinkAccess;

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

    /**
     * Method run on any <Test> tag on TestNG XML
     * Currently, initiates the parameters for every TestLink configuration
     * @param iTestResult Test execution results
     */
    @Override
    public void onTestStart(ITestResult iTestResult) {
        initSuiteParameters(iTestResult);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        try {
            System.out.println(String.format("%s : %s", "Successfully executed test",
                    iTestResult.getMethod().getMethodName()));
            initTestParameters(iTestResult);
            if (checkExistingTestCase() == null) {
                addTestCaseToTestPlan();
            }
            updateTestCaseStatus(ExecutionStatus.PASSED);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        try {
            System.out.println(String.format("%s : %s", "Test execution failed",
                    iTestResult.getMethod().getMethodName()));
            initTestParameters(iTestResult);

            WebDriver driver = ((TestSuiteBase) (iTestResult.getInstance())).getDriver();
            TakeScreenshots.takescreenshot(driver, FILE_PATH, String.valueOf(this.testCaseID));
            if (checkExistingTestCase() != null) {
                addTestCaseToTestPlan();
            }
            updateTestCaseStatus(ExecutionStatus.FAILED);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        try {
            System.out.println(String.format("%s : %s", "Test execution skipped",
                    iTestResult.getMethod().getMethodName()));
            initTestParameters(iTestResult);

            WebDriver driver = ((TestSuiteBase) (iTestResult.getInstance())).getDriver();
            TakeScreenshots.takescreenshot(driver, FILE_PATH, String.valueOf(this.testCaseID));
            if (checkExistingTestCase() != null) {
                addTestCaseToTestPlan();
            }
            updateTestCaseStatus(ExecutionStatus.NOT_RUN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("Starting tests...");
        //iTestContext.getAttribute();
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("Finishing tests...");
    }


    private void updateTestCaseStatus(ExecutionStatus status) {
        try {
            testLinkAccess.updateTestCaseExecution(testCaseID, null, testPlanID,
                    status, testBuildID, testBuildName, testBuildNotes, false, "",
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

    private void initSuiteParameters(ITestResult iTestResult) {

        try {
            this.testProjectID = ((TestSuiteBase) (iTestResult.getInstance())).getTestProjectID();
            this.testPlanID = ((TestSuiteBase) (iTestResult.getInstance())).getTestPlanID();
            this.testBuildID = ((TestSuiteBase) (iTestResult.getInstance())).getTestBuildID();
            this.testBuildName = ((TestSuiteBase) (iTestResult.getInstance())).getTestBuildName();
            this.testBuildNotes = ((TestSuiteBase) (iTestResult.getInstance())).getTestBuildNotes();
            this.testLinkAccess = ((TestSuiteBase) (iTestResult.getInstance())).getTestLinkAccess();

            this.platformID = 0;
            this.order = 1;
            this.urgency = 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initTestParameters(ITestResult iTestResult) {
        try {
            this.testCaseID = ((TestSuiteBase) (iTestResult.getInstance())).getTestCaseID();
            this.version = ((TestSuiteBase) (iTestResult.getInstance())).getTestCaseVersion();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
