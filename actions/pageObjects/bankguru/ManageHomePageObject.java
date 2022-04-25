package pageObjects.bankguru;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.bankguru.ManageHomePageUI;

public class ManageHomePageObject extends BasePage {
    private WebDriver driver;

    public ManageHomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public String getTextHeading() {
        waitForElementVisible(driver, ManageHomePageUI.HEADING_TEXT);
        return getElementText(driver, ManageHomePageUI.HEADING_TEXT);
    }

    public void clickToLogoutLink() {
        waitForElementClickable(driver, ManageHomePageUI.LOGOUT_LINK);
        clickToElement(driver, ManageHomePageUI.LOGOUT_LINK);
    }

    public AccessPageObject acceptLogoutAlert(WebDriver driver) {
        waitForAlertPresence(driver);
        acceptAlert(driver);
        return PageGeneratorManager.getAccessPage(driver);
    }
}
