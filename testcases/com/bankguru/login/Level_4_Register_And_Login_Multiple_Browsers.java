package com.bankguru.login;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.AccessPageObject;
import pageObjects.ManageHomePageObject;

import java.util.Random;

public class Level_4_Register_And_Login_Multiple_Browsers extends BaseTest {
    WebDriver driver;
    String curentUrl, userId, password;

    @Parameters({"Browser", "Url"})
    @BeforeClass
    public void beforClass(String browserName, String urlApp) {
        driver = getBrowserDriver(browserName, urlApp);
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

    public String getEmailRandom() {
        Random ran = new Random();
        return "automation" + ran.nextInt(9999) + "@gmail.com";
    }

    AccessPageObject accessPage;
    ManageHomePageObject manageHomePage;
}
