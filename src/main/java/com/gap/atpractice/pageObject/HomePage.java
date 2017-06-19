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

    public boolean checkHomePage() {
        WebElement header = super.botDriver.waitByLocator(HEADERHOME);
        if (header.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public String getPageHeader() {
        return super.botDriver.waitByLocator(HEADERHOME).getText();
    }

    public void goToHomePage(String url) {
        super.goToPage(url);
    }

    public String getPageTitle() {
        return super.driver.getTitle();
    }

    @Override
    protected void load() {
        super.driver.get(createURL(PATH));
    }

    @Override

    protected void isLoaded() throws Error {
        super.driver.get(super.createURL(PATH));
        JavascriptExecutor js = (JavascriptExecutor) super.driver;
        Assert.assertEquals(js.executeScript("return document.readyState").toString(), "complete",
                "Error loading Home page...");
    }
}
