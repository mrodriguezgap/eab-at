package com.gap.atpractice.testSuites.commons;

import com.gap.atpractice.pageObject.HomePage;
import com.gap.atpractice.pageObject.LoginPage;
import com.gap.atpractice.testSuites.TestSuiteBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by auto on 05/06/17.
 */
public class commonTest extends TestSuiteBase{

    public static LoginPage loadLoginPage() {
        LoginPage loginPage = (LoginPage) new LoginPage(driver).get();
        System.out.println(loginPage.getPageTitle());
        Assert.assertEquals(loginPage.getPageTitle(), "Login | SSC Campus", "Login Page Not Found");
        return loginPage;
    }

    public static HomePage login(String userName, String password){
        return loadLoginPage().login(userName, password);
    }
}
