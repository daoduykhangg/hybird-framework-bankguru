package pageObjects.bankguru;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class DepositPageObject extends BasePage {
    private WebDriver driver;

    public DepositPageObject(WebDriver driver) {
        this.driver = driver;
    }
}
