package commons;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    private WebDriver driver;
    private String projectPath = System.getProperty("user.dir");

    private enum BrowserName {
        CHROME, FIREFOX, EDGE_CHROMIUM;
    }

    protected WebDriver getBrowserDriver(String browserName, String urlApp) {
        BrowserName browser = BrowserName.valueOf(browserName.toUpperCase());
        if (browser == BrowserName.CHROME) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser == BrowserName.FIREFOX) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser == BrowserName.EDGE_CHROMIUM) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            throw new RuntimeException("Please enter correct browser name!");
        }
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(urlApp);
        return driver;
    }

    protected String getEmailRandom() {
        Random ran = new Random();
        return "automation" + ran.nextInt(9999) + "@gmail.com";
    }
}
