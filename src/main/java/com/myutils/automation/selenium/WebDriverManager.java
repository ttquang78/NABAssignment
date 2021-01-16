package com.myutils.automation.selenium;

import com.pingone.automation.webportal.common.CustomData;
import com.pingone.automation.webportal.common.Setup;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverManager {
    private WebDriverManager() {
    }

    // WebDriver as localthread
    private static final ThreadLocal<EventFiringWebDriver> webDriver = new ThreadLocal<>();

    // Verification Error Buffer as localthread
    // -To store failures in a buffer
    private static final ThreadLocal<StringBuffer> errorBuffer = new ThreadLocal<>();

    /**
     * ------------------------------------------------------ /* [SETVERBUFFER]
     * /*- Sets a new Error Buffer
     * /*------------------------------------------------------
     */
    public static synchronized void setErrorBuffer() {
        errorBuffer.set(new StringBuffer());
    }

    public static synchronized void setErrorBuffer(String errorStr) {
        errorBuffer.set(new StringBuffer(errorStr));
    }

    /**
     * ------------------------------------------------------ /* [GETVERBUFFER]
     * /*- Returns the Error Buffer
     * /*------------------------------------------------------
     */
    public static synchronized StringBuilder getErrorBuffer() {
        return new StringBuilder(errorBuffer.get());
    }

    public static void checkForVerificationErrors() {

        String verificationErrorString = WebDriverManager.getErrorBuffer().toString();

        //Fail test case if needed
        if (!verificationErrorString.isEmpty()) {
            Assert.fail(verificationErrorString);
        }

    }

    public static WebDriver get() {
        return webDriver.get();
    }

    public static synchronized void setBrowserDriver(CustomData.BrowserName browserName, String testName)
            throws MalformedURLException {
        WebDriver driver;
        if (Setup.runTime.equalsIgnoreCase("local")) {
            Capabilities options = setBrowserOption(browserName);
            driver = setLocalBrowserDriver(browserName, options);
        } else {
            DesiredCapabilities caps = setCapabilities(testName);
            driver = new RemoteWebDriver(new URL(Setup.saucelabsURL), caps);
        }

        webDriver.set(new EventFiringWebDriver(driver));
    }

    private static DesiredCapabilities setCapabilities(String testName) {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", Setup.browserName.getBrowser());
        caps.setCapability("name", testName);

        if (Setup.browserName == CustomData.BrowserName.SAFARI) {
            caps.setCapability("version", "10");
        }

        return caps;
    }

    private static WebDriver setLocalBrowserDriver(CustomData.BrowserName browserName,
                                                   Capabilities options) {
        if (Setup.os == CustomData.OS.WINDOWS) {
            return setLocalBrowserDriverOnWin(browserName, options);
        } else {
            return setLocalBrowserDriverOnMac(browserName, options);
        }
    }

    private static WebDriver setLocalBrowserDriverOnMac(CustomData.BrowserName browserName, Capabilities options) {
        WebDriver driver;
        if (browserName == CustomData.BrowserName.FIREFOX) {
            System.setProperty("webdriver.gecko.driver", Setup.localMacFirefoxDriverPath);
            driver = new FirefoxDriver((FirefoxOptions) options);
        } else {
            System.setProperty("webdriver.chrome.driver", Setup.localMacChromeDriverPath);
            driver = new ChromeDriver((ChromeOptions) options);
        }

        return driver;
    }

    private static Capabilities setBrowserOption(CustomData.BrowserName browserName) {
        switch (browserName) {
            case CHROME:
                return setChromeOption();

            case MSEDGE:
                String localAppDataDir = System.getenv("LOCALAPPDATA");
                String edgeSideLoadPath = localAppDataDir
                        + "\\Packages\\Microsoft.MicrosoftEdge_8wekyb3d8bbwe\\LocalState\\BE";
                String[] extPaths = new String[]{edgeSideLoadPath};

                EdgeOptions edgeOpts = new EdgeOptions();
                edgeOpts.setCapability("extensionPaths", extPaths);
                edgeOpts.setPageLoadStrategy("normal");
                edgeOpts.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);

                return edgeOpts;

            case FIREFOX:
                return setFirefoxOption();

            case IE:
                InternetExplorerOptions ieOpts = new InternetExplorerOptions();
                ieOpts.ignoreZoomSettings();
                ieOpts.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                ieOpts.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);

                return ieOpts;

            default:
                return null;
        }
    }

    private static ChromeOptions setChromeOption() {
        ChromeOptions chromeOpts = new ChromeOptions();

        chromeOpts.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        chromeOpts.addArguments("--start-maximized");
        chromeOpts.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        return chromeOpts;
    }

    private static FirefoxOptions setFirefoxOption() {
        FirefoxProfile firefoxProfile = new FirefoxProfile();

        firefoxProfile.setAcceptUntrustedCertificates(true);
        firefoxProfile.setAssumeUntrustedCertificateIssuer(false);
        firefoxProfile.setPreference("dom.disable_open_during_load", false);

        FirefoxOptions ffOptions = new FirefoxOptions();
        ffOptions.setProfile(firefoxProfile);
        ffOptions.addPreference("marionette", true);
        ffOptions.addPreference("acceptInsecureCerts", true);
        ffOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        //ffOptions.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);

        return ffOptions;
    }

    private static WebDriver setLocalBrowserDriverOnWin(CustomData.BrowserName browserName,
                                                        Capabilities options) {
        WebDriver driver;
        switch (browserName) {
            case MSEDGE:
                System.setProperty("webdriver.edge.driver", Setup.localMSEdgeDriverPath);
                driver = new EdgeDriver((EdgeOptions) options);

                break;
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", Setup.localWinFirefoxDriverPath);
                driver = new FirefoxDriver((FirefoxOptions) options);

                break;
            case IE:
                System.setProperty("webdriver.ie.driver", Setup.localIEDriverPath);
                driver = new InternetExplorerDriver((InternetExplorerOptions) options);

                break;
            default:
                System.setProperty("webdriver.chrome.driver", Setup.localWinChromeDriverPath);
                driver = new ChromeDriver((ChromeOptions) options);
                break;
        }

        return driver;
    }

    public static void updateTestResultToSauceLabs(boolean isPassed) {
        String result = "failed";
        if (isPassed) {
            result = "passed";
        }

        ((JavascriptExecutor) get()).executeScript("sauce:job-result=" + (result));
    }

}
