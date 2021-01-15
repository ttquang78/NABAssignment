package com.openweather.pages;

import com.myutils.automation.selenium.SelBase;
import com.myutils.automation.selenium.SelCmd;
import com.myutils.automation.tools.ReportUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultPage {
    private final WebDriver driver;

    String xpathForecastList = "//div[@id='forecast_list_ul']";

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(css = "div.alert-warning")
    private WebElement lblNotFound;

    @FindBy(xpath = "//div[@id='forecast_list_ul']/table")
    private WebElement tableResult;

    public boolean isNotFoundDisplay()
    {
        return SelBase.isElementVisibility(driver, lblNotFound);
    }

    public int countResult()
    {
        int count = 0;

        if (SelBase.isElementVisibility(driver, tableResult))
        {
            count = tableResult.findElements(By.tagName("tr")).size();
        }

        return count;
    }

    public boolean isCorrectResult(String expectStr)
    {
        boolean isCorrect = false;

        if (expectStr.equalsIgnoreCase("not found"))
        {
            isCorrect = isNotFoundDisplay();
        }
        else
        {
            if (countResult() > 0)
            {
                isCorrect = true;
            }
        }

        return isCorrect;
    }

    public void clickSearchResultNumber(int index)
    {
        ReportUtil.logStep("Select result #" + index);

        String xpath = xpathForecastList + "//tr[" + index + "]//a";

        SelCmd.performActionOnBy(driver, SelCmd.SelCommand.CLICK, By.xpath(xpath), null);
    }
}
