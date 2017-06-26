package com.gap.atpractice.testLinkAccess;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionType;
import br.eti.kinoshita.testlinkjavaapi.constants.TestCaseDetails;
import br.eti.kinoshita.testlinkjavaapi.model.Build;
import br.eti.kinoshita.testlinkjavaapi.model.TestCase;
import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;
import org.apache.commons.lang.ArrayUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by manuel on 6/14/17.
 */
public class TestLinkAccess extends TestLinkAPI {

    public TestLinkAccess(URL url, String devKey) throws TestLinkAPIException {
        super(url, devKey);
    }

    public Boolean checkDevKey(String devKey) {
        return super.checkDevKey(devKey);
    }

    public TestPlan createTestPlan(String planName, String projectName, String notes, Boolean isActive, Boolean isPublic) {
        TestPlan plan = checkExistingTestPlan(planName, projectName);
        if (plan == null) {
            plan = super.createTestPlan(planName, projectName, notes, isActive, isPublic);
        }
        return plan;
    }

    public Build createTestBuild(int testPlanId, String buildName, String buildNotes) {
        Build build = checkExistingTestBuild(testPlanId, buildName);
        if (build == null) {
            build = super.createBuild(testPlanId, buildName, buildNotes);
        }
        return build;
    }

    public void updateTestCaseExecution(Integer testCaseId, Integer testCaseExternalId, Integer testPlanId,
                                        ExecutionStatus status, Integer buildId, String buildName, String notes,
                                        Boolean guess, String bugId, Integer platformId, String platformName,
                                        Map<String, String> customFields, Boolean overwrite) {
        super.setTestCaseExecutionResult(testCaseId, testCaseExternalId, testPlanId, status, buildId, buildName,
                notes, guess, bugId, platformId, platformName, customFields, overwrite);
    }


    public TestPlan checkExistingTestPlan(String planName, String projectName) {
        try {
            System.out.println("Checking if test plan exists...");
            TestPlan[] plans =
                    super.getProjectTestPlans(super.getTestProjectByName(projectName).getId());
            if (!ArrayUtils.isEmpty(plans)) {
                for (TestPlan plan : plans) {
                    if (plan.getName().equals(planName)) {
                        System.out.println("Test plan already exists!!!");
                        return plan;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Build checkExistingTestBuild(int testPlanID, String buildName) {
        try {
            System.out.println("Checking if build exists...");
            Build[] builds = super.getBuildsForTestPlan(testPlanID);
            if (!ArrayUtils.isEmpty(builds)) {
                for (Build build : builds) {
                    if (build.getName().equals(buildName)) {
                        System.out.println("Build already exists!!!");
                        return build;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public TestCase checkExistingTestCase(int testPlanID, int testCaseID, int buildID) {
        try {
            List<Integer> testList = new ArrayList<Integer>();
            testList.add(testCaseID);
            TestCase[] testCases = super.getTestCasesForTestPlan(testPlanID, null, buildID, null,
                    "", true, null, null, ExecutionType.AUTOMATED,
                    true, TestCaseDetails.FULL);
            if (!ArrayUtils.isEmpty(testCases)) {
                for (TestCase tCase : testCases) {
                    if (tCase.getId() == testCaseID) {
                        System.out.println("Test case already exists!!!");
                        return tCase;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
