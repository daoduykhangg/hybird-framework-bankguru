package pageObjects.bankguru;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class NewCustomerPageObject extends BasePage {
    private WebDriver driver;

    public NewCustomerPageObject(WebDriver driver) {
        this.driver = driver;
    }
}
