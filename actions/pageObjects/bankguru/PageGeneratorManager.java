package pageObjects.bankguru;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
    private static AccessPageObject accessPage;
    private static ManageHomePageObject manageHomePage;
    private static NewCustomerPageObject newCustomerPage;
    private static NewAccountPageObject newAccountPage;
    private static WithDrawalPageObject withDrawalPage;
    private static DepositPageObject depositPage;

    private PageGeneratorManager() {
    }

    public static AccessPageObject getAccessPage(WebDriver driver) {
        if (accessPage == null) {
            accessPage = new AccessPageObject(driver);
        }
        return accessPage;
    }

    public static ManageHomePageObject getManageHomePage(WebDriver driver) {
        if (manageHomePage == null) {
            manageHomePage = new ManageHomePageObject(driver);
        }
        return manageHomePage;
    }

    public static NewCustomerPageObject getNewCustomerPage(WebDriver driver) {
        if (newCustomerPage == null) {
            newCustomerPage = new NewCustomerPageObject(driver);
        }
        return newCustomerPage;
    }

    public static NewAccountPageObject getNewAccountPage(WebDriver driver) {
        if (newAccountPage == null) {
            newAccountPage = new NewAccountPageObject(driver);
        }
        return newAccountPage;
    }

    public static WithDrawalPageObject getWithDrawalPage(WebDriver driver) {
        if (withDrawalPage == null) {
            withDrawalPage = new WithDrawalPageObject(driver);
        }
        return withDrawalPage;
    }

    public static DepositPageObject getDepositPage(WebDriver driver) {
        if (depositPage == null) {
            depositPage = new DepositPageObject(driver);
        }
        return depositPage;
    }
}
