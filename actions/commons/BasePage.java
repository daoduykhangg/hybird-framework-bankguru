package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.NopCommerce.AddressesPageObject;
import pageObject.NopCommerce.ChangePasswordPageObject;
import pageObject.NopCommerce.CustomerInforPageObject;
import pageObject.NopCommerce.MyProductReviewsPageObject;
import pageObjects.bankguru.*;
import pageUIs.bankguru.BasePageUI;

import java.util.List;
import java.util.Set;

public class BasePage {
    protected void openPageUrl(WebDriver driver, String url) {
        driver.get(url);
    }

    protected String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    protected String getCurrentUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    protected String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    protected void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    protected void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    protected Alert waitForAlertPresence(WebDriver driver) {
        explicitWait = new WebDriverWait(driver, timeout);
        return explicitWait.until(ExpectedConditions.alertIsPresent());
    }

    protected void acceptAlert(WebDriver driver) {
        alert = waitForAlertPresence(driver);
        alert.accept();
    }

    protected void cancelAlert(WebDriver driver) {
        alert = waitForAlertPresence(driver);
        alert.dismiss();
    }

    protected void sendkeyToAlert(WebDriver driver, String value) {
        alert = waitForAlertPresence(driver);
        alert.sendKeys(value);
    }

    protected String getAlertText(WebDriver driver) {
        alert = waitForAlertPresence(driver);
        return alert.getText();
    }

    protected void switchWindowByID(WebDriver driver, String parentId) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(parentId)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }

    protected void switchWindowByTitle(WebDriver driver, String expectedPageTitle) {
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

    protected void closeAllWindowsWithoutParentPage(WebDriver driver, String parentID) {
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

    protected By getByXpath(String locator) {
        return By.xpath(locator);
    }

    private String getDynamicLocator(String locator, String... params) {
        return String.format(locator, (Object[]) params);
    }

    protected WebElement getElement(WebDriver driver, String locator) {
        return driver.findElement(getByXpath(locator));
    }

    protected List<WebElement> getElements(WebDriver driver, String locator) {
        return driver.findElements(getByXpath(locator));
    }

    protected int getElementSize(WebDriver driver, String locator) {
        return getElements(driver, locator).size();
    }

    protected int getElementSize(WebDriver driver, String locator, String... params) {
        return getElements(driver, getDynamicLocator(locator, params)).size();
    }

    protected void clickToElement(WebDriver driver, String locator) {
        getElement(driver, locator).click();
    }

    protected void clickToElement(WebDriver driver, String locator, String... params) {
        getElement(driver, getDynamicLocator(locator, params)).click();
    }

    protected void sendkeyToElement(WebDriver driver, String locator, String value) {
        getElement(driver, locator).clear();
        getElement(driver, locator).sendKeys(value);
    }

    protected void sendkeyToElement(WebDriver driver, String locator, String value, String... params) {
        getElement(driver, getDynamicLocator(locator, params)).clear();
        getElement(driver, getDynamicLocator(locator, params)).sendKeys(value);
    }

    protected void selectItemInDropdown(WebDriver driver, String locator, String itemText) {
        select = new Select(getElement(driver, locator));
        select.selectByVisibleText(itemText);
    }

    protected String getSelectedItemDropdown(WebDriver driver, String locator) {
        select = new Select(getElement(driver, locator));
        return select.getFirstSelectedOption().getText();
    }

    protected Boolean isDropdownMultiple(WebDriver driver, String locator) {
        select = new Select(getElement(driver, locator));
        return select.isMultiple();
    }

    protected void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
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

    protected String getElementAttribute(WebDriver driver, String locator, String attributeName) {
        return getElement(driver, locator).getAttribute(attributeName);
    }

    protected String getElementText(WebDriver driver, String locator) {
        return getElement(driver, locator).getText();
    }

    protected String getElementText(WebDriver driver, String locator, String... params) {
        return getElement(driver, getDynamicLocator(locator, params)).getText();
    }

    protected String getCssValue(WebDriver driver, String locator, String cssValue) {
        return getElement(driver, locator).getCssValue(cssValue);
    }

    protected String getHexaColorRgbaColor(String rgbaValue) {
        return Color.fromString(rgbaValue).asHex();
    }

    public void sleepInSecond(long second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void checkTheCheckboxorRadio(WebDriver driver, String locator) {
        if (!isElementSelected(driver, locator)) {
            getElement(driver, locator).click();
        }
    }

    protected void checkTheCheckboxorRadio(WebDriver driver, String locator, String... params) {
        if (!isElementSelected(driver, getDynamicLocator(locator, params))) {
            getElement(driver, getDynamicLocator(locator, params)).click();
        }
    }

    protected void uncheckTheCheckbox(WebDriver driver, String locator) {
        if (isElementSelected(driver, locator)) {
            getElement(driver, locator).click();
        }
    }

    protected void uncheckTheCheckbox(WebDriver driver, String locator, String... params) {
        if (isElementSelected(driver, getDynamicLocator(locator, params))) {
            getElement(driver, getDynamicLocator(locator, params)).click();
        }
    }

    protected boolean isElementDisplayed(WebDriver driver, String locator) {
        return getElement(driver, locator).isDisplayed();
    }

    protected boolean isElementDisplayed(WebDriver driver, String locator, String... params) {
        return getElement(driver, getDynamicLocator(locator, params)).isDisplayed();
    }

    protected boolean isElementEnabled(WebDriver driver, String locator) {
        return getElement(driver, locator).isEnabled();
    }

    protected boolean isElementSelected(WebDriver driver, String locator) {
        return getElement(driver, locator).isSelected();
    }

    protected void switchToIframeByElement(WebDriver driver, String locator) {
        driver.switchTo().frame(getElement(driver, locator));
    }

    protected void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    protected void doubleClickToElement(WebDriver driver, String locator) {
        action = new Actions(driver);
        action.doubleClick(getElement(driver, locator)).perform();
    }

    protected void hoverMouseToElement(WebDriver driver, String locator) {
        action = new Actions(driver);
        action.moveToElement(getElement(driver, locator)).perform();
    }

    protected void rightClickToElement(WebDriver driver, String locator) {
        action = new Actions(driver);
        action.contextClick(getElement(driver, locator)).perform();
    }

    protected void dragAndDrop(WebDriver driver, String sourceLocator, String targetLocator) {
        action = new Actions(driver);
        action.dragAndDrop(getElement(driver, sourceLocator), getElement(driver, targetLocator)).perform();
    }

    protected void pressKeyBoardToElement(WebDriver driver, String locator, Keys key) {
        action = new Actions(driver);
        action.sendKeys(getElement(driver, locator), key).perform();
    }

    protected void pressKeyBoardToElement(WebDriver driver, String locator, Keys key, String... params) {
        action = new Actions(driver);
        action.sendKeys(getElement(driver, getDynamicLocator(locator, params)), key).perform();
    }

    protected Object executeForBrowser(WebDriver driver, String javaScript) {
        jsExecutor = (JavascriptExecutor) driver;
        return jsExecutor.executeScript(javaScript);
    }

    protected String getInnerText(WebDriver driver) {
        jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    protected boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
        jsExecutor = (JavascriptExecutor) driver;
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
        return textActual.equals(textExpected);
    }

    protected void scrollToBottomPage(WebDriver driver) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    protected void navigateToUrlByJS(WebDriver driver, String url) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.location = '" + url + "'");
    }

    protected void highlightElement(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    protected void clickToElementByJS(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getElement(driver, locator));
    }

    protected void scrollToElement(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
    }

    protected void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, locator));
    }

    protected void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, locator));
    }

    protected boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
        explicitWait = new WebDriverWait(driver, timeout);
        jsExecutor = (JavascriptExecutor) driver;

        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    protected String getElementValidationMessage(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
    }

    protected boolean isImageLoaded(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(driver, locator));
        if (status) {
            return true;
        } else {
            return false;
        }
    }

    protected void waitForElementVisible(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, timeout);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
    }

    protected void waitForElementVisible(WebDriver driver, String locator, String... params) {
        explicitWait = new WebDriverWait(driver, timeout);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(getDynamicLocator(locator, params))));
    }

    protected void waitForAllElementVisible(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, timeout);
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(locator)));
    }

    protected void waitForElementClickable(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, timeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
    }

    protected void waitForElementInvisible(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, timeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
    }

    protected void waitForElementClickable(WebDriver driver, String locator, String... params) {
        explicitWait = new WebDriverWait(driver, timeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(getDynamicLocator(locator, params))));
    }

    protected void waitForElementInvisible(WebDriver driver, String locator, String... params) {
        explicitWait = new WebDriverWait(driver, timeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(getDynamicLocator(locator, params))));
    }

    /* Common methods Nopcommerce */
    public CustomerInforPageObject openCustomerInforPage(WebDriver driver) {
        waitForElementClickable(driver, pageUIs.NopCommerce.BasePageUI.CUSTOMER_INFOR_LINK);
        clickToElement(driver, pageUIs.NopCommerce.BasePageUI.CUSTOMER_INFOR_LINK);
        return pageObject.NopCommerce.PageGeneratorManager.getCustomerInforPage(driver);
    }

    public AddressesPageObject openAddressesPage(WebDriver driver) {
        waitForElementClickable(driver, pageUIs.NopCommerce.BasePageUI.ADDRESSES_LINK);
        clickToElement(driver, pageUIs.NopCommerce.BasePageUI.ADDRESSES_LINK);
        return pageObject.NopCommerce.PageGeneratorManager.getAddressesPage(driver);
    }

    public ChangePasswordPageObject openChangePasswordPage(WebDriver driver) {
        waitForElementClickable(driver, pageUIs.NopCommerce.BasePageUI.CHANGE_PASSWORD_LINK);
        clickToElement(driver, pageUIs.NopCommerce.BasePageUI.CHANGE_PASSWORD_LINK);
        return pageObject.NopCommerce.PageGeneratorManager.getChangePasswordPage(driver);
    }

    public MyProductReviewsPageObject openMyProductReviewsPage(WebDriver driver) {
        waitForElementClickable(driver, pageUIs.NopCommerce.BasePageUI.MY_PRODUCT_REVIEW_LINK);
        clickToElement(driver, pageUIs.NopCommerce.BasePageUI.MY_PRODUCT_REVIEW_LINK);
        return pageObject.NopCommerce.PageGeneratorManager.getMyProductReviewsPage(driver);
    }
    /*Common methods*/

    public ManageHomePageObject openManagerHomePage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.MANAGER_HOMEPAGE_LINK);
        clickToElement(driver, BasePageUI.MANAGER_HOMEPAGE_LINK);
        return PageGeneratorManager.getManageHomePage(driver);
    }

    public NewCustomerPageObject openNewCustomerPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.NEW_CUSTOMER_PAGE_LINK);
        clickToElement(driver, BasePageUI.NEW_CUSTOMER_PAGE_LINK);
        return PageGeneratorManager.getNewCustomerPage(driver);
    }

    public NewAccountPageObject openNewAccountPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.NEW_ACCOUNT_PAGE_LINK);
        clickToElement(driver, BasePageUI.NEW_ACCOUNT_PAGE_LINK);
        return PageGeneratorManager.getNewAccountPage(driver);
    }

    public WithDrawalPageObject openWithDrawalPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.WITHDRAWAL_PAGE_LINK);
        clickToElement(driver, BasePageUI.WITHDRAWAL_PAGE_LINK);
        return PageGeneratorManager.getWithDrawalPage(driver);
    }

    public DepositPageObject openDepositPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.DEPOSIT_PAGE_LINK);
        clickToElement(driver, BasePageUI.DEPOSIT_PAGE_LINK);
        return PageGeneratorManager.getDepositPage(driver);
    }

    public void openPageByPageName(WebDriver driver, String locator, String pageName) {
        waitForElementVisible(driver, locator, pageName);
        clickToElement(driver, locator, pageName);
    }


    private Alert alert;
    private Select select;
    private Actions action;
    private long timeout = 30;
    private WebDriverWait explicitWait;
    private JavascriptExecutor jsExecutor;
}
