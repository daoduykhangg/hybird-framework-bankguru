package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.ManageHomePageUI;

public class ManageHomePageObject extends BasePage {
    WebDriver driver;

    public ManageHomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public String getTextHeading() {
        waitForElementVisible(driver, ManageHomePageUI.HEADING_TEXT);
        return getElementText(driver, ManageHomePageUI.HEADING_TEXT);
    }
}
