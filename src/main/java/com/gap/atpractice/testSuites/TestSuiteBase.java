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

    private String testLinkURL;
    private String testLinkKey;
    private int testProjectID;
    protected int testPlanID;
    private int testCaseID;
    private int testCaseVersion;
    private int testBuildID;
    private String testBuildName;
    private String testBuildNotes;

    @BeforeSuite(alwaysRun = true)
    @Parameters({"testLinkURL", "testLinkKey", "testProjectID", "testPlanID", "testBuildID", "testBuildName",
            "testBuildNotes"})
    public void setupTestParameters(String URL, String devKey, int testProjectID, String testPlanID, String testBuildID,
                                    String testBuildName, String testBuildNotes) {
        try {
            testLinkURL = URL;
            testLinkKey = devKey;
            this.testProjectID = testProjectID;
            this.testBuildID = Integer.valueOf(testBuildID);
            this.testBuildName = testBuildName;
            this.testBuildNotes = testBuildNotes;
           // this.testPlanID = Integer.valueOf(testPlanID);

//            System.out.println("Connecting to TestLink...");
//            testLinkAccess = new TestLinkAccess(new URL(URL), devKey);
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

    /*
    private TestLinkAccess testLinkAccess;
    private String testLinkURL;
    private String testLinkKey;
    private int testProjectID;
    private int testPlanID;
    private int testCaseID;
    private int testCaseVersion;
    private int testBuildID;
    private String testBuildName;
    private String testBuildNotes;
     */

    public WebDriver getDriver() {
        return driver;
    }

    public String getTestLinkURL() {
        return testLinkURL;
    }

    public void setTestLinkURL(String url) {
        this.testLinkURL = url;
    }

    public String getTestLinkKey() {
        return testLinkKey;
    }

    public void setTestLinkKey(String key) {
        this.testLinkKey = key;
    }

    public int getTestProjectID() {
        return testProjectID;
    }

    public void setTestProjectID(int id) {
        this.testProjectID = id;
    }

    public int getTestBuildID() {
        return testBuildID;
    }

    public void setTestBuildID(int id) {
        this.testBuildID = id;
    }

    public String getTestBuildName() {
        return testBuildName;
    }

    public void setTestBuildName(String name) {
        this.testBuildName = name;
    }

    public String getTestBuildNotes() {
        return testBuildNotes;
    }

    public void setTestBuildNotes(String notes) {
        this.testBuildNotes = notes;
    }

    public int getTestPlanID() {
        return testPlanID;
    }

    public void setTestPlanID(int id) {
        this.testPlanID = id;
    }

    public TestLinkAccess getTestLinkAccess() {
        return this.testLinkAccess;
    }

    public void setTestLinkAccess(TestLinkAccess access) {
        this.testLinkAccess = access;
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
