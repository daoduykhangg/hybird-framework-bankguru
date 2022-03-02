package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public class BasePage {
    public void openPageUrl(WebDriver driver, String url) {
        driver.get(url);
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getCurrentUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public Alert waitForAlertPresence(WebDriver driver) {
        explicitWait = new WebDriverWait(driver, timeout);
        return explicitWait.until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert(WebDriver driver) {
        alert = waitForAlertPresence(driver);
        alert.accept();
    }

    public void cancelAlert(WebDriver driver) {
        alert = waitForAlertPresence(driver);
        alert.dismiss();
    }

    public void sendkeyToAlert(WebDriver driver, String value) {
        alert = waitForAlertPresence(driver);
        alert.sendKeys(value);
    }

    public String getAlertText(WebDriver driver) {
        alert = waitForAlertPresence(driver);
        return alert.getText();
    }

    public void switchWindowByID(WebDriver driver, String parentId) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(parentId)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }

    public void switchWindowByTitle(WebDriver driver, String expectedPageTitle) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            driver.switchTo().window(window);
            sleepInSecond(1);
            String actualPageTitle = driver.getTitle();
            if (actualPageTitle.equals(expectedPageTitle)) {
                break;
            }
        }
    }

    public void closeAllWindowsWithoutParentPage(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(parentID)) {
                driver.switchTo().window(window);
                sleepInSecond(1);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
        sleepInSecond(1);
    }

    public By getByXpath(String locator) {
        return By.xpath(locator);
    }

    public WebElement getElement(WebDriver driver, String locator) {
        return driver.findElement(getByXpath(locator));
    }

    public List<WebElement> getElements(WebDriver driver, String locator) {
        return driver.findElements(getByXpath(locator));
    }

    public int getElementSize(WebDriver driver, String locator) {
        return getElements(driver, locator).size();
    }

    public void clickToElement(WebDriver driver, String locator) {
        getElement(driver, locator).click();
    }

    public void sendkeyToElement(WebDriver driver, String locator, String value) {
        getElement(driver, locator).clear();
        getElement(driver, locator).sendKeys(value);
    }

    public void selectItemInDropdown(WebDriver driver, String locator, String itemText) {
        select = new Select(getElement(driver, locator));
        select.selectByVisibleText(itemText);
    }

    public String getSelectedItemDropdown(WebDriver driver, String locator) {
        select = new Select(getElement(driver, locator));
        return select.getFirstSelectedOption().getText();
    }

    public Boolean isDropdownMultiple(WebDriver driver, String locator) {
        select = new Select(getElement(driver, locator));
        return select.isMultiple();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
        getElement(driver, parentLocator).click();
        sleepInSecond(1);
        explicitWait = new WebDriverWait(driver, timeout);
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childItemLocator)));
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedItem)) {
                jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
                sleepInSecond(1);
                item.click();
                sleepInSecond(1);
                break;
            }
        }
    }

    public String getElementAttribute(WebDriver driver, String locator, String attributeName) {
        return getElement(driver, locator).getAttribute(attributeName);
    }

    public String getElementText(WebDriver driver, String locator) {
        return getElement(driver, locator).getText();
    }

    public String getCssValue(WebDriver driver, String locator, String cssValue) {
        return getElement(driver, locator).getCssValue(cssValue);
    }

    public String getHexaColorRgbaColor(String rgbaValue) {
        return Color.fromString(rgbaValue).asHex();
    }

    public void sleepInSecond(long second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private WebDriverWait explicitWait;
    private long timeout = 30;
    private Alert alert;
    private Select select;
    private JavascriptExecutor jsExecutor;
}
