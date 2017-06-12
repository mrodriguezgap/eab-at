package com.gap.atpractice.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by auto on 15/05/17.
 */
public class HomePage extends PageBase {

    private static final String PATH = "https://auto3ss-staging7.gradesfirst.com/home";
    private static final By HEADERHOME = By.xpath(".//*/h1/div[contains(@class,'current-role')]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // Public elements

    public boolean checkHomePage() {
        WebElement header = super.wait(HEADERHOME);
        if (header.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }
    
    public String getPageHeader() {
        return super.wait(HEADERHOME).getText();
    }

    public void goToHomePage(String url) {
        super.goToPage(url);
    }

    public String getPageTitle() {
        return super.getPageTitle();
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
}
