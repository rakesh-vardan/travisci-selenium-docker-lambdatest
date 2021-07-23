package com.lambdatest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserFactory {

    private static final Logger logger = LogManager.getLogger(BrowserFactory.class);

    private BrowserFactory() {
    }

    public static WebDriver createInstance(String browserName, String gridHubURL) {
        BrowserType browserType = BrowserType.valueOf(browserName);
        WebDriver driver = null;
        switch (browserType) {
            case LOCAL_FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case GRID_CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                try {
                    driver = new RemoteWebDriver(new URL(gridHubURL), chromeOptions);
                } catch (MalformedURLException e) {
                    logger.error("Grid Hub URL is malformed: {}", e.getMessage());
                }
                break;
            case GRID_FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                try {
                    driver = new RemoteWebDriver(new URL(gridHubURL), firefoxOptions);
                } catch (MalformedURLException e) {
                    logger.error("Grid Hub URL is malformed: {}", e.getMessage());
                }
                break;
            case GRID_LAMBDATEST_CHROME:
                try {
                    ChromeOptions options = new ChromeOptions();
                    driver = new RemoteWebDriver(new URL("https://" + getUsername() + ":" + getAccessKey() + "@hub.lambdatest.com/wd/hub"), options);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            case LOCAL_CHROME:
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;

        }
        return driver;
    }

    private static String getAccessKey() {
        return System.getenv("LT_ACCESS_KEY") != null ? System.getenv("LT_ACCESS_KEY") : "xr3lIeM4tq4KdDKowUlrkkJftz7ixT2kFtI1OT33T4QS0suGhH";
    }

    private static String getUsername() {
        return System.getenv("LT_USERNAME") != null ? System.getenv("LT_USERNAME") : "rakeshbudugu";
    }

}
