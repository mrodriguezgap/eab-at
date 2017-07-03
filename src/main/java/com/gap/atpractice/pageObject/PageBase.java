package com.gap.atpractice.pageObject;

import com.gap.atpractice.botStyle.BotStyle;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by manuel on 25/05/17.
 */
public abstract class PageBase extends LoadableComponent {

    private final String URL = "https://auto3ss-staging7.gradesfirst.com";
    private final long TIMEOUT = 30;
    protected WebDriver driver;
    protected BotStyle botDriver;

    public PageBase(WebDriver driver) {
        this.driver = driver;
        this.botDriver = new BotStyle(driver);
    }

    /**
     * Page factory method to initialize all page fields
     * @param driver WebDriver instance
     */
    protected void initElements(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    /**
     * Method used on loadable component, to build a URL to all child pages
     * @param url
     * @return
     */
    public String createURL(String url) {
        return String.format("%s%s", URL, url);
    }

    /**
     * Method used to move to a desired page
     * @param url String representing URL of the page
     */
    public void goToPage(String url) {
        this.driver.get(url);
    }

    /**
     * Abstract method. Gets the current page title
     * @return A String representing page title
     */
    public abstract String getPageTitle();

    // Loadable Component ****************

    /**
     * Abstract method from Loadable Component. Loads the current page
     */
    protected abstract void load();

    /**
     * Abstract method from Loadable Component. Verifies if the current page has been loaded
     * @throws Error
     */
    protected abstract void isLoaded() throws Error;
}
