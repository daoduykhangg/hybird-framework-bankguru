package com.NopCommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObject.NopCommerce.HomePageObject;
import pageObject.NopCommerce.RegisterPageObject;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Level_03_Page_Object_01_Register {

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
        currentURL = driver.getCurrentUrl();

        firstName = "automation";
        lastName = "fc";
        email = getEmailRandom();
        invalidEmail = "abc@qwe";
        password = "123456";
        invalidPassword = "123";
        confirmPassword = "456789";
    }

    @Test
    public void Register_01_Empty_Data() {
        System.out.println("Register 01 - Step 01: Click to 'Register' Link");
        homePage = new HomePageObject(driver);
        homePage.clickToRegisterLink();

        System.out.println("Register 01 - Step 02: Click to 'Register' Button");
        registerPage = new RegisterPageObject(driver);
        registerPage.clickToRegisterButton();

        System.out.println("Register 01 - Step 03: Verify Error Message at 'Firstname' Textbox");
        Assert.assertEquals(registerPage.getErrorMessageAtFirstNameTextbox(), "First name is required.");

        System.out.println("Register 01 - Step 04: Verify Error Message at 'Lastname' Textbox");
        Assert.assertEquals(registerPage.getErrorMessageAtLastnameTextbox(), "Last name is required.");

        System.out.println("Register 01 - Step 05: Verify Error Message at 'Email' Textbox");
        Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Email is required.");

        System.out.println("Register 01 - Step 06: Verify Error Message at 'Password' Textbox");
        Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password is required.");

        System.out.println("Register 01 - Step 07: Verify Error Message at 'Confirm Password' Textbox");
        Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");
    }

    @Test
    public void Register_02_Invalid_Email() {
        System.out.println("Register 02 - Step 01: Click to 'Register' Link");
        homePage.clickToRegisterLink();
        registerPage = new RegisterPageObject(driver);

        System.out.println("Register 02 - Step 02: Enter to 'Firstname' textbox with value '" + firstName + "'");
        registerPage.enterToFirstNameTextbox(firstName);

        System.out.println("Register 02 - Step 03: Enter to 'Lastname' textbox with value '" + lastName + "'");
        registerPage.enterToLastNameTextbox(lastName);

        System.out.println("Register 02 - Step 04: Enter to 'Email' textbox with value '" + invalidEmail + "'");
        registerPage.enterToEmailTextbox(invalidEmail);

        System.out.println("Register 02 - Step 05: Enter to 'Password' textbox with value '" + password + "'");
        registerPage.enterToPasswordTextbox(password);

        System.out.println("Register 02 - Step 06: Enter to 'Confirm Password' textbox with value '" + password + "'");
        registerPage.enterToConfirmPasswordTextbox(password);

        System.out.println("Register 02 - Step 07: Click to 'Register' Button");
        registerPage.clickToRegisterButton();

        System.out.println("Register 02 - Step 08: Verify Validation Message Error");
        Assert.assertEquals(registerPage.getValidationMessageError(), "Wrong email");
    }

    @Test
    public void Register_03_Register_Successfully() {
        System.out.println("Register 03 - Step 01: Click to 'Register' Link");
        homePage.clickToRegisterLink();
        registerPage = new RegisterPageObject(driver);

        System.out.println("Register 03 - Step 02: Enter to 'Firstname' textbox with value '" + firstName + "'");
        registerPage.enterToFirstNameTextbox(firstName);

        System.out.println("Register 03 - Step 03: Enter to 'Lastname' textbox with value '" + lastName + "'");
        registerPage.enterToLastNameTextbox(lastName);

        System.out.println("Register 03 - Step 04: Enter to 'Email' textbox with value '" + email + "'");
        registerPage.enterToEmailTextbox(email);

        System.out.println("Register 03 - Step 05: Enter to 'Password' textbox with value '" + password + "'");
        registerPage.enterToPasswordTextbox(password);

        System.out.println("Register 03 - Step 06: Enter to 'Confirm Password' textbox with value '" + password + "'");
        registerPage.enterToConfirmPasswordTextbox(password);

        System.out.println("Register 03 - Step 07: Click to 'Register' Button");
        registerPage.clickToRegisterButton();

        System.out.println("Register 03 - Step 08: Verify Register Sucess Message");
        Assert.assertEquals(registerPage.getSuccessMessage(), "Your registration completed");

        System.out.println("Register 03 - Step 09: Click to Logout Link");
        registerPage.clickToLogoutLink();
        homePage = new HomePageObject(driver);
    }

    @Test
    public void Register_04_Existing_Email() {
        System.out.println("Register 04 - Step 01: Click to 'Register' Link");
        homePage.clickToRegisterLink();
        registerPage = new RegisterPageObject(driver);

        System.out.println("Register 04 - Step 02: Enter to 'Firstname' textbox with value '" + firstName + "'");
        registerPage.enterToFirstNameTextbox(firstName);

        System.out.println("Register 04 - Step 03: Enter to 'Lastname' textbox with value '" + lastName + "'");
        registerPage.enterToLastNameTextbox(lastName);

        System.out.println("Register 04 - Step 04: Enter to 'Email' textbox with value '" + email + "'");
        registerPage.enterToEmailTextbox(email);

        System.out.println("Register 04 - Step 05: Enter to 'Password' textbox with value '" + password + "'");
        registerPage.enterToPasswordTextbox(password);

        System.out.println("Register 04 - Step 06: Enter to 'Confirm Password' textbox with value '" + password + "'");
        registerPage.enterToConfirmPasswordTextbox(password);

        System.out.println("Register 04 - Step 07: Click to 'Register' Button");
        registerPage.clickToRegisterButton();

        System.out.println("Register 04 - Step 08: Verify Validation Message Error");
        Assert.assertEquals(registerPage.getValidationMessageError(), "The specified email already exists");
    }

    @Test
    public void Register_05_Password_Less_Than_6_Characters() {
        System.out.println("Register 05 - Step 01: Click to 'Register' Link");
        homePage.clickToRegisterLink();
        registerPage = new RegisterPageObject(driver);

        System.out.println("Register 05 - Step 02: Enter to 'Firstname' textbox with value '" + firstName + "'");
        registerPage.enterToFirstNameTextbox(firstName);

        System.out.println("Register 05 - Step 03: Enter to 'Lastname' textbox with value '" + lastName + "'");
        registerPage.enterToLastNameTextbox(lastName);

        System.out.println("Register 05 - Step 04: Enter to 'Email' textbox with value '" + email + "'");
        registerPage.enterToEmailTextbox(email);

        System.out.println("Register 05 - Step 05: Enter to 'Password' textbox with value '" + invalidPassword + "'");
        registerPage.enterToPasswordTextbox(invalidPassword);

        System.out.println("Register 05 - Step 06: Enter to 'Confirm Password' textbox with value '" + invalidPassword + "'");
        registerPage.enterToConfirmPasswordTextbox(invalidPassword);

        System.out.println("Register 05 - Step 07: Click to 'Register' Button");
        registerPage.clickToRegisterButton();

        System.out.println("Register 05 - Step 08: Verify Error Message at 'Password' Textbox");
        Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password must meet the following rules:\nmust have at least 6 characters");
    }

    @Test
    public void Register_06_Password_And_ConfirmPassword_Do_Not_Match() {
        System.out.println("Register 06 - Step 01: Click to 'Register' Link");
        homePage.clickToRegisterLink();
        registerPage = new RegisterPageObject(driver);

        System.out.println("Register 06 - Step 02: Enter to 'Firstname' textbox with value '" + firstName + "'");
        registerPage.enterToFirstNameTextbox(firstName);

        System.out.println("Register 06 - Step 03: Enter to 'Lastname' textbox with value '" + lastName + "'");
        registerPage.enterToLastNameTextbox(lastName);

        System.out.println("Register 06 - Step 04: Enter to 'Email' textbox with value '" + email + "'");
        registerPage.enterToEmailTextbox(email);

        System.out.println("Register 06 - Step 05: Enter to 'Password' textbox with value '" + password + "'");
        registerPage.enterToPasswordTextbox(password);

        System.out.println("Register 06 - Step 06: Enter to 'Confirm Password' textbox with value '" + confirmPassword + "'");
        registerPage.enterToConfirmPasswordTextbox(confirmPassword);

        System.out.println("Register 06 - Step 07: Click to 'Register' Button");
        registerPage.clickToRegisterButton();

        System.out.println("Register 01 - Step 08: Verify Error Message at 'Confirm Password' Textbox");
        Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "The password and confirmation password do not match.");
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
    private String currentURL, firstName, lastName, email, invalidEmail, password, invalidPassword, confirmPassword;
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
}
