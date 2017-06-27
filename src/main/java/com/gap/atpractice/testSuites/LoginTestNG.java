package com.gap.atpractice.testSuites;


import com.gap.atpractice.pageObject.HomePage;
import com.gap.atpractice.pageObject.LoginPage;
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

    @Test(groups = "test_001")
    @Parameters({"userName", "userPassword", "testCaseID_001", "testCaseVersion"})
    public void testLoginSuccess(String userName, String password, String testCaseID_001, String testCaseVersion) {
        super.setTestCaseID(Integer.valueOf(testCaseID_001));
        super.setTestCaseVersion(Integer.valueOf(testCaseVersion));

        HomePage homePage = commonTest.login(driver, userName, password);
        Assert.assertEquals(homePage.checkHomePage(), true, "Home Page not loaded");
        System.out.println(homePage.getPageTitle());

    }

    @Test(groups = "test_001")
    @Parameters({"userName", "badUserPassword", "testCaseID_002", "testCaseVersion"})
    public void testLoginFailed(String userName, String password, String testCaseID_001, String testCaseVersion) {
        super.setTestCaseID(Integer.valueOf(testCaseID_001));
        super.setTestCaseVersion(Integer.valueOf(testCaseVersion));

        LoginPage login = (LoginPage) new LoginPage(driver).get();
        login.login(userName, password);
        Assert.assertEquals(login.getPageTitle(), "Login | SSC Campus", "Login Page not loaded");
        System.out.println(login.getPageTitle());

    }



    /**
     * Test the page displays an error message due to incorrect login credentials
     * NOTE: Current assertion checks for home page, since no error message is configured
     * @param name User login id
     * @param password User login password
     */
//    @Test(groups = "test_003", dataProvider = "dataProviderUser",
//            dataProviderClass = DataProviderTest.class)
//    private void testLoginError(String name, String password){
//        // call static test case from CommonTest.java
//       // System.out.println(new LoginPage(driver).getPageTitle());
//        Assert.assertEquals(commonTest.login(driver, name, password).getPageTitle(),
//                "Login | SSC Campus", "Login Page Not Found");
//    }

}
