package com.NopCommerce;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.NopCommerce.*;

import java.util.Random;

public class Level_06_Switch_Page extends BaseTest {
    @Parameters({"Browser", "Url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserDriver(browserName, url);
        firstName = "automation";
        lastName = "fc";
        email = getEmailRandom();
        invalidEmail = "abcqwe";
        password = "123456";
        invalidPassword = "123";

        System.out.println("Pre-Condition - Step 01: Click to 'Register' Link");
        homePage = PageGeneratorManager.getHomePage(driver);
        registerPage = homePage.clickToRegisterLink(driver);

        System.out.println("Pre-Condition - Step 02: Enter to 'Firstname' textbox with value '" + firstName + "'");
        registerPage.enterToFirstNameTextbox(firstName);

        System.out.println("Pre-Condition - Step 03: Enter to 'Lastname' textbox with value '" + lastName + "'");
        registerPage.enterToLastNameTextbox(lastName);

        System.out.println("Pre-Condition - Step 04: Enter to 'Email' textbox with value '" + email + "'");
        registerPage.enterToEmailTextbox(email);

        System.out.println("Pre-Condition - Step 05: Enter to 'Password' textbox with value '" + password + "'");
        registerPage.enterToPasswordTextbox(password);

        System.out.println("Pre-Condition - Step 06: Enter to 'Confirm Password' textbox with value '" + password + "'");
        registerPage.enterToConfirmPasswordTextbox(password);

        System.out.println("Pre-Condition - Step 07: Click to 'Register' Button");
        registerPage.clickToRegisterButton();

        System.out.println("Pre-Condition - Step 08: Verify Register Sucess Message");
        Assert.assertEquals(registerPage.getSuccessMessage(), "Your registration completed");

        System.out.println("Pre-Condition - Step 09: Click to Logout Link");
        homePage = registerPage.clickToLogoutLink(driver);
    }

    @Test
    public void Login_01_Existing_Email_With_correct_Password() {
        System.out.println("Login 06 - Step 01: Click to 'Login' Link");
        loginPage = homePage.clickToLoginLink(driver);

        System.out.println("Login 06 - Step 02: Enter to 'Email' Textbox with value '" + email + "'");
        loginPage.enterToEmailTextbox(email);

        System.out.println("Login 06 - Step 03: Enter to 'Password' Textbox with value '" + password + "'");
        loginPage.enterToPasswordTextbox(password);

        System.out.println("Login 06 - Step 04: Click to 'Login' Button");
        homePage = loginPage.clickToLoginButton(driver);

        System.out.println("Login 06 - Step 05: Verify 'My account' link is displayed");
        Assert.assertTrue(loginPage.isMyAccountLinkDisplayed());

        System.out.println("Login 06 - Step 06: Click to 'Login' Button");
        customerInforPage = homePage.clickToMyAccountLink(driver);
    }
    @Test
    public void Login_02_Switch_Page() {
        //Customer infor -> Addresses
        addressesPage = customerInforPage.openAddressesPage(driver);

        //Addresses -> Change password
        changePasswordPage = addressesPage.openChangePasswordPage(driver);

        //Change password -> My Product reviews
        myProductReviewsPage = changePasswordPage.openMyProductReviewsPage(driver);

        // My Product reviews -> Customer info
        customerInforPage = myProductReviewsPage.openCustomerInforPage(driver);

        //Customer info -> change password
        changePasswordPage = customerInforPage.openChangePasswordPage(driver);

        //change password -> Addresses
        addressesPage = changePasswordPage.openAddressesPage(driver);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }

    public String getEmailRandom() {
        Random ran = new Random();
        return "automation" + ran.nextInt(9999) + "@gmail.com";
    }

    private WebDriver driver;
    private String projectPath = System.getProperty("user.dir");
    private String firstName, lastName, email, invalidEmail, password, invalidPassword;
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private LoginPageObject loginPage;
    private CustomerInforPageObject customerInforPage;
    private AddressesPageObject addressesPage;
    private ChangePasswordPageObject changePasswordPage;
    private MyProductReviewsPageObject myProductReviewsPage;
}
