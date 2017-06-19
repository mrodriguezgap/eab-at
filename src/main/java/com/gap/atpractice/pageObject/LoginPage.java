package com.gap.atpractice.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

/**
 * Created by manuel on 15/05/17.
 */
public class LoginPage extends PageBase {

    // Page Factory Paths
    // WebElements
    @FindBy(xpath = "//*/input[contains(@class,'button')]")
    private WebElement buttonLogin;

    @FindBy(xpath = "//input[@id='login']")
    private WebElement userNameField;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordField;

    // BotStyle paths
    private static final By BUTTONLOGINPATH = By.xpath("//*/input[contains(@class,'button')]");
    private static final By USERNAMEPATH = By.xpath("//input[@id='login']");
    private static final By PASSWORDPATH = By.xpath("//input[@id='password']");
    private static final String PATH = "/session/new";

    public LoginPage(WebDriver driver) {
        super(driver);
        super.initElements(driver);
    }

    // Public methods *******************

    public void goToLoginPage(String url) {
        super.goToPage(url);
    }

    public String getPageTitle() {
        return super.driver.getTitle();
    }

    public HomePage login(String username, String password) {
        sendUserName(username);
        sendUserPassword(password);
        clickLogin();
        return new HomePage(this.driver);
    }

    // Page Factory methods *************

    private void sendUserName(String username) {
        super.botDriver.waitByWebElement(userNameField).click();
        super.botDriver.waitByWebElement(userNameField).sendKeys(username);
    }

    private void sendUserPassword(String password) {
        super.botDriver.waitByWebElement(passwordField).click();
        super.botDriver.waitByWebElement(passwordField).sendKeys(password);
    }

    private void clickLogin() {
        if (buttonLogin.isDisplayed()) {
            buttonLogin.click();
        } else {
            System.out.println("Could not find button login");
        }
    }

    // BotStyle *************************

    public HomePage botLogin(String username, String password) throws Exception {
        super.botDriver.type(USERNAMEPATH, username);
        super.botDriver.type(PASSWORDPATH, password);
        super.botDriver.click(BUTTONLOGINPATH);
        return new HomePage(this.driver);
    }

    // Loadable Component ****************

    @Override
    protected void load() {
        super.driver.get(super.createURL(PATH));
    }

    @Override
    protected void isLoaded() throws Error {
        super.driver.get(super.createURL(PATH));
        JavascriptExecutor js = (JavascriptExecutor) super.driver;
        Assert.assertEquals(js.executeScript("return document.readyState").toString(), "complete",
                "Error loading Login page...");
    }

}
