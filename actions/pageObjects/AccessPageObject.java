package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.AccessPageUI;

public class AccessPageObject extends BasePage {
    WebDriver driver;

    public AccessPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToHereLink() {
        waitForElementClickable(driver, AccessPageUI.HERE_LINK);
        clickToElement(driver, AccessPageUI.HERE_LINK);
    }

    public void enterToEmailIdTextbox(String email) {
        waitForElementVisible(driver, AccessPageUI.EMAIL_TEXTBOX);
        sendkeyToElement(driver, AccessPageUI.EMAIL_TEXTBOX, email);
    }

    public void clickToSubmitButton() {
        waitForElementClickable(driver, AccessPageUI.SUBMIT_BUTTON);
        clickToElement(driver, AccessPageUI.SUBMIT_BUTTON);
    }

    public String getValueUserId() {
        waitForElementVisible(driver, AccessPageUI.USERID_TEXT);
        return getElementText(driver, AccessPageUI.USERID_TEXT);
    }

    public String getValuePassword() {
        waitForElementVisible(driver, AccessPageUI.PASSWORD_TEXT);
        return getElementText(driver, AccessPageUI.PASSWORD_TEXT);
    }

    public void entertoUserIdTextbox(String userId) {
        waitForElementVisible(driver, AccessPageUI.USERID_TEXTBOX);
        sendkeyToElement(driver, AccessPageUI.USERID_TEXTBOX, userId);
    }

    public void entertoPasswordTextbox(String password) {
        waitForElementVisible(driver, AccessPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, AccessPageUI.PASSWORD_TEXTBOX, password);
    }

    public void clickToLoginButton() {
        waitForElementClickable(driver, AccessPageUI.LOGIN_BUTTON);
        clickToElement(driver, AccessPageUI.LOGIN_BUTTON);
    }
}
