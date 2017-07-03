package com.gap.atpractice.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

/**
 * Created by manuel on 15/05/17.
 */
public class HomePage extends PageBase {

    private static final String PATH = "/home";
    private static final By HEADERHOME = By.xpath(".//*/h1/div[contains(@class,'current-role')]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // Public elements

    /**
     * Page Object method. Checks if the page has been loaded correctly
     * @return A boolean stating if the page loaded correctly
     */
    public boolean checkHomePage() {
        WebElement header = super.botDriver.waitByLocator(HEADERHOME);
        if (header.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * BotStyle method. Gets the text inside Home Page header element
     * @return A string with text from the header
     */
    public String getPageHeader() {
        return super.botDriver.waitByLocator(HEADERHOME).getText();
    }

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
     * Implementation from Loadable Component. Loads the current page
     */
    @Override
    protected void load() {
        super.driver.get(createURL(PATH));
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
                "Error loading Home page...");
    }
}
