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

    /**
     * Page Object method. Moves to desired URL
     * @param url String representing destination URL
     */
    public void moveToAnotherPage(String url) {
        super.goToPage(url);
    }

    /**
     * Page Object method. Gets the current page title
     * @return String representing current page title
     */
    public String getPageTitle() {
        return super.driver.getTitle();
    }

    /**
     * BotStyle method. Performs a login and moves to Home Page
     * @param username String representing user name
     * @param password String representing password
     * @return HomePage object representing a Home Page
     */
    public HomePage login(String username, String password) {
        sendUserName(username);
        sendUserPassword(password);
        clickLogin();
        return new HomePage(this.driver);
    }

    // Page Factory methods *************

    /**
     * BotStyle method. Types the user name on corresponding field
     * @param username String representing the user name
     */
    private void sendUserName(String username) {
        super.botDriver.waitByWebElement(userNameField).click();
        super.botDriver.waitByWebElement(userNameField).sendKeys(username);
    }

    /**
     * BotStyle method. Types the password on corresponding field
     * @param password String representing the password
     */
    private void sendUserPassword(String password) {
        super.botDriver.waitByWebElement(passwordField).click();
        super.botDriver.waitByWebElement(passwordField).sendKeys(password);
    }

    /**
     * Page Object method. Performs a click on Login button
     */
    private void clickLogin() {
        if (buttonLogin.isDisplayed()) {
            buttonLogin.click();
        } else {
            System.out.println("Could not find button login");
        }
    }

    // BotStyle *************************

    /**
     * BotStyle method. Performs a login and moves to Home Page
     * Makes use of Page Factory locators
     * @param username String representing user name
     * @param password String representing password
     * @return HomePage object representing a Home Page
     */
    public HomePage botLogin(String username, String password) throws Exception {
        super.botDriver.type(USERNAMEPATH, username);
        super.botDriver.type(PASSWORDPATH, password);
        super.botDriver.click(BUTTONLOGINPATH);
        return new HomePage(this.driver);
    }

    // Loadable Component ****************

    /**
     * Implementation from Loadable Component. Loads the current page
     */
    @Override
    protected void load() {
        super.driver.get(super.createURL(PATH));
    }

    /**
     * Implementation from Loadable Component. Verifies if the current page has been loaded
     * @throws Error
     */
    @Override
    protected void isLoaded() throws Error {
        super.driver.get(super.createURL(PATH));
        JavascriptExecutor js = (JavascriptExecutor) super.driver;
        Assert.assertEquals(js.executeScript("return document.readyState").toString(), "complete",
                "Error loading Login page...");
    }

}
