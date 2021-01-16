package com.myutils.automation.selenium;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelBase {
    private static Logger log = Logger.getLogger(SelBase.class.getName());
    private static final int DEFAULT_WAITING_TIMEOUT = 15;

    public static WebElement findElement(WebDriver driver, By locator) {
        SelCmd.performActionOnBy(driver, SelCmd.SelCommand.WAIT_ELEMENT_PRESENCE, locator, null);
        //waitForElementPresence(driver, locator);
        return driver.findElement(locator);
    }

    public static String getText(WebDriver driver, WebElement element) {
        waitForElementVisibility(driver, element);
        log.info("Getting text of " + element + "...");
        String value = element.getText();
        log.info("Text of " + element + " is: " + value);
        return value;
    }

    public static String getAttribute(WebDriver driver, WebElement element, String attribute) {
        waitForElementVisibility(driver, element);
        log.info("Getting attribute [" + attribute + "] of " + element + "...");
        String value = element.getAttribute(attribute);
        log.info("Value of " + attribute + " is: " + value);
        return value;
    }

    public static void waitWithReason(int second, String reason) {
        try {
            log.info("Waiting " + second + "s for " + reason);
            Thread.sleep(second * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void loadURL(WebDriver driver, String url) {
        driver.get(url);
    }

    public static void waitForElementClickable(WebDriver driver, WebElement element, int timeout) {
        log.info("Waiting for element to be clickable in " + timeout + "s ...");
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        log.info("[" + element + "] is clickable");
    }

    public static void waitForElementClickable(WebDriver driver, WebElement element) {
        waitForElementClickable(driver, element, DEFAULT_WAITING_TIMEOUT);
    }

    private static void waitForElementPresence(WebDriver driver, By locator, int timeout) {
        log.info("Waiting for element presence in " + timeout + "s ...");
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        log.info("[" + locator + "] is presence");
    }

    public static void waitForElementPresence(WebDriver driver, By locator) {
        waitForElementPresence(driver, locator, DEFAULT_WAITING_TIMEOUT);
    }

    static void waitForElementVisibility(WebDriver driver, WebElement element, int timeout) {
        log.info("Waiting for element to be visibility in " + timeout + "s ...");
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
        log.info("[" + element + "] is visible");
    }

    public static void waitForElementVisibility(WebDriver driver, WebElement element) {
        waitForElementVisibility(driver, element, DEFAULT_WAITING_TIMEOUT);
    }

    private static boolean isTextPresentInElement(WebDriver driver, WebElement element, String text, int timeout) {
        log.info("Waiting for '" + text + "' present in element in " + timeout + "s ...");
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.textToBePresentInElement(element, text));
        } catch (Exception e) {
            log.error("Text was not presented in [" + element + "]");
            return false;
        }
        log.info("Text presented in [" + element + "]");
        return true;
    }

    public static boolean isTextPresentInElement(WebDriver driver, WebElement element, String text) {
        return isTextPresentInElement(driver, element, text, DEFAULT_WAITING_TIMEOUT);
    }

    private static boolean isVisibilityOfElementLocated(WebDriver driver, By locator, int timeout) {
        log.info("Waiting for element in " + timeout + "s ...");
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            log.error("!!!Element was not visible");
            return false;
        }
        log.info("Element [" + locator + "] was visible");
        return true;
    }

    public static boolean isVisibilityOfElementLocated(WebDriver driver, By locator) {
        return isVisibilityOfElementLocated(driver, locator, DEFAULT_WAITING_TIMEOUT);
    }

    private static boolean isElementVisibility(WebDriver driver, WebElement element, int timeout) {
        log.info("Waiting for element in " + timeout + "s ...");
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            log.error("!!!Element was not visible");
            return false;
        }
        log.info("Element [" + element + "] was visible");
        return true;
    }

    public static boolean isElementVisibility(WebDriver driver, WebElement element) {
        return isElementVisibility(driver, element, DEFAULT_WAITING_TIMEOUT);
    }

    public static void waitForElementInvisibility(WebDriver driver, WebElement element, int timeout) {
        log.info("Waiting for element to be invisibility in " + timeout + "s ...");
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.invisibilityOf(element));
        log.info("[" + element + "] is visible");
    }

    public static void waitForElementInvisibility(WebDriver driver, WebElement element) {
        waitForElementInvisibility(driver, element, DEFAULT_WAITING_TIMEOUT);
    }

    private static boolean isInvisibilityOfElementLocated(WebDriver driver, By locator, int timeout) {
        log.info("Waiting for element in " + timeout + "s ...");
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception e) {
            log.error("!!!Element was not invisible");
            return false;
        }
        log.info("Element [" + locator + "] was invisible");
        return true;
    }

    public static boolean isInvisibilityOfElementLocated(WebDriver driver, By locator) {
        return isInvisibilityOfElementLocated(driver, locator, DEFAULT_WAITING_TIMEOUT);
    }

    private static boolean isElementInvisibility(WebDriver driver, WebElement element, int timeout) {
        log.info("Waiting for element in " + timeout + "s ...");
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.invisibilityOf(element));
        } catch (Exception e) {
            log.error("!!!Element was not invisible");
            return false;
        }
        log.info("Element [" + element + "] was invisible");
        return true;
    }

    public static boolean isElementInvisibility(WebDriver driver, WebElement element) {
        return isElementInvisibility(driver, element, DEFAULT_WAITING_TIMEOUT);
    }
}
