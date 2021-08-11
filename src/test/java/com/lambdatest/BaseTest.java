package com.lambdatest;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

public class BaseTest {

    protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();
//    protected WebDriver driver;

    @BeforeSuite
    public void beforeSuite() {
        //Implement when needed
    }

    @AfterSuite
    public void afterSuite() {
        //Implement when needed
    }

    @BeforeTest
    @Parameters({"browser", "gridHubURL"})
    public void beforeTest(String browser, String gridHubURL, ITestContext testContext) {
        driver.set(BrowserFactory.createInstance(browser, gridHubURL));
        testContext.setAttribute("driver", getDriver());
    }

    @AfterTest
    public void afterTest() {
        getDriver().quit();
    }

    public WebDriver getDriver()
    {
        return driver.get();
    }
}
