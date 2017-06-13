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

    protected final String URL = "https://auto3ss-staging7.gradesfirst.com";
    protected final long TIMEOUT = 30;
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

    // TODO Wait should go on Bot
    public WebElement wait(By locator) {
        return (new WebDriverWait(this.driver, TIMEOUT))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement wait(WebElement element){
        return (new WebDriverWait(this.driver, TIMEOUT))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public String createURL(String url) {
        return String.format("%s%s", URL, url);
    }

    public void goToPage(String url) {
        this.driver.get(url);
    }

    public abstract String getPageTitle();

    // Loadable Component ****************

    protected abstract void load();

    protected abstract void isLoaded() throws Error;
}
