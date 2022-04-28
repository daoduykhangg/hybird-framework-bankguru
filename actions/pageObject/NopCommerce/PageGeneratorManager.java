package pageObject.NopCommerce;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
    private static HomePageObject homePage;
    private static LoginPageObject loginPage;
    private static RegisterPageObject registerPage;
    private static CustomerInforPageObject customerInforPage;
    private static AddressesPageObject addressesPage;
    private static ChangePasswordPageObject changePasswordPage;
    private static MyProductReviewsPageObject myProductReviewsPage;

    private PageGeneratorManager() {
    }

    public static HomePageObject getHomePage(WebDriver driver) {
        if (homePage == null) {
            return new HomePageObject(driver);
        }
        return homePage;
    }

    public static LoginPageObject getLoginPage(WebDriver driver) {
        if (loginPage == null) {
            return new LoginPageObject(driver);
        }
        return loginPage;
    }

    public static RegisterPageObject getRegisterPage(WebDriver driver) {
        if (registerPage == null) {
            return new RegisterPageObject(driver);
        }
        return registerPage;
    }

    public static CustomerInforPageObject getCustomerInforPage(WebDriver driver) {
        if (customerInforPage == null) {
            return new CustomerInforPageObject(driver);
        }
        return customerInforPage;
    }
    public static AddressesPageObject getAddressesPage(WebDriver driver) {
        if (addressesPage == null) {
            return new AddressesPageObject(driver);
        }
        return addressesPage;
    }
    public static ChangePasswordPageObject getChangePasswordPage(WebDriver driver) {
        if (changePasswordPage == null) {
            return new ChangePasswordPageObject(driver);
        }
        return changePasswordPage;
    }
    public static MyProductReviewsPageObject getMyProductReviewsPage(WebDriver driver) {
        if (myProductReviewsPage == null) {
            return new MyProductReviewsPageObject(driver);
        }
        return myProductReviewsPage;
    }

}
