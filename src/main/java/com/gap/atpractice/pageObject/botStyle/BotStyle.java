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
        wait(locator).clear();
        wait(locator).sendKeys(txt);
    }

    public void click(By locator) throws Exception {
        WebElement button = wait(locator);
        if (button.isDisplayed()) {
            button.click();
        } else {
            throw new Exception();
        }
    }

    // implicit wait
    public void waitForPageTitle(int timeToWaitSecs, final String title){

        (new WebDriverWait(this.driver, timeToWaitSecs)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith(title.toLowerCase());
            }
        });
    }

    // explicit wait
    private WebElement wait(By locator) {
        return (new WebDriverWait(this.driver, TIMEOUT))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

}
