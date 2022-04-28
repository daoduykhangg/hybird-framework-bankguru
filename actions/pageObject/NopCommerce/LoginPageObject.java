package pageObject.NopCommerce;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.NopCommerce.LoginPageUI;

public class LoginPageObject extends BasePage {
    private WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public HomePageObject clickToLoginButton(WebDriver driver) {
        waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getHomePage(driver);
    }

    public String getErrorMessageAtEmailTextbox() {
        waitForElementVisible(driver, LoginPageUI.MESSAGE_ERROR_AT_EMAIL_TEXTBOX);
        return getElementText(driver, LoginPageUI.MESSAGE_ERROR_AT_EMAIL_TEXTBOX);
    }

    public void enterToEmailTextbox(String email) {
        waitForElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
        sendkeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, email);
    }

    public String getValidationMessageError() {
        waitForElementVisible(driver, LoginPageUI.VALIDATION_MESSAGE_ERROR);
        return getElementText(driver, LoginPageUI.VALIDATION_MESSAGE_ERROR);
    }

    public void enterToPasswordTextbox(String password) {
        waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
    }

    public boolean isMyAccountLinkDisplayed() {
        waitForElementVisible(driver, LoginPageUI.MY_ACCOUNT_LINK);
        return isElementDisplayed(driver, LoginPageUI.MY_ACCOUNT_LINK);
    }
}
