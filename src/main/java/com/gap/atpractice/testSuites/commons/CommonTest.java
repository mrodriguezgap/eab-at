package com.gap.atpractice.testSuites.commons;

import com.gap.atpractice.pageObject.HomePage;
import com.gap.atpractice.pageObject.LoginPage;
import com.gap.atpractice.testSuites.TestSuiteBase;
import org.testng.Assert;

/**
 * Created by manuel on 05/06/17.
 */
public class CommonTest extends TestSuiteBase {

    /** TODO redundant method, not necessary. Better use a login method with parameters for credentialsm Or use a login valid and invalid
     * Loads the login page
     * @return a LoginPage object, representing login web page
     */
    public static LoginPage loadLoginPage() {
        LoginPage loginPage = (LoginPage) new LoginPage(driver).get();
        System.out.println(loginPage.getPageTitle());
        Assert.assertEquals(loginPage.getPageTitle(), "Login | SSC Campus", "Login Page Not Found");
        return loginPage;
    }

    /**
     * Perform a login, and returns a home page
     * @param userName Unique user identification
     * @param password Unique user password
     * @return a HomePage object, representing home web page
     */
    public static HomePage login(String userName, String password){
        return loadLoginPage().login(userName, password);
    }
}
