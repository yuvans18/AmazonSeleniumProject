package com.amazon.auto.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FilterProductTest {

    @Test
    public void filterProduct() throws InterruptedException {
        // Setup browser
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://www.amazon.in");

        // Search for "headphones"
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("headphones");
        driver.findElement(By.id("nav-search-submit-button")).click();

        Thread.sleep(3000); // wait for results

        // Filter by Price (₹1,000 - ₹2,000)
        WebElement priceFilter = driver.findElement(By.xpath("//span[text()='₹1,000 – ₹2,000']"));
        priceFilter.click();

        Thread.sleep(3000); // wait for filter to apply

        // Filter by Customer Rating: 4 Stars & Up
        WebElement ratingFilter = driver.findElement(By.xpath("//span[text()='4 Stars & Up']"));
        ratingFilter.click();

        Thread.sleep(3000); // wait for filter to apply

        // Print the page title after applying filters
        System.out.println("Filtered Page Title: " + driver.getTitle());
        
        

        // Close the browser
        driver.quit();
    }
}
 