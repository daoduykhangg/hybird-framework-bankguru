package com.bankguru.login;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.*;

public class Level_6_Switch_Page extends BaseTest {
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
    public void TC_03_Switch_Page() {
        // ManageHome Page -> New Customer Page
        newCustomerPage = manageHomePage.openNewCustomerPage(driver);

        //New Customer Page -> New Account Page
        newAccountPage = newCustomerPage.openNewAccountPage(driver);

        //New Account Page -> WithDrawal Page
        withDrawalPage = newAccountPage.openWithDrawalPage(driver);

        //WithDrawal Page -> ManageHome Page
        manageHomePage = withDrawalPage.openManagerHomePage(driver);

        //ManageHome Page -> Deposit Page
        depositPage = manageHomePage.openDepositPage(driver);

        // Deposit Page -> New Customer Page
        newCustomerPage = depositPage.openNewCustomerPage(driver);

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
