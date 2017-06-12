package com.gap.atpractice.testSuites;


import com.gap.atpractice.dataProvider.DataProviderTest;
import com.gap.atpractice.pageObject.HomePage;
import com.gap.atpractice.pageObject.LoginPage;
import com.gap.atpractice.testSuites.commons.commonTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by auto on 22/05/17.
 */
public class LoginTestNG extends TestSuiteBase {

//    private static LoginPage loginPage;
//    private static HomePage homePage;

    /*
     */
//    @Test(groups = "test_001")
//    private void testPageObject() {
//        LoginPage loginPage = (LoginPage) new LoginPage(driver).get();
//        System.out.println(loginPage.getPageTitle());
//        Assert.assertEquals(loginPage.getPageTitle(), "Login | SSC Campus", "Login Page Not Found");
//    }

    @Test(groups = "test_001")
    @Parameters({"userName", "userPassword"})
    private void testLoginPO(String userName, String password) throws Exception {
        //commonTest.loadLoginPage();
        HomePage homePage = commonTest.login(userName, password);
        Assert.assertEquals(homePage.checkHomePage(), true, "Home Page not loaded");
        System.out.println(homePage.getPageTitle());
    }

    /**
     * Test the page displays an error message due to incorrect login credentials
     * NOTE: Current assertion checks for home page, since no error message is configured
     * @param name User login id
     * @param password User login password
     */
    @Test(groups = "test_003", dataProvider = "dataProviderUser",
            dataProviderClass = DataProviderTest.class)
    private void testLoginError(String name, String password){
        // call static test case from commonTest.java
       // System.out.println(new LoginPage(driver).getPageTitle());
        Assert.assertEquals(commonTest.login(name, password).getPageTitle(),
                "Login | SSC Campus", "Login Page Not Found");
    }

}
