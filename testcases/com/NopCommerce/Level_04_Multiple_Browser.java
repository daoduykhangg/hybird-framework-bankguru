package com.NopCommerce;

import commons.BasePage;
import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.NopCommerce.HomePageObject;
import pageObject.NopCommerce.LoginPageObject;
import pageObject.NopCommerce.RegisterPageObject;

import java.util.Base64;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Level_04_Multiple_Browser extends BaseTest {
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
        homePage = new HomePageObject(driver);
//        homePage.clickToRegisterLink();

        System.out.println("Pre-Condition - Step 02: Click to 'Register' Link");
//        homePage.clickToRegisterLink();
        registerPage = new RegisterPageObject(driver);

        System.out.println("Pre-Condition - Step 03: Enter to 'Firstname' textbox with value '" + firstName + "'");
        registerPage.enterToFirstNameTextbox(firstName);

        System.out.println("Pre-Condition - Step 04: Enter to 'Lastname' textbox with value '" + lastName + "'");
        registerPage.enterToLastNameTextbox(lastName);

        System.out.println("Pre-Condition - Step 05: Enter to 'Email' textbox with value '" + email + "'");
        registerPage.enterToEmailTextbox(email);

        System.out.println("Pre-Condition - Step 06: Enter to 'Password' textbox with value '" + password + "'");
        registerPage.enterToPasswordTextbox(password);

        System.out.println("Pre-Condition - Step 07: Enter to 'Confirm Password' textbox with value '" + password + "'");
        registerPage.enterToConfirmPasswordTextbox(password);

        System.out.println("Pre-Condition - Step 08: Click to 'Register' Button");
        registerPage.clickToRegisterButton();

        System.out.println("Pre-Condition - Step 09: Verify Register Sucess Message");
        Assert.assertEquals(registerPage.getSuccessMessage(), "Your registration completed");

        System.out.println("Pre-Condition - Step 10: Click to Logout Link");
//        registerPage.clickToLogoutLink();
        homePage = new HomePageObject(driver);
    }

    @Test
    public void Login_01_Empty_Data() {
        System.out.println("Login 01 - Step 01: Click to 'Login' Link");
//        homePage.clickToLoginLink();

        System.out.println("Login 01 - Step 02: Click to 'Login' Button");
//        loginPage = new LoginPageObject(driver);
//        loginPage.clickToLoginButton();

        System.out.println("Login 01 - Step 02: Verrify Error message at 'Email' Textbox");
        Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");
    }

    @Test
    public void Login_02_Invalid_Email() {
        System.out.println("Login 02 - Step 01: Click to 'Login' Link");
//        homePage.clickToLoginLink();

        System.out.println("Login 02 - Step 02: Enter to 'Email' Textbox with value '" + invalidEmail + "'");
        loginPage = new LoginPageObject(driver);
        loginPage.enterToEmailTextbox(invalidEmail);

        System.out.println("Login 02 - Step 03: Click to 'Login' Button");
//        loginPage.clickToLoginButton();

        System.out.println("Login 02 - Step 04: Verrify Error message at 'Email' Textbox");
        Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Wrong email");
    }

    @Test
    public void Login_03_Email_Not_Found() {
        System.out.println("Login 03 - Step 01: Click to 'Login' Link");
//        homePage.clickToLoginLink();

        System.out.println("Login 03 - Step 02: Enter to 'Email' Textbox with value 'abc@poi.com'");
        loginPage = new LoginPageObject(driver);
        loginPage.enterToEmailTextbox("abc@poi.com");

        System.out.println("Login 03 - Step 03: Enter to 'Password' Textbox with value '" + password + "'");
        loginPage.enterToPasswordTextbox(password);

        System.out.println("Login 03 - Step 04: Click to 'Login' Button");
//        loginPage.clickToLoginButton();

        System.out.println("Login 03 - Step 05: Verrify Validation message error");
        Assert.assertEquals(loginPage.getValidationMessageError(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
    }

    @Test
    public void Login_04_Existing_Email_With_Empty_Password() {
        System.out.println("Login 04 - Step 01: Click to 'Login' Link");
//        homePage.clickToLoginLink();

        System.out.println("Login 04 - Step 02: Enter to 'Email' Textbox with value '" + email + "'");
        loginPage = new LoginPageObject(driver);
        loginPage.enterToEmailTextbox(email);

        System.out.println("Login 04 - Step 03: Enter to 'Password' Textbox with value ''");
        loginPage.enterToPasswordTextbox("");

        System.out.println("Login 04 - Step 04: Click to 'Login' Button");
//        loginPage.clickToLoginButton();

        System.out.println("Login 04 - Step 05: Verrify Validation message error");
        Assert.assertEquals(loginPage.getValidationMessageError(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
    }

    @Test
    public void Login_05_Existing_Email_With_Incorrect_Password() {
        System.out.println("Login 05 - Step 01: Click to 'Login' Link");
//        homePage.clickToLoginLink();

        System.out.println("Login 05 - Step 02: Enter to 'Email' Textbox with value '" + email + "'");
        loginPage = new LoginPageObject(driver);
        loginPage.enterToEmailTextbox(email);

        System.out.println("Login 05 - Step 03: Enter to 'Password' Textbox with value '" + invalidPassword + "'");
        loginPage.enterToPasswordTextbox(invalidPassword);

        System.out.println("Login 05 - Step 04: Click to 'Login' Button");
//        loginPage.clickToLoginButton();

        System.out.println("Login 05 - Step 05: Verrify Validation message error");
        Assert.assertEquals(loginPage.getValidationMessageError(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
    }

    @Test
    public void Register_06_Existing_Email_With_correct_Password() {
        System.out.println("Login 06 - Step 01: Click to 'Login' Link");
//        homePage.clickToLoginLink();

        System.out.println("Login 06 - Step 02: Enter to 'Email' Textbox with value '" + email + "'");
        loginPage = new LoginPageObject(driver);
        loginPage.enterToEmailTextbox(email);

        System.out.println("Login 06 - Step 03: Enter to 'Password' Textbox with value '" + password + "'");
        loginPage.enterToPasswordTextbox(password);

        System.out.println("Login 06 - Step 04: Click to 'Login' Button");
//        loginPage.clickToLoginButton();

        System.out.println("Login 06 - Step 05: Verify 'My account' link is displayed");
        Assert.assertTrue(loginPage.isMyAccountLinkDisplayed());
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
}
