package com.gap.atpractice.testSuites.commons;

import com.gap.atpractice.pageObject.HomePage;
import com.gap.atpractice.pageObject.LoginPage;
import com.gap.atpractice.testSuites.TestSuiteBase;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/**
 * Created by manuel on 05/06/17.
 */
public class CommonTest {

    /**
     * Perform a login, and returns a home page
     * @param driver WebDriver instance
     * @param userName Unique user identification
     * @param password Unique user password
     * @return a HomePage object, representing home web page
     */
    public HomePage login(WebDriver driver, String userName, String password){
        // Use get() method, because this is the first step always, to load a login page
        LoginPage loginPage = (LoginPage) new LoginPage(driver).get();
        return loginPage.login(userName, password);
    }
}
