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

    protected TestLinkAccess testLinkAccess;

    private String testLinkURL;
    private String testLinkKey;
    private int testProjectID;
    private int testPlanID;
    private int testCaseID;
    private int testCaseVersion;
    private int testBuildID;
    private String testBuildName;
    private String testBuildNotes;

    public String getTestLinkURL() {
        return testLinkURL;
    }

    public String getTestLinkKey() {
        return testLinkKey;
    }

    public int getTestProjectID() {
        return testProjectID;
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

    public int getTestPlanID() {
        return testPlanID;
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

    @BeforeSuite(alwaysRun = true)
    @Parameters({"testLinkURL", "testLinkKey"})
    public void setupTestLink(String URL, String devKey) {
        try {
            testLinkURL = URL;
            testLinkKey = devKey;

            System.out.println("Connecting to TestLink...");
            testLinkAccess = new TestLinkAccess(new URL(URL), devKey);
        } catch (Exception e) {
            System.out.println("Could not connect to TestLink");
            e.printStackTrace();
        }
    }

    @BeforeSuite
    @Parameters({"testProjectID", "testPlanID", "testBuildID", "testBuildName", "testBuildNotes"})
    public void setupTestLinkParameters(int testProjectID, String testPlanID, String testBuildID,
                                        String testBuildName, String testBuildNotes) {
        this.testProjectID = testProjectID;
        this.testBuildID = Integer.valueOf(testBuildID);
        this.testBuildName = testBuildName;
        this.testBuildNotes = testBuildNotes;
        this.testPlanID = Integer.valueOf(testPlanID);
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
