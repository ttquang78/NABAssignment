package com.myutils.automation.selenium;

import com.pingone.automation.webportal.common.CustomData;
import com.pingone.automation.webportal.common.Setup;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SelAction extends SelBase {
    private static Logger log = Logger.getLogger(SelAction.class.getName());

    private SelAction() {
    }

    static void clickElement(WebDriver driver, WebElement element) {
        waitForElementClickable(driver, element);

        log.info("Trying to click element " + element + "] ...");
        if (Setup.browserName == CustomData.BrowserName.IE) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
        } else {
            element.click();
        }
        log.info("Clicked element " + element + "]");
    }

    static void clickElement(WebDriver driver, By by) {
        waitForElementPresence(driver, by);
        WebElement element = driver.findElement(by);

        waitForElementClickable(driver, element);

        log.info("Trying to click element " + element + "] ...");
        if (Setup.browserName == CustomData.BrowserName.IE) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
        } else {
            element.click();
        }
        log.info("Clicked element " + element + "]");
    }

    public static void selectCategory(WebDriver driver, WebElement lstCategory, String option) {
        waitForElementVisibility(driver, lstCategory);

        log.info("Trying to select option from  [" + lstCategory + "] ...");
        Select categoryList = new Select(lstCategory);
        categoryList.selectByVisibleText(option);
        log.info("Selected [" + option + "] from element " + lstCategory + "]");
    }

    static void sendKeysToElement(WebDriver driver, WebElement element, String value) {
        waitForElementVisibility(driver, element);

        log.info("Trying to send keys to element [" + element + "] ...");
        element.clear();
        element.sendKeys(value);
        /*if (Setup.browserName == CustomData.BrowserName.IE)// If run parallel with multiple browser, should use Threadlocal for browserName
        {
            ((JavascriptExecutor)driver).executeScript("arguments[0].value ='';", element);
        }
        else
        {
            element.sendKeys(value);
        }*/
        log.info("Sent [" + value + "] to element " + element + "]");
    }

    public static void scrollToElement(WebDriver driver, WebElement element) {
        waitForElementVisibility(driver, element);

        log.info("Trying to scroll to [" + element + "] ...");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        log.info("Scrolled to [" + element + "]");
    }

}
