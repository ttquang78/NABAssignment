package com.openweather.pages;

import com.myutils.automation.selenium.SelBase;
import com.myutils.automation.selenium.SelCmd;
import com.myutils.automation.tools.ReportUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StickFooterPage {
    private final WebDriver driver;

    public StickFooterPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(css = "div#stick-footer-panel button")
    private WebElement btnAllowAll;

    @FindBy(css = "div.owm-loader")
    private WebElement divLoader;

    public void clickAllowAll() {
        if (SelBase.isElementVisibility(driver, btnAllowAll)) {
            ReportUtil.logStep("Allow all cookies");
            SelBase.waitWithReason(5, "wait loading");
            SelCmd.performAction(driver, SelCmd.SelCommand.CLICK, btnAllowAll, null);
        }
    }
}
