package pageUIs.jquery;

public class HomePageUI {
    public static final String PAGGING_BY_NUMBER = "//a[@class='qgrd-pagination-page-link' and text()='%s']";
    public static final String ACTIVED_PAGGING_BY_NUMBER = "//a[@class='qgrd-pagination-page-link active' and text()='%s']";
    public static final String TEXTBOX_BY_HEADERNAME = "//div[@class='qgrd-header-text' and text()='%s']/parent::div/following-sibling::input";
    public static final String ICON_BY_COUNTRY = "//td[@data-key='country' and text()='%s']/preceding-sibling::td[@class='qgrd-actions']/button[@class='qgrd-%s-row-btn']";
    public static final String ROW_VALUE = "//td[@data-key='total' and text()='%s']/preceding-sibling::td[@data-key='males' and text()='%s']/preceding-sibling::td[@data-key='country' and text()='%s']/preceding-sibling::td[@data-key='females' and text()='%s']";
    public static final String PLUS_BUTTON = "//button[@title='Append Row']";
    public static final String HEADER_NAME = "//td[@class='ui-widget-header'and text()='%s']/preceding-sibling::td";
    public static final String TEXTBOX_IN_ROW = "//tr[%s]/td[%s]/input";
}
