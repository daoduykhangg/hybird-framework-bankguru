package com.jquery;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.jquery.HomePageObject;

public class Level_09_Handle_Data_Table extends BaseTest {

    @Parameters({"Browser", "Url"})
    @BeforeClass
    public void beforeClass(String browsername, String urlApp) {
        driver = getBrowserDriver(browsername, urlApp);
        homePage = new HomePageObject(driver);
    }

    public void Table_01_Pagging() {
        homePage.openPageByNumber("5");
        Assert.assertTrue(homePage.isPageActivedDisplayed("5"));
        homePage.sleepInSecond(2);

        homePage.openPageByNumber("10");
        Assert.assertTrue(homePage.isPageActivedDisplayed("10"));
        homePage.sleepInSecond(2);

        homePage.openPageByNumber("15");
        Assert.assertTrue(homePage.isPageActivedDisplayed("15"));
        homePage.sleepInSecond(2);

        homePage.openPageByNumber("22");
        Assert.assertTrue(homePage.isPageActivedDisplayed("22"));
        homePage.sleepInSecond(2);
    }

    public void Table_02_Actions() {
        homePage.inputToHeaderTextboxByName("Females", "384187");
        homePage.sleepInSecond(2);
        homePage.refreshPage(driver);

        homePage.inputToHeaderTextboxByName("Country", "Algeria");
        homePage.sleepInSecond(2);
        homePage.refreshPage(driver);

        homePage.inputToHeaderTextboxByName("Males", "756");
        homePage.sleepInSecond(2);
        homePage.refreshPage(driver);

        homePage.inputToHeaderTextboxByName("Total", "1567904");
        homePage.sleepInSecond(2);
        homePage.refreshPage(driver);

    }

    public void Table_03_Click_Icon() {
        homePage.clickToIconByContry("Afghanistan", "remove");
        homePage.sleepInSecond(2);
        homePage.refreshPage(driver);

        homePage.clickToIconByContry("Arab Rep of Egypt", "edit");
        homePage.sleepInSecond(2);
        homePage.refreshPage(driver);

        homePage.clickToIconByContry("Aruba", "remove");
        homePage.sleepInSecond(2);
        homePage.refreshPage(driver);

        homePage.clickToIconByContry("Argentina", "edit");
        homePage.sleepInSecond(2);
        homePage.refreshPage(driver);

    }

    public void Table_04_Verify_Row_Value() {
        homePage.inputToHeaderTextboxByName("Females", "764956");
        Assert.assertTrue(homePage.isRowValueDisplayed("764956", "Arab Rep of Egypt", "802948", "1567904"));
        homePage.sleepInSecond(2);
        homePage.refreshPage(driver);

        homePage.inputToHeaderTextboxByName("Country", "Argentina");
        Assert.assertTrue(homePage.isRowValueDisplayed("338282", "Argentina", "349238", "687522"));
        homePage.sleepInSecond(2);
        homePage.refreshPage(driver);

        homePage.inputToHeaderTextboxByName("Males", "756");
        Assert.assertTrue(homePage.isRowValueDisplayed("750", "Aruba", "756", "1504"));
        homePage.sleepInSecond(2);
        homePage.refreshPage(driver);

    }

    @Test
    public void Table_05_Input_To_Row_Textbox() {
        homePage.clickToPlusButton();
        homePage.sleepInSecond(1);
        homePage.clickToPlusButton();
        homePage.sleepInSecond(1);
        homePage.inputToTextBoxByRowNumber("Year", "1", "1996");
        homePage.sleepInSecond(1);
        homePage.inputToTextBoxByRowNumber("Artist", "2", "Khang");
        homePage.sleepInSecond(1);
        homePage.inputToTextBoxByRowNumber("Album", "3", "March");
        homePage.sleepInSecond(1);

    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }

    private WebDriver driver;
    private HomePageObject homePage;
}
