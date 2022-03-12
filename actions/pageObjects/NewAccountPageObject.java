package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class NewAccountPageObject extends BasePage {
    private WebDriver driver;

    public NewAccountPageObject(WebDriver driver) {
        this.driver = driver;
    }
}
