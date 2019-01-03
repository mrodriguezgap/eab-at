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
 * <p>
 * Used to manage Selenium's Drivers
 */
public class SeleniumBase {

    protected static WebDriver driver;

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

    public void quitDriver() {
        this.driver.quit();
    }

    private void initChrome() {
        StringBuilder chromeDriverPath = new StringBuilder();
        chromeDriverPath.append(System.getProperty("user.dir")).append
                ("/src/main/resources/drivers/chromedriver").toString();
        System.setProperty("webdriver.chrome.driver", chromeDriverPath.toString());
        driver = new ChromeDriver();
    }

    private void initFirefox() {
        driver = new FirefoxDriver();
    }

    private void initInternetExplorer() {
        driver = new InternetExplorerDriver();
    }

    private void initChromeCapabilities() {
        ChromeOptions options = new ChromeOptions();
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setBrowserName("Chrome");
        capabilities.setCapability("applicationCacheEnabled", false);
        options.addArguments("start-maximized");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        driver = new ChromeDriver(capabilities);
    }

    private void initFirefoxCapabilities() {

        FirefoxProfile fprofile = new FirefoxProfile();
        fprofile.setPreference("browser.startup.homepage", "https://auto3ss-staging7.gradesfirst.com/");
        //capabilities.setBrowserName("Firefox");

        driver = new FirefoxDriver(fprofile);
    }

    private void initInternetExplorerCapabilities() {
        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        capabilities.setBrowserName("InternetExplorer");

        driver = new InternetExplorerDriver(capabilities);
    }
}
