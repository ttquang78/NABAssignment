package com.myutils.automation.selenium;

import com.myutils.automation.tools.ReportUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class SelCmd {
    private static Logger log = Logger.getLogger(SelCmd.class.getName());

    public enum SelCommand {
        CLICK, SENDKEY, GOTO, SELECT, SCROLL_TO_ELEMENT,
        GET_TEXT, GET_ATTRIBUTE,
        WAIT_ELEMENT_PRESENCE

    }

    public static void performAction(WebDriver driver, SelCommand cmd, WebElement element, String value) {
        log.info("Perform action: " + cmd.toString());
        int retryCmd = 2;
        String errorMsg = "";
        while (retryCmd > 0) {
            try {
                switch (cmd) {
                    case CLICK:
                        SelAction.clickElement(driver, element);
                        break;
                    case GOTO:
                        SelBase.loadURL(driver, value);
                        break;
                    case SELECT:
                        SelAction.selectCategory(driver, element, value);
                        break;
                    case SENDKEY:
                        SelAction.sendKeysToElement(driver, element, value);
                        break;
                    case SCROLL_TO_ELEMENT:
                        SelAction.scrollToElement(driver, element);
                        break;
                    default:
                        Assert.fail("!!!Command is not supported");
                        break;
                }
                break;
            } catch (Exception e) {
                retryCmd--;
                errorMsg = e.toString();
            }
        }

        if (!errorMsg.isEmpty()) {
            ReportUtil.logError(WebDriverManager.get(), "Unexpected Error");
            Assert.fail(errorMsg);
        }
    }

    public static void performActionOnBy(WebDriver driver, SelCommand cmd, By element, String value) {
        log.info("Perform action: " + cmd.toString());
        int retryCmd = 2;
        String errorMsg = "";
        while (retryCmd > 0) {
            try {
                switch (cmd) {
                    case CLICK:
                        SelAction.clickElement(driver, element);
                        break;
                    case WAIT_ELEMENT_PRESENCE:
                        SelBase.waitForElementPresence(driver, element);
                        break;
                    default:
                        Assert.fail("!!!Command is not supported");
                        break;
                }
                break;
            } catch (Exception e) {
                retryCmd--;
                errorMsg = e.toString();
            }
        }

        if (!errorMsg.isEmpty()) {
            ReportUtil.logError(WebDriverManager.get(), "Unexpected Error");
            Assert.fail(errorMsg);
        }
    }

    public static String performExtract(WebDriver driver, SelCommand cmd, WebElement element, String value) {
        log.info("Perform action: " + cmd.toString());
        int retryCmd = 2;
        String errorMsg = "";
        String strReturn = "";
        while (retryCmd > 0) {
            try {
                switch (cmd) {
                    case GET_ATTRIBUTE:
                        strReturn = SelBase.getAttribute(driver, element, value);
                        break;
                    case GET_TEXT:
                        strReturn = SelBase.getText(driver, element);
                        break;
                    default:
                        Assert.fail("!!!Command is not supported");
                        break;
                }
                break;
            } catch (Exception e) {
                retryCmd--;
                errorMsg = e.toString();
            }
        }

        if (!errorMsg.isEmpty()) {
            ReportUtil.logError(WebDriverManager.get(), "Unexpected Error");
            Assert.fail(errorMsg);
        }

        return strReturn;
    }
}
