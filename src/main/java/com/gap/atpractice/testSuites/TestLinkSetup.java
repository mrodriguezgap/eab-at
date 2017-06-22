package com.gap.atpractice.testSuites;

import br.eti.kinoshita.testlinkjavaapi.model.Build;
import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

/**
 * Created by manuel on 6/22/17.
 */
public class TestLinkSetup extends TestSuiteBase {

    @BeforeTest(alwaysRun = true)
    @Parameters({"planName", "projectName", "notes", "isActive", "isPublic"})
    public void setupTestPlan(String planName, String projectName, String notes, String isActive, String isPublic) {
        try {
            System.out.println("Creating test plan...");
            TestPlan testLinkPlan = testLinkAccess.createTestPlan(planName, projectName, notes,
                    Boolean.valueOf(isActive), Boolean.valueOf(isPublic));
            testLinkPlan.setPublic(true);
        } catch (Exception e) {
            System.out.println("Could not create test plan");
            e.printStackTrace();
        }
    }

    /**
     * Make sure to configure correct test groups!!
     * @param testPlanID
     * @param buildName
     * @param buildNotes
     */
    @BeforeGroups(groups = "test_001")
    @Parameters({"testPlanID", "testBuildName", "testBuildNotes"})
    public void setupTestBuild(String testPlanID, String buildName, String buildNotes) {
        try {
            System.out.println("Creating test build...");
            Build testLinkBuild = testLinkAccess.createTestBuild(Integer.valueOf(testPlanID), buildName, buildNotes);
        } catch (Exception e) {
            System.out.println("Could not create test build");
            e.printStackTrace();
        }
    }
}
