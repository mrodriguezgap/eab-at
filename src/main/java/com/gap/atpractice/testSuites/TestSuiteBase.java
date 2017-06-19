package com.gap.atpractice.testSuites;

import br.eti.kinoshita.testlinkjavaapi.constants.TestCaseDetails;
import br.eti.kinoshita.testlinkjavaapi.model.Build;
import br.eti.kinoshita.testlinkjavaapi.model.TestCase;
import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;
import br.eti.kinoshita.testlinkjavaapi.model.TestSuite;
import com.gap.atpractice.selenium.SeleniumBase;
import com.gap.atpractice.testLinkAccess.TestLinkAccess;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.net.URL;

/**
 * Created by manuel on 25/05/17.
 */
public class TestSuiteBase extends SeleniumBase {

    protected TestLinkAccess testLinkAccess;
    protected TestPlan testLinkPlan;
    protected Build testLinkBuild;
    protected TestSuite testLinkSuite;
    private String testCaseID;

    /**
     * Gets the value of each test case ID
     * @return String representing the value of a test case ID
     */
    public String getTestCaseID() {
        return testCaseID;
    }

    /**
     * Sets the value of a test case ID
     * @param testCaseID String representing the test case ID
     */
    public void setTestCaseID(String testCaseID) {
        this.testCaseID = testCaseID;
    }

    // TODO REGEX to validate test case ID format
    public Boolean validateTestCaseID(String testCaseID) {
        // Finish method implementation
        return true;
    }

    public TestCase getTestCase(String testCaseID)
    {
        // TODO
        /*
        Get a test case using ID taken from test execution
        On Test Success on listener, update test case status,
        calling this class method, and using the test case ID
         */
        return null;
    }

    @BeforeSuite(alwaysRun = true)
    @Parameters({"tLinkURL", "tLinkKey"})
    public void setupTestLink(String URL, String devKey) {
        try {
            System.out.println("Connecting to TestLink...");
            testLinkAccess = new TestLinkAccess(new URL(URL), devKey);
        } catch (Exception e) {
            System.out.println("Could not connect to TestLink");
            e.printStackTrace();
        }
    }

    @BeforeTest(alwaysRun = true)
    @Parameters({"planName", "projectName", "notes", "isActive", "isPublic"})
    public void setupTestPlan(String planName, String projectName, String notes, String isActive, String isPublic) {
        try {
            System.out.println("Creating test plan...");
            testLinkPlan = testLinkAccess.createTestPlan(planName, projectName, notes,
                    Boolean.valueOf(isActive), Boolean.valueOf(isPublic));
            testLinkPlan.setPublic(true);
        } catch (Exception e) {
            System.out.println("Could not create test plan");
            e.printStackTrace();
        }
    }

    @BeforeGroups(groups = "test_001")
    @Parameters({"testPlanID", "buildName", "buildNotes"})
    public void setupTestBuild(String testPlanID, String buildName, String buildNotes) {
        try {
            System.out.println("Creating test build...");
            testLinkBuild = testLinkAccess.createTestBuild(Integer.valueOf(testPlanID), buildName, buildNotes);
        } catch (Exception e) {
            System.out.println("Could not create test build");
            e.printStackTrace();
        }
    }

    @BeforeGroups(groups = "test_001")
    @Parameters({"testSuiteID","testCaseID"})
    public void setupTestSuite(String suiteID, String testCaseID)
    {
        testLinkSuite = testLinkAccess.getTestSuiteDetails(Integer.valueOf(suiteID));
        TestCase[] cases =
        testLinkAccess.getTestCasesForTestSuite(testLinkSuite.getId(), false,
                testLinkAccess.getTestCaseDetails());
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browserName", "useCapabilities"})
    public void setup(String browser, Boolean useCapabilities) {
        super.setup(browser, useCapabilities);
    }

    @AfterMethod(alwaysRun = true)
    public void finish() {
        // This Thread.sleep should not be used, however I keep it for testing purposes
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        super.quitDriver();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
