package com.amazon.auto.test;

public class LoginTest extends BaseTest {

    public static void main(String[] args) {
        LoginTest test = new LoginTest();
        test.setUp();

        driver.get("https://www.amazon.in");
        String title = driver.getTitle();
        System.out.println("Page Title: " + title);

        test.tearDown();
        
        
    }
}
