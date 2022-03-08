package com.bankguru.login;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.AccessPageObject;
import pageObjects.ManageHomePageObject;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Level_3_Register_And_Login_Page_Object {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String curentUrl, userId, password;

    @BeforeClass
    public void beforClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("https://demo.guru99.com/v4");
        curentUrl = driver.getCurrentUrl();
    }

    @Test
    public void TC_01_Generate_Access() {
        accessPage = new AccessPageObject(driver);
        accessPage.clickToHereLink();

        accessPage.enterToEmailIdTextbox(getEmailRandom());
        accessPage.clickToSubmitButton();

        userId = accessPage.getValueUserId();
        password = accessPage.getValuePassword();
    }

    @Test
    public void TC_02_Login() {
        driver.get(curentUrl);
        accessPage.entertoUserIdTextbox(userId);
        accessPage.entertoPasswordTextbox(password);
        accessPage.clickToLoginButton();

        manageHomePage = new ManageHomePageObject(driver);
        Assert.assertEquals(manageHomePage.getTextHeading(), "Welcome To Manager's Page of Guru99 Bank");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }

    public String getEmailRandom(){
        Random ran = new Random();
        return "automation" + ran.nextInt(9999)+"@gmail.com";
    }

    AccessPageObject accessPage;
    ManageHomePageObject manageHomePage;
}
