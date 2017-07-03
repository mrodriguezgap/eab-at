package com.gap.atpractice.testSuites;

import com.gap.atpractice.testLinkAccess.TestLinkAccess;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.net.URL;

/**
 * Created by manuel on 6/22/17.
 */
public class TestLinkSetup {

    private TestLinkAccess testLinkAccess;
    private int testPlanID;

    /**
     * Initialize TestLink connection
     * @param url String representing TestLink Access URL
     * @param devKey String representing TestLink Access API Developer key
     * @param runSetupFlag Boolean to enable/disable TestLink setup
     */
    @BeforeSuite(alwaysRun = true)
    @Parameters({"testLinkURL", "testLinkKey", "runSetupFlag"})
    public void setupTestLink(String url, String devKey, String runSetupFlag) {
        try {
            if (Boolean.valueOf(runSetupFlag)) {
                System.out.println("Connecting to TestLink...");
                testLinkAccess = new TestLinkAccess(new URL(url), devKey);
            }
        } catch (Exception e) {
            System.out.println("Could not connect to TestLink");
            e.printStackTrace();
        }
    }

    /**
     * Create a TestLink Test plan
     * @param planName String representing test plan name
     * @param projectName String representing test project name
     * @param notes String representing test plan notes
     * @param isActive String representing a boolean value, to set the plan as active/inactive
     * @param isPublic String representing a boolean value, to set the plan as public/private
     * @param runSetupPlanFlag Boolean to enable/disable test plan setup
     */
    @BeforeSuite(dependsOnMethods = "setupTestLink")
    @Parameters({"planName", "projectName", "notes", "isActive", "isPublic", "runSetupPlanFlag"})
    public void setupTestPlan(String planName, String projectName, String notes, String isActive, String isPublic,
                              String runSetupPlanFlag) {
        try {
            if (Boolean.valueOf(runSetupPlanFlag)) {
                System.out.println("Creating test plan...");
                testPlanID = testLinkAccess.createTestPlan(planName, projectName, notes,
                        Boolean.valueOf(isActive), Boolean.valueOf(isPublic)).getId();
            }
        } catch (Exception e) {
            System.out.println("Could not create test plan");
            e.printStackTrace();
        }
    }

    /**
     * Create a TestLink Build plan
     * @param buildName String representing test build name
     * @param buildNotes String representing test build notes
     * @param runSetupBuildFlag Boolean to enable/disable test build setup
     */
    @BeforeSuite(dependsOnMethods = "setupTestPlan")
    @Parameters({"testBuildName", "testBuildNotes", "runSetupBuildFlag"})
    public void setupTestBuild(String buildName, String buildNotes, String runSetupBuildFlag) {
        try {
            if (Boolean.valueOf(runSetupBuildFlag)) {
                System.out.println("Creating test build...");
                testLinkAccess.createTestBuild(this.testPlanID, buildName, buildNotes);
            }
        } catch (Exception e) {
            System.out.println("Could not create test build");
            e.printStackTrace();
        }
    }

}
