package AmazonShoppingCart;

import org.openqa.selenium.*;

import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.*;

import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class AmazonCartTests {

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
    public void amazonCompleteFlow() throws InterruptedException {
        driver.get("https://www.amazon.in/ap/signin?ie=UTF8&ie=UTF8&openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.in%2Fyour-account%3Fref_%3Dnav_ya_signin&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=inflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&fromAuthPrompt=1&switch_account=signin&ignoreAuthState=1&disableLoginPrepopulate=1&ref_=ap_sw_aa");

        System.out.println("Opened Amazon.in Login Page");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ap_email"))).sendKeys("YOUR AMAZON ID");
        driver.findElement(By.id("continue")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ap_password"))).sendKeys("YOUR PASSWORD");
        driver.findElement(By.id("signInSubmit")).click();

        System.out.println("Login Completed");
        System.out.println("shopping cart button is vissible");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));

        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.clear();
        searchBox.sendKeys("Boldfit Ice Pad");
        searchBox.sendKeys(Keys.ENTER);
        System.out.println("Searched for Boldfit Ice Pad");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@data-component-type='s-search-result'])[1]")));
        Thread.sleep(3000);
       
        driver.findElement(By.xpath("//button[text()='Add to cart']")).click();
        System.out.println("Icepad added to cart");
        Thread.sleep(3000);
        
        driver.findElement(By.xpath("//button[@aria-label='Increase quantity by one']")).click();
        System.out.println("increase cart count 2");
        Thread.sleep(3000);
        
        driver.findElement(By.xpath("//span[@data-a-selector='decrement-icon']")).click();
        System.out.println("cart count decrease ");
        Thread.sleep(3000);
        
        driver.findElement(By.xpath("//span[@data-a-selector='decrement-icon']")).click();
        System.out.println("cart count decrease to 1");
        Thread.sleep(3000);
        
        driver.findElement(By.xpath("//span[@data-a-selector='decrement-icon']")).click();
        System.out.println("cart count decreased to 0 empty");
        Thread.sleep(1000);
        
        
        driver.findElement(By.xpath("//a[contains(normalize-space(), 'Boldfit Reusable Hot & Cold')]")).click();
        System.out.println("open product detain page");
        Thread.sleep(7000);
   
        
        driver.findElement(By.id("add-to-cart-button")).click();
        System.out.println("added to cart again");
       Thread.sleep(3000);
    
     driver.findElement(By.xpath("//a[normalize-space()='Go to Cart']")).click();
     System.out.println("go to cart for checkout");
     Thread.sleep(3000);
    
    driver.findElement(By.xpath("//input[@data-feature-id='proceed-to-checkout-action']")).click();
    System.out.println("checkout page open");
    Thread.sleep(9000);
    
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println(" Browser closed");
        }
}
}
