package org.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class RunTest {

    WebDriver driver;
    @BeforeMethod
    public void preCondition(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void testOne(){
        // Navigate to the Sauce Demo login page
        driver.get("https://www.saucedemo.com/");

        // Enter username and password
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        // Click on the login button
        driver.findElement(By.id("login-button")).click();

        // Verify that login is successful
        String currentUrl = driver.getCurrentUrl();
        assert currentUrl.equals("https://www.saucedemo.com/inventory.html") : "Login unsuccessful!";

    }

    @AfterMethod
    public void cleanUp(){
        // Close the browser after test execution
        if (driver != null) {
            driver.quit();
        }
    }


}
