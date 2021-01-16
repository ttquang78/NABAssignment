package com.openweather.pages;

import com.myutils.automation.tools.ReportUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderPage {
    private final WebDriver driver;

    public HeaderPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(id = "nav-search-form")
    private WebElement formSearch;

    @FindBy(id = "q")
    private WebElement txtSearch;

    public void searchCity(String searchStr) {
        ReportUtil.logStep("Search city with: " + searchStr);

        txtSearch.sendKeys(searchStr);
        formSearch.submit();
    }

}
