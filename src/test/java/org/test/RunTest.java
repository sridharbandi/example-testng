package org.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
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
    ExtentReports report = new ExtentReports();
    ExtentTest test;

    @BeforeMethod
    public void preCondition(){
        ExtentSparkReporter spark = new ExtentSparkReporter("target/SparkReport.html");
        report.attachReporter(spark);
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
        test = report.createTest("Test Login");
        // Navigate to the Sauce Demo login page
        driver.get("https://www.saucedemo.com/");
        test.log(Status.INFO, "Launched the URL");
        // Enter username and password
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        test.log(Status.INFO, "Entered the Username");

        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        test.log(Status.INFO, "Entered the Password");

        // Click on the login button
        driver.findElement(By.id("login-button")).click();
        test.log(Status.INFO, "Submitted the login form");

        // Verify that login is successful
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL " + currentUrl);
        test.log(Status.INFO, "Navigated to the URL "+ currentUrl);
        assert currentUrl.equals("https://www.saucedemo.com/inventory.html") : "Login unsuccessful!";

    }

    @AfterMethod
    public void cleanUp(){
        // Close the browser after test execution
        if (driver != null) {
            driver.quit();
        }
        report.flush();
    }


}
