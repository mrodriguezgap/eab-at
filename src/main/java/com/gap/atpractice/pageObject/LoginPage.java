package com.gap.atpractice.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by auto on 15/05/17.
 */
public class LoginPage extends PageBase {

    // Page Factory Paths
    // paths
    @FindBy(xpath = "//*/input[contains(@class,'button')]")
    private WebElement buttonLogin;

    @FindBy(xpath = "//input[@id='login']")
    private WebElement userNameField;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordField;

    // paths
    private static final By BUTTONLOGINPATH = By.xpath("//*/input[contains(@class,'button')]");
    private static final By USERNAMEPATH = By.xpath("//input[@id='login']");
    private static final By PASSWORDPATH = By.xpath("//input[@id='password']");
    private static final String PATH = "https://auto3ss-staging7.gradesfirst.com/session/new";

    public LoginPage(WebDriver driver) {
        super(driver);
        super.initElements(driver, this);
    }

    // Page Factory methods *************

    private void sendUserName(String username) {
        super.wait(userNameField).click();
        super.wait(userNameField).sendKeys(username);
    }

    private void sendUserPassword(String password) {
        super.wait(passwordField).click();
        super.wait(passwordField).sendKeys(password);
    }

    private void clickLogin() {
        if (buttonLogin.isDisplayed()) {
            buttonLogin.click();
        } else {
            System.out.println("Could not find button login");
        }
    }

    // Public elements *******************

    public void goToLoginPage(String url) {
        super.goToPage(url);
    }

    public String getPageTitle() {
        return super.getPageTitle();
    }

    public HomePage login(String username, String password) {
        sendUserName(username);
        sendUserPassword(password);
        clickLogin();
        return new HomePage(this.driver);
    }

    // BotStyle *************************

    public HomePage botLogin(String username, String password) throws Exception {
        super.botDriver.type(USERNAMEPATH, username);
        super.botDriver.type(PASSWORDPATH, password);
        super.botDriver.click(BUTTONLOGINPATH);
        return new HomePage(this.driver);
    }

    @Override
    protected void load() {
        super.driver.get(PATH);
    }

    @Override
    protected void isLoaded() throws Error {
        super.driver.get(PATH);
        JavascriptExecutor js = (JavascriptExecutor) super.driver;
        if (js.executeScript("return document.readyState").toString().equals("complete")) {
            System.out.println("Overview page is loaded");
        }
    }


    // OLD PAGE OBJECT METHODS ***********

    /*

    private void sendUserName(String username) {
        super.wait(USERNAMEPATH).click();
        super.wait(USERNAMEPATH).sendKeys(username);
    }

    private void sendUserPassword(String password) {
        super.wait(PASSWORDPATH).click();
        super.wait(PASSWORDPATH).sendKeys(password);
    }

    private void clickLogin() {
        WebElement buttonLogin = super.wait(BUTTONLOGINPATH);
        if (buttonLogin.isDisplayed()) {
            buttonLogin.click();
        } else {
            System.out.println("Could not find button login");
        }
    }

     */

}
