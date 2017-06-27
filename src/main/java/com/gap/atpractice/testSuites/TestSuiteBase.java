package com.gap.atpractice.testSuites;

import br.eti.kinoshita.testlinkjavaapi.model.Build;
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

    private TestLinkAccess testLinkAccess;

    private int testProjectID;
    private int testPlanID;
    private int testCaseID;
    private int testCaseVersion;
    private int testBuildID;
    private String testBuildName;
    private String testBuildNotes;

    @BeforeSuite(alwaysRun = true)
    @Parameters({"testLinkURL", "testLinkKey", "projectName", "testProjectID", "planName", "testBuildName",
            "testBuildNotes"})
    public void setupTestParameters(String URL, String devKey, String projectName, String testProjectID,
                                    String planName, String testBuildName, String testBuildNotes) {
        System.out.println("Setting up TestLink parameters...");
        try {
            this.testLinkAccess = new TestLinkAccess(new URL(URL), devKey);

            this.testProjectID = Integer.valueOf(testProjectID);
            this.testPlanID = testLinkAccess.checkExistingTestPlan(planName, projectName).getId();
            this.testBuildID = testLinkAccess.checkExistingTestBuild(testPlanID, testBuildName).getId();
            this.testBuildName = testBuildName;
            this.testBuildNotes = testBuildNotes;

        } catch (Exception e) {
            System.out.println("Could not setup TestLink parameters");
            e.printStackTrace();
        }
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

    public TestLinkAccess getTestLinkAccess() {
        return this.testLinkAccess;
    }

    public int getTestProjectID() {
        return testProjectID;
    }

    public int getTestPlanID() {
        return testPlanID;
    }

    public int getTestBuildID() {
        return testBuildID;
    }

    public String getTestBuildName() {
        return testBuildName;
    }

    public String getTestBuildNotes() {
        return testBuildNotes;
    }

    /**
     * Gets the value of each test case ID
     *
     * @return String representing the value of a test case ID
     */
    public int getTestCaseID() {
        return testCaseID;
    }

    /**
     * Sets the value of a test case ID
     *
     * @param testCaseID String representing the test case ID
     */
    public void setTestCaseID(int testCaseID) {
        this.testCaseID = testCaseID;
    }

    public int getTestCaseVersion() {
        return testCaseVersion;
    }

    public void setTestCaseVersion(int testCaseVersion) {
        this.testCaseVersion = testCaseVersion;
    }

}
