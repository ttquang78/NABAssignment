package com.openweather.pages;

import com.myutils.automation.selenium.SelBase;
import com.myutils.automation.selenium.SelCmd;
import com.myutils.automation.tools.ReportUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CityDetailPage {
    private final WebDriver driver;

    public CityDetailPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(css = "div.daily-container")
    private WebElement div8days;

    @FindBy(css = "div.daily-detail-container")
    private WebElement divDailyDetail;

    public boolean is8dayForecastDisplay() {
        return SelBase.isElementVisibility(driver, div8days);
    }

    public boolean isDailyDetailPanelDisplay() {
        return SelBase.isElementVisibility(driver, divDailyDetail);
    }

    public boolean isForecastOfDayDisplay(int day) {
        boolean isTrue = false;

        if (day <= 8) {
            String xpath = generateXPathOfForecast(day);
            isTrue = SelBase.isVisibilityOfElementLocated(driver, By.xpath(xpath));
        }

        return isTrue;
    }

    public void expandForecastOfDate(int day) {
        if (isForecastOfDayDisplay(day)) {
            ReportUtil.logStep("Expand date # " + day + " from current date");

            String xpath = generateXPathOfForecast(day);
            SelCmd.performActionOnBy(driver, SelCmd.SelCommand.CLICK, By.xpath(xpath), null);
        }
    }

    private String generateXPathOfForecast(int day) {
        Date objDate = DateUtils.addDays(new Date(), day);
        String strDateFormat = "E, MMM d";
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
        System.out.println(objSDF.format(objDate));

        return "//div[contains(@class,'daily-container')]//li[span[.='" + objSDF.format(objDate) + "']]";
    }

}
