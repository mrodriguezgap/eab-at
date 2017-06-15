package com.gap.atpractice.testLinkAccess;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionType;
import br.eti.kinoshita.testlinkjavaapi.constants.TestImportance;
import br.eti.kinoshita.testlinkjavaapi.model.*;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;
import br.eti.kinoshita.testlinkjavaapi.constants.*;

import java.net.URL;
import java.util.List;

/**
 * Created by manuel on 6/14/17.
 */
public class TestLinkAccess extends TestLinkAPI {
    public TestLinkAccess(URL url, String devKey) throws TestLinkAPIException {
        super(url, devKey);
    }

    public TestProject createTestlinkProject(String testProjectName, String testProjectPrefix, String notes, Boolean
            enableRequirements, Boolean enableTestPriority, Boolean enableAutomation, Boolean enableInventory,
                                             Boolean isActive,Boolean isPublic){
        return this.createTestProject(testProjectName, testProjectPrefix, notes, enableRequirements, enableTestPriority,
                enableAutomation, enableInventory, isActive, isPublic);

    }

    public TestPlan createTestLinkPlan(String planName, String projectName, String notes, Boolean isActive,
                                   Boolean isPublic) throws TestLinkAPIException{
        return this.createTestPlan(planName, projectName, notes, isActive, isPublic);
    }

    public TestSuite createTestLinkSuite(Integer testProjectId, String name, String details, Integer parentId,
                                         Integer order, Boolean checkDuplicatedName, String actionOnDuplicatedName)
            throws TestLinkAPIException {
        return this.createTestSuite(testProjectId, name, details, parentId, order, checkDuplicatedName,
                br.eti.kinoshita.testlinkjavaapi.constants.ActionOnDuplicate.valueOf(actionOnDuplicatedName));
    }

    public Build createTestLinkBuild(Integer testPlanId, String buildName, String buildNotes)
            throws TestLinkAPIException {
        return this.createBuild(testPlanId, buildName, buildNotes);
    }

    public TestCase createTestLinkCaseString (String testCaseName, Integer testSuiteId, Integer testProjectId,
                                              String authorLogin, String summary, List<TestCaseStep> steps,
                                              String preconditions, Integer status, TestImportance importance,
                                              ExecutionType execution, Integer order, Integer internalId,
                                              Boolean checkDuplicatedName, String actionOnDuplicatedName)
            throws TestLinkAPIException{
        return this.createTestCase(testCaseName, testSuiteId, testProjectId, authorLogin, summary, steps, preconditions,
                TestCaseStatus.valueOf(status.toString()), importance, execution, order, internalId, checkDuplicatedName,
                ActionOnDuplicate.valueOf(actionOnDuplicatedName));
    }

}
