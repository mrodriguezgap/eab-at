package com.gap.atpractice.testSuites;


import com.gap.atpractice.dataProviders.DataProviderTest;
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

    //private static WebDriver driver;
    private static LoginPage loginPage;
    private static HomePage homePage;
    // private static LoginPageFactory loginPageFactory;
    // private static HomePageFactory homePageFactory;

    /*
     */
//    @Test(groups = "test_001")
//    private void testPageObject() {
//        loginPage = (LoginPage) new LoginPage(driver).get();
//        System.out.println(loginPage.getPageTitle());
//        Assert.assertEquals(loginPage.getPageTitle(), "Login | SSC Campus", "Login Page Not Found");
//    }

    @Test(groups = "test_001")
    @Parameters({"userName", "userPassword"})
    private void testLoginPO(String userName, String password) throws Exception {
        //commonTest.loadLoginPage();
        homePage = commonTest.login(userName, password);
        Assert.assertEquals(homePage.checkHomePage(), true, "Home Page not loaded");
        System.out.println(homePage.getPageTitle());
    }


    @Test(groups = "test_003", dataProvider = "dataProviderUser",
            dataProviderClass = DataProviderTest.class)
    private void testLoginError(String name, String password){
        // call static test case from commonTest.java
        System.out.println(loginPage.getPageTitle());
        commonTest.login(name, password);
    }

}
