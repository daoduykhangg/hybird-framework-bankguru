package pageObjects.jquery;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
    private static HomePageObject HomePage;

    private PageGeneratorManager() {
    }

    public static HomePageObject getManageHomePage(WebDriver driver) {
        if (HomePage == null) {
            HomePage = new HomePageObject(driver);
        }
        return HomePage;
    }
}
