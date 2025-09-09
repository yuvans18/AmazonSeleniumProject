package com.amazon.auto.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.*;

import java.time.Duration;

public class AmazonFlowTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
    }

    @Test
    public void amazonCompleteFlow() {
       
        driver.get("https://www.amazon.com/ap/signin?openid.pape.max_auth_age=900" +
                   "&openid.return_to=https%3A%2F%2Fwww.amazon.com%3F" +
                   "&openid.assoc_handle=usflex&openid.mode=checkid_setup" +
                   "&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0");
        System.out.println(" Opened Amazon Login Page");

 
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ap_email")));
        emailField.clear();
        emailField.sendKeys("Your amazon id");
        driver.findElement(By.id("continue")).click();

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ap_password")));
        passwordField.clear();
        passwordField.sendKeys("Your password");
        driver.findElement(By.id("signInSubmit")).click();
        System.out.println(" Login Completed");

       
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));
        searchBox.sendKeys("laptop");
        driver.findElement(By.id("nav-search-submit-button")).click();
        System.out.println(" Searched for laptop");

       
        WebElement filter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='4 Stars & Up']")));
        filter.click();
        System.out.println(" Applied filter");


        WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("div.s-main-slot div[data-component-type='s-search-result'] h2 a")));
        firstProduct.click();


        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

      
        WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button")));
        addToCartBtn.click();
        System.out.println(" Added product to cart");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println(" Browser closed");
        }
    }
}
