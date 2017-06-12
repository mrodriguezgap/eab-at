package com.gap.atpractice.pageObject.botStyle;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by auto on 18/05/17.
 */
public class BotStyle {

    private WebDriver driver;
    private static final long TIMEOUT = 10;

    public BotStyle(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public void type(WebElement element, String txt){
        element.clear();
        element.sendKeys(txt);
    }

    public void type(By locator, String txt){
        waitByLocator(locator).clear();
        waitByLocator(locator).sendKeys(txt);
    }

    public void click(By locator) throws Exception {
        WebElement button = waitByLocator(locator);
        if (button.isDisplayed()) {
            button.click();
        } else {
            throw new Exception();
        }
    }

    /**
     * Implicit wait, that expects for a String to appear on page body
     * @param timeToWaitSecs Wait timeout in seconds
     * @param textToCompare Text string taken from page element
     * @param criteria Text string to compare to
     */
    public void waitByTextString(int timeToWaitSecs, final String textToCompare ,final String criteria){

        (new WebDriverWait(this.driver, timeToWaitSecs)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return textToCompare.toLowerCase().startsWith(criteria.toLowerCase());
            }
        });
    }

    /**
     * Explicit wait, that expects for an element to appear on page body
     * @param locator By locator taken from page
     * @return A WebElement taken found using By locator
     */
    private WebElement waitByLocator(By locator) {
        return (new WebDriverWait(this.driver, TIMEOUT))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

}
