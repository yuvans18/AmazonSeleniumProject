package com.amazon.auto.test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

public class AddToCartTest {

    @Test
    public void directAddToCartAndProceed() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();
        driver.get("https://www.amazon.in");

    
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));
        searchBox.sendKeys("laptop");
        driver.findElement(By.id("nav-search-submit-button")).click();
 
        
        
              List<WebElement> results = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("div.s-main-slot div[data-component-type='s-search-result']")
        ));
        results.get(0).findElement(By.cssSelector("h2 a")).click();

        
        for (String window : driver.getWindowHandles()) {
            driver.switchTo().window(window);
        }

       
        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button")));
        addToCart.click();

    
        wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-cart"))).click();

        
        WebElement proceedToBuy = wait.until(ExpectedConditions.elementToBeClickable(By.name("proceedToRetailCheckout")));
        proceedToBuy.click();


        System.out.println("Reached payment/login page. Title: " + driver.getTitle());
        
        
        
        

        driver.quit();
    }
}
