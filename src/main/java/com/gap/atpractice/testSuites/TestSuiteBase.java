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
    private TestPlan testLinkPlan;
    private Build testLinkBuild;
    protected TestSuite testLinkSuite;


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
    @Parameters({"testLinkURL", "testLinkKey", "testProjectID", "testPlanID","testBuildID", "testBuildName", "testBuildNotes"})
    public void setupTestLink(String URL, String devKey, int testProjectID, String testPlanID, String testBuildID,
                              String testBuildName, String testBuildNotes) {
        try {
            testLinkURL = URL;
            testLinkKey = devKey;
            this.testProjectID = testProjectID;
            this.testBuildID = Integer.valueOf(testBuildID);
            this.testBuildName = testBuildName;
            this.testBuildNotes = testBuildNotes;
            this.testPlanID = Integer.valueOf(testPlanID);

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
    @Parameters({"testPlanID", "testBuildName", "testBuildNotes"})
    public void setupTestBuild(String testPlanID, String buildName, String buildNotes) {
        try {
            System.out.println("Creating test build...");
            testLinkBuild = testLinkAccess.createTestBuild(Integer.valueOf(testPlanID), buildName, buildNotes);
        } catch (Exception e) {
            System.out.println("Could not create test build");
            e.printStackTrace();
        }
    }

//    @BeforeGroups(groups = "test_001")
//    @Parameters({"testSuiteID", "testCaseID"})
//    public void setupTestSuite(String suiteID, String testCaseID) {
//        testLinkSuite = testLinkAccess.getTestSuiteDetails(Integer.valueOf(suiteID));
//        TestCase[] cases =
//                testLinkAccess.getTestCasesForTestSuite(testLinkSuite.getId(), false,
//                        testLinkAccess.getTestCaseDetails());
//    }

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
