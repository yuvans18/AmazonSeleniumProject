package com.amazon.auto.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SearchProductTest extends BaseTest {

    WebDriverWait wait;

    @BeforeMethod
    public void launchBrowser() {
        setUp();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test
    public void searchProduct() {
     
        driver.get("https://www.amazon.in");

        wait.until(ExpectedConditions.titleContains("Amazon"));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("twotabsearchtextbox")));

        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("laptop");

        WebElement searchButton = driver.findElement(By.id("nav-search-submit-button"));
        searchButton.click();

        wait.until(ExpectedConditions.titleContains("laptop"));


        System.out.println("Search Result Page Title: " + driver.getTitle());
    }

    @AfterMethod 
    public void closeBrowser() {
        tearDown();
    }
}
