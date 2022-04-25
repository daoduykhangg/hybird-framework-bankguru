package pageObjects.jquery;

import commons.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pageUIs.jquery.HomePageUI;

public class HomePageObject extends BasePage {
    private WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void openPageByNumber(String number) {
        waitForElementClickable(driver, HomePageUI.PAGGING_BY_NUMBER, number);
        clickToElement(driver, HomePageUI.PAGGING_BY_NUMBER, number);
    }

    public boolean isPageActivedDisplayed(String number) {
        waitForElementVisible(driver, HomePageUI.ACTIVED_PAGGING_BY_NUMBER, number);
        return isElementDisplayed(driver, HomePageUI.ACTIVED_PAGGING_BY_NUMBER, number);
    }

    public void inputToHeaderTextboxByName(String headerName, String value) {
        waitForElementVisible(driver, HomePageUI.TEXTBOX_BY_HEADERNAME, headerName);
        sendkeyToElement(driver, HomePageUI.TEXTBOX_BY_HEADERNAME, value, headerName);
        pressKeyBoardToElement(driver, HomePageUI.TEXTBOX_BY_HEADERNAME, Keys.ENTER, headerName);
    }

    public void clickToIconByContry(String countryName, String iconName) {
        waitForElementClickable(driver, HomePageUI.ICON_BY_COUNTRY, countryName, iconName);
        clickToElement(driver, HomePageUI.ICON_BY_COUNTRY, countryName, iconName);
    }

    public boolean isRowValueDisplayed(String female, String country, String male, String total) {
        waitForElementVisible(driver, HomePageUI.ROW_VALUE, total, male, country, female);
        return isElementDisplayed(driver, HomePageUI.ROW_VALUE, total, male, country, female);
    }

    public void clickToPlusButton() {
        waitForElementClickable(driver, HomePageUI.PLUS_BUTTON);
        clickToElement(driver, HomePageUI.PLUS_BUTTON);
    }

    public void inputToTextBoxByRowNumber(String columnName, String rowIndex, String value) {
        int columnIndex = getElementSize(driver, HomePageUI.HEADER_NAME, columnName) + 1;
        System.out.println(columnIndex);
        waitForElementVisible(driver, HomePageUI.TEXTBOX_IN_ROW, rowIndex, String.valueOf(columnIndex));
        sendkeyToElement(driver, HomePageUI.TEXTBOX_IN_ROW, value, rowIndex, String.valueOf(columnIndex));
    }
}
