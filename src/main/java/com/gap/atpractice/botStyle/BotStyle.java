package com.gap.atpractice.botStyle;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by manuel on 18/05/17.
 */
public class BotStyle {

    private WebDriver driver;
    private Actions action;
    private static final long TIMEOUT = 10;

    public BotStyle(WebDriver driver) {
        this.driver = driver;
        this.action = new Actions(driver);
    }

    /**
     * Return an instance of the global Web Driver
     * @return Selenium Web Driver instance
     */
    public WebDriver getDriver() {
        return this.driver;
    }

    /**
     * Type any text on a given web element
     * @param element Webelement on page
     * @param text String representing the text to enter on the web element
     */
    public void type(WebElement element, String text){
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Type any text on a given web element
     * @param locator By locator for the element
     * @param text String representing the text to enter on the web element
     */
    public void type(By locator, String text){
        waitByLocator(locator).clear();
        waitByLocator(locator).sendKeys(text);
    }

    /**
     * Perfrom a click on any Web element
     * @param locator By locator for the element
     * @throws Exception If the element is not found or displayed
     */
    public void click(By locator) throws Exception {
        WebElement element = waitByLocator(locator);
        if (element.isDisplayed()) {
            element.click();
        } else {
            throw new Exception();
        }
    }

    // Actions methods *******************

    public void performDoubleclick(By locator){
        this.action.moveToElement(driver.findElement(locator));
        action.doubleClick().perform();
    }

    public void performHoverElement(By locator){
        this.action.moveToElement(driver.findElement(locator)).perform();
    }

    public void performHoverMenuItems(By menu, By menuItems){
        this.action.moveToElement(driver.findElement(menu)).perform();
        List<WebElement> items = driver.findElements(menuItems);
        for (int i = 0; i<items.size(); i++){
            this.action.moveToElement(items.get(i)).perform();
        }
    }

    public void performHoverOneMenuItem(By menu, By menuItems, int index){
        this.action.moveToElement(driver.findElement(menu)).perform();
        List<WebElement> items = driver.findElements(menuItems);
        for (int i = 0; i<index; i++){
            this.action.moveToElement(items.get(i)).perform();
        }
    }

    // Wait methods *******************

    /**
     * Implicit wait. Waits for a predetermined amount of seconds (10 seconds)
     */
    public void waitBySeconds()
    {
        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
    }

    /**
     * Implicit wait. Waits for a given amount of seconds
     * @param timeInSeconds Amount of seconds to wait
     */
    public void waitBySeconds(long timeInSeconds){
        driver.manage().timeouts().implicitlyWait(timeInSeconds, TimeUnit.SECONDS);
    }

    /**
     * Explicit wait, that expects for a String to appear on page body
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
     * @return A WebElement found using By locator
     */
    public WebElement waitByLocator(By locator) {
        return (new WebDriverWait(this.driver, TIMEOUT))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Explicit wait, that expects for a Web Element to appear on page body
     * @param element Web Element representing page element
     * @return The WebElement found after wait
     */
    public WebElement waitByWebElement(WebElement element){
        return (new WebDriverWait(this.driver, TIMEOUT))
                .until(ExpectedConditions.visibilityOf(element));
    }

}
