package com.gap.atpractice.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by manuel on 06/04/17.
 * Used to manage Selenium's Drivers
 */
public class SeleniumBase {

    protected static WebDriver driver;

    /**
     * Sets up the web driver and browser for current testing session
     * @param browserName String representing a browser's name
     * @param useCapabilities Boolean value, to enable or disable specific browser's capabilities
     * @return
     */
    public WebDriver setup(String browserName, boolean useCapabilities) {
        switch (browserName) {
            case "FireFox":
                if (!useCapabilities)
                    initFirefox();
                else
                    initFirefoxCapabilities();
                break;

            case "Chrome":
                if (!useCapabilities)
                    initChrome();
                else
                    initChromeCapabilities();
                break;

            case "IntenetExplorer":
                if (!useCapabilities)
                    initInternetExplorer();
                else
                    initInternetExplorerCapabilities();
                break;

            default:
                System.out.println("Please define a browser Name");
        }

        return driver;
    }

    /**
     * Quits current wer driver and closes browser
     */
    public void quitDriver() {
        this.driver.quit();
    }

    /**
     * Initializes a Google Chrome Browser without capabilities
     */
    private void initChrome() {
        driver = new ChromeDriver();
    }

    /**
     * Initializes a Firefox Browser without capabilities
     */
    private void initFirefox() {
        driver = new FirefoxDriver();
    }

    /**
     * Initializes an Internet Explorer Browser without capabilities
     */
    private void initInternetExplorer() {
        driver = new InternetExplorerDriver();
    }

    /**
     * Initializes a Google Chrome Browser with capabilities
     */
    private void initChromeCapabilities() {
        ChromeOptions options = new ChromeOptions();
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setBrowserName("Chrome");
        capabilities.setCapability("applicationCacheEnabled", false);
        options.addArguments("start-maximized");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        driver = new ChromeDriver(capabilities);
    }

    /**
     * Initializes a Firefox Browser with capabilities
     */
    private void initFirefoxCapabilities() {

        FirefoxProfile fprofile = new FirefoxProfile();
        fprofile.setPreference("browser.startup.homepage", "https://auto3ss-staging7.gradesfirst.com/");
        //capabilities.setBrowserName("Firefox");

        driver = new FirefoxDriver(fprofile);
    }

    /**
     * Initializes an Internet Explorer Browser with capabilities
     */
    private void initInternetExplorerCapabilities() {
        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        capabilities.setBrowserName("InternetExplorer");

        driver = new InternetExplorerDriver(capabilities);
    }
}
