package com.lambdatest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SeleniumTests extends BaseTest {

    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        driver = getDriver();
        driver.get("https://lambdatest.github.io/sample-todo-app/");
    }

    @Test(priority = 1)
    public void verifyHeader1() {
        System.out.println("Test Case One in " + getClass().getSimpleName()
                + " with Thread Id:- " + Thread.currentThread().getId());
        String headerText = driver.findElement(By.xpath("//h2")).getText();
        assertThat(headerText).isEqualTo("LambdaTest Sample App");
    }

    @Test(priority = 2)
    public void verifyHeader2() {
        System.out.println("Test Case Two in " + getClass().getSimpleName()
                + " with Thread Id:- " + Thread.currentThread().getId());
        String text = driver.findElement(By.xpath("//h2/following-sibling::div/span")).getText();
        assertThat(text).isEqualTo("5 of 5 remaining");
    }

    @Test(priority = 3)
    public void verifyFirstElementBehavior() {
        System.out.println("Test Case Three in " + getClass().getSimpleName()
                + " with Thread Id:- " + Thread.currentThread().getId());
        WebElement firstElementText = driver.findElement(By.xpath("//input[@name='li1']/following-sibling::span[@class='done-false']"));
        assertThat(firstElementText.isDisplayed()).isTrue();
        assertThat(firstElementText.getText()).isEqualTo("First Item");

        assertThat(driver.findElement(By.name("li1")).isSelected()).isFalse();
        driver.findElement(By.name("li1")).click();
        assertThat(driver.findElement(By.name("li1")).isSelected()).isTrue();

        WebElement firstItemPostClick = driver.findElement(By.xpath("//input[@name='li1']/following-sibling::span[@class='done-true']"));
        assertThat(firstItemPostClick.isDisplayed()).isTrue();

        String text = driver.findElement(By.xpath("//h2/following-sibling::div/span")).getText();
        assertThat(text).isEqualTo("4 of 5 remaining");

    }

    @Test(priority = 4)
    public void verifySecondElementBehavior() {
        System.out.println("Test Case Four in " + getClass().getSimpleName()
                + " with Thread Id:- " + Thread.currentThread().getId());
        WebElement secondElementText = driver.findElement(By.xpath("//input[@name='li2']/following-sibling::span[@class='done-false']"));
        assertThat(secondElementText.isDisplayed()).isTrue();
        assertThat(secondElementText.getText()).isEqualTo("Second Item");

        assertThat(driver.findElement(By.name("li2")).isSelected()).isFalse();
        driver.findElement(By.name("li2")).click();
        assertThat(driver.findElement(By.name("li2")).isSelected()).isTrue();

        WebElement secondItemPostClick = driver.findElement(By.xpath("//input[@name='li2']/following-sibling::span[@class='done-true']"));
        assertThat(secondItemPostClick.isDisplayed()).isTrue();

        String text = driver.findElement(By.xpath("//h2/following-sibling::div/span")).getText();
        assertThat(text).isEqualTo("3 of 5 remaining");
    }

    @Test(priority = 5)
    public void verifyAddButtonBehavior() {
        System.out.println("Test Case Five in " + getClass().getSimpleName()
                + " with Thread Id:- " + Thread.currentThread().getId());
        driver.findElement(By.id("sampletodotext")).clear();
        driver.findElement(By.id("sampletodotext")).sendKeys("Yey, Let's add it to list");
        driver.findElement(By.id("addbutton")).click();
        WebElement element = driver.findElement(By.xpath("//input[@name='li6']/following-sibling::span[@class='done-false']"));
        assertThat(element.isDisplayed()).isTrue();
        assertThat(element.getText()).isEqualTo("Yey, Let's add it to list");
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
