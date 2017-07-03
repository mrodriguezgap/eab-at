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

    /**
     * Initialize Test Suite parameters, including TestLink connection
     * @param URL String representing TestLink Access URL
     * @param devKey String representing TestLink Access API Developer key
     * @param projectName String representing TestLink Test Project name
     * @param testProjectID String representing TestLink Test Project unique identifier
     * @param planName String representing TestLink Test Plan name
     * @param testBuildName String representing TestLink Test Build name
     * @param testBuildNotes String representing TestLink Test Build notes
     */
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

    /**
     * Setup Selenium web driver and browser to test
     * @param browser String representing the browser name
     * @param useCapabilities Boolean value to turn on or off additional browser capabilities
     */
    @BeforeMethod(alwaysRun = true)
    @Parameters({"browserName", "useCapabilities"})
    public void setup(String browser, Boolean useCapabilities) {
        super.setup(browser, useCapabilities);
    }

    /**
     * Method used to finish WebDriver execution and to close browser
     */
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

    /**
     * Gets current Selenium web driver instance
     * @return Current Selenium Web Driver
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Gets current TestLink access session
     * @return Object representing current TestLink access session
     */
    public TestLinkAccess getTestLinkAccess() {
        return this.testLinkAccess;
    }

    /**
     * Gets current TestLink unique test project identifier
     * @return Integer representing current TestLink unique project identifier
     */
    public int getTestProjectID() {
        return testProjectID;
    }

    /**
     * Gets TestLink unique test plan identifier
     * @return Integer representing current TestLink unique test plan identifier
     */
    public int getTestPlanID() {
        return testPlanID;
    }

    /**
     * Gets TestLink unique test build identifier
     * @return Integer representing current TestLink unique test build identifier
     */
    public int getTestBuildID() {
        return testBuildID;
    }

    /**
     * Gets TestLink test build name
     * @return String representing current TestLink test build name
     */
    public String getTestBuildName() {
        return testBuildName;
    }

    /**
     * Gets TestLink test build notes
     * @return String representing current TestLink test build notes
     */
    public String getTestBuildNotes() {
        return testBuildNotes;
    }

    /**
     * Gets TestLink unique test case identifier
     * @return Integer representing current TestLink unique test case identifier
     */
    public int getTestCaseID() {
        return testCaseID;
    }

    /**
     * Sets the value of a test case ID
     * @param testCaseID String representing the test case ID
     */
    public void setTestCaseID(int testCaseID) {
        this.testCaseID = testCaseID;
    }

    /**
     * Gets TestLink unique test version identifier
     * @return Integer representing current TestLink unique test version identifier
     */
    public int getTestCaseVersion() {
        return testCaseVersion;
    }

    /**
     * Sets the value of a test case version
     * @param testCaseVersion String representing the test case version
     */
    public void setTestCaseVersion(int testCaseVersion) {
        this.testCaseVersion = testCaseVersion;
    }

}
