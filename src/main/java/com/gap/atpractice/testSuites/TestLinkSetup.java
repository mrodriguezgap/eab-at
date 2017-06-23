package com.gap.atpractice.testSuites;

import br.eti.kinoshita.testlinkjavaapi.model.Build;
import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.net.URL;

/**
 * Created by manuel on 6/22/17.
 */
public class TestLinkSetup extends TestSuiteBase {

    @BeforeSuite(alwaysRun = true)
    @Parameters({"testLinkURL", "testLinkKey", "runSetupFlag"})
    public void setupTestLink(String url, String devKey, String runSetupFlag) {
        try {
            if (Boolean.valueOf(runSetupFlag)) {
                super.setTestLinkURL(url);
                super.setTestLinkKey(devKey);

                System.out.println("Connecting to TestLink...");
                super.setTestLinkAccess(new com.gap.atpractice.testLinkAccess.TestLinkAccess(new URL(url), devKey));
            }
        } catch (Exception e) {
            System.out.println("Could not connect to TestLink");
            e.printStackTrace();
        }

    }

    @BeforeTest(alwaysRun = true)
    @Parameters({"planName", "projectName", "notes", "isActive", "isPublic", "runSetupPlanFlag"})
    public void setupTestPlan(String planName, String projectName, String notes, String isActive, String isPublic,
                              String runSetupPlanFlag) {
        try {
            if (Boolean.valueOf(runSetupPlanFlag)) {
                System.out.println("Creating test plan...");
                TestPlan testLinkPlan = super.getTestLinkAccess().createTestPlan(planName, projectName, notes,
                        Boolean.valueOf(isActive), Boolean.valueOf(isPublic));
                this.setTestPlanID(testLinkPlan.getId());
                this.testPlanID = testLinkPlan.getId();
            }
        } catch (Exception e) {
            System.out.println("Could not create test plan");
            e.printStackTrace();
        }
    }

    @BeforeTest(dependsOnMethods = "setupTestPlan")
    @Parameters({"testBuildName", "testBuildNotes", "runSetupBuildFlag"})
    public void setupTestBuild(String buildName, String buildNotes, String runSetupBuildFlag) {
        try {
            if (Boolean.valueOf(runSetupBuildFlag)) {
                System.out.println("Creating test build...");
                Build testLinkBuild = super.getTestLinkAccess().
                        createTestBuild(super.getTestPlanID(), buildName, buildNotes);
                super.setTestBuildID(testLinkBuild.getId());
                //super.setTest
            }
        } catch (Exception e) {
            System.out.println("Could not create test build");
            e.printStackTrace();
        }
    }

}
