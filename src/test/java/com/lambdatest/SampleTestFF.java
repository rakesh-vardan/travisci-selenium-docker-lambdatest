package com.lambdatest;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.URL;

public class SampleTestFF {

    public RemoteWebDriver driver = null;

    String username = System.getenv("LT_USERNAME") != null ? System.getenv("LT_USERNAME") : "test";
    String accessKey = System.getenv("LT_ACCESS_KEY") != null ? System.getenv("LT_ACCESS_KEY") : "test";

    @Test
    public void testScript() {
        try {

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platform", "Windows 10");
            capabilities.setCapability("browserName", "Firefox");
            capabilities.setCapability("version", "89.0"); // If this cap isn't specified, it will just get the any available one
            capabilities.setCapability("resolution","1024x768");
            capabilities.setCapability("build", "New Test case");
            capabilities.setCapability("name", "Brand new test case");
            capabilities.setCapability("network", true); // To enable network logs
            capabilities.setCapability("visual", true); // To enable step by step screenshot
            capabilities.setCapability("video", true); // To enable video recording
            capabilities.setCapability("console", true); // To capture console logs

//            FirefoxOptions options = new FirefoxOptions();
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub"), capabilities);
            driver.get("https://lambdatest.github.io/sample-todo-app/");
            driver.findElement(By.name("li1")).click();
            driver.findElement(By.name("li2")).click();
            driver.findElement(By.id("sampletodotext")).clear();
            driver.findElement(By.id("sampletodotext")).sendKeys("Yey, Let's add it to list");
            driver.findElement(By.id("addbutton")).click();
            driver.quit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
