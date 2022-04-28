package pageObject.NopCommerce;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class MyProductReviewsPageObject extends BasePage {
    WebDriver driver;

    public MyProductReviewsPageObject(WebDriver driver) {
        this.driver = driver;
    }
}
