package com.bankguru.login;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.bankguru.*;
import pageUIs.bankguru.BasePageUI;

public class Level_7_Dynamic_Locator extends BaseTest {
    private WebDriver driver;
    String curentUrl, userId, password;

    @Parameters({"Browser", "Url"})
    @BeforeClass
    public void beforeClass(String browserName, String urlApp) {
        driver = getBrowserDriver(browserName, urlApp);
        curentUrl = driver.getCurrentUrl();
    }

    @Test
    public void TC_01_Generate_Access() {
        accessPage = PageGeneratorManager.getAccessPage(driver);
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
    public void TC_03_Switch_Page() {
        // ManageHome Page -> New Customer Page
        manageHomePage.openPageByPageName(driver, BasePageUI.DYNAMIC_PAGE_LINK, "New Customer");
        newCustomerPage = PageGeneratorManager.getNewCustomerPage(driver);

        //New Customer Page -> New Account Page
        newCustomerPage.openPageByPageName(driver, BasePageUI.DYNAMIC_PAGE_LINK, "New Account");
        newAccountPage = PageGeneratorManager.getNewAccountPage(driver);

        //New Account Page -> WithDrawal Page
        newAccountPage.openPageByPageName(driver, BasePageUI.DYNAMIC_PAGE_LINK, "Withdrawal");
        withDrawalPage = PageGeneratorManager.getWithDrawalPage(driver);

        //WithDrawal Page -> ManageHome Page
        withDrawalPage.openPageByPageName(driver, BasePageUI.DYNAMIC_PAGE_LINK, "Manager");
        manageHomePage = PageGeneratorManager.getManageHomePage(driver);

        //ManageHome Page -> Deposit Page
        manageHomePage.openPageByPageName(driver, BasePageUI.DYNAMIC_PAGE_LINK, "Deposit");
        depositPage = PageGeneratorManager.getDepositPage(driver);

        // Deposit Page -> New Customer Page
        depositPage.openPageByPageName(driver, BasePageUI.DYNAMIC_PAGE_LINK, "New Customer");
        newCustomerPage = PageGeneratorManager.getNewCustomerPage(driver);

    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }

    private AccessPageObject accessPage;
    private ManageHomePageObject manageHomePage;
    private NewCustomerPageObject newCustomerPage;
    private NewAccountPageObject newAccountPage;
    private WithDrawalPageObject withDrawalPage;
    private DepositPageObject depositPage;
}
