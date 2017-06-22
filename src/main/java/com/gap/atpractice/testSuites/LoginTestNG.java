package com.gap.atpractice.testSuites;


import com.gap.atpractice.dataProvider.DataProviderTest;
import com.gap.atpractice.pageObject.HomePage;
import com.gap.atpractice.testSuites.commons.CommonTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by manuel on 22/05/17.
 */
public class LoginTestNG extends TestSuiteBase {

    private CommonTest commonTest;

    public LoginTestNG() {
        commonTest = new CommonTest();
    }

    /**
     * Test user is sable to log in to application and is taken to Home page
     * @param userName User name
     * @param password User password
     * @param testCaseID_001 Test case ID from TestLink
     * @param testCaseVersion Test case version from TestLink
     */
    @Test(groups = "test_001")
    @Parameters({"userName", "userPassword", "testCaseID_001", "testCaseVersion"})
    public void loginSuccessful(String userName, String password, String testCaseID_001, String testCaseVersion) {
        super.setTestCaseID(Integer.valueOf(testCaseID_001));
        super.setTestCaseVersion(Integer.valueOf(testCaseVersion));

        HomePage homePage = commonTest.login(driver, userName, password);
        Assert.assertEquals(homePage.checkHomePage(), true, "Home Page not loaded");
        System.out.println(homePage.getPageTitle());

    }

    /**
     * Test the page displays an error message due to incorrect login credentials
     * NOTE: Current assertion checks for home page, since no error message is configured
     * @param name User login id
     * @param password User login password
     */
    @Test(groups = "test_001", dataProvider = "dataProviderUser",
            dataProviderClass = DataProviderTest.class)
    public void testUnsuccessful(String name, String password){
        Assert.assertEquals(commonTest.login(driver, name, password).getPageTitle(),
                "Login | SSC Campus", "Login Page Not Found");
    }

}
