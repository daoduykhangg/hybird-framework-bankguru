package com.bankguru.login;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.bankguru.AccessPageObject;
import pageObjects.bankguru.ManageHomePageObject;
import pageObjects.bankguru.PageGeneratorManager;

public class Level_5_Register_And_Login_Page_Generator_Manager extends BaseTest {
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
        accessPage = PageGeneratorManager.getAccessPage(driver);
        System.out.println(accessPage.hashCode());
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

        manageHomePage = accessPage.clickToLoginButton();
        Assert.assertEquals(manageHomePage.getTextHeading(), "Welcome To Manager's Page of Guru99 Bank");
    }

    @Test
    public void TC_03_Logout() {
        manageHomePage.clickToLogoutLink();
        accessPage = manageHomePage.acceptLogoutAlert(driver);
        System.out.println(accessPage.hashCode());
        Assert.assertEquals(accessPage.getTextStepToGenerateAccess(), "Steps To Generate Access");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }

    AccessPageObject accessPage;
    ManageHomePageObject manageHomePage;
}
