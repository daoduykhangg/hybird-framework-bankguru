package com.bankguru.login;

import commons.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Login_01_Register_And_login_Level_2 extends BasePage {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String currentURL, username, password;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        openPageUrl(driver, "https://demo.guru99.com/v4");
        currentURL = getCurrentUrl(driver);
    }

    @Test
    public void Login_01_Register() {
        clickToElement(driver, "//a[text()='here']");
        sendkeyToElement(driver, "//input[@name='emailid']", randomEmail());
        clickToElement(driver, "//input[@name='btnLogin']");

        username = getElementText(driver, "//td[text()='User ID :']/following-sibling::td");
        password = getElementText(driver, "//td[text()='Password :']/following-sibling::td");
    }

    @Test
    public void Login_02_Login() {
        openPageUrl(driver, currentURL);
        sendkeyToElement(driver, "//input[@name='uid']", username);
        sendkeyToElement(driver, "//input[@name='password']", password);
        clickToElement(driver, "//input[@name='btnLogin']");
        Assert.assertEquals(driver.findElement(By.xpath("//marquee[@class='heading3']")).getText(), "Welcome To Manager's Page of Guru99 Bank");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }

    public String randomEmail() {
        Random ran = new Random();
        return "automation" + ran.nextInt(999) + "@gmail.com";
    }
}
