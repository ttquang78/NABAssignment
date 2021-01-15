package com.openweather;

import com.myutils.automation.selenium.SelCmd;
import com.myutils.automation.selenium.WebDriverManager;
import com.myutils.automation.tools.ReportUtil;
import com.pingone.automation.webportal.common.Setup;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

public class TestBase
{
    @BeforeClass
    public void beforeClass() throws IOException
    {
        Setup.initTestEnv();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeBaseMethod(Method method) throws MalformedURLException
    {
        WebDriverManager.setBrowserDriver(Setup.browserName, method.getName());
        WebDriverManager.setErrorBuffer();
    }

    @AfterMethod(alwaysRun = true)
    public void afterBaseMethod(ITestResult result)
    {
        if (Setup.runTime.equalsIgnoreCase("saucelabs")) {
            WebDriverManager.updateTestResultToSauceLabs(result.isSuccess());
        }
        getDriver().quit();
        ReportUtil.endCollapseHtml();
    }

    public WebDriver getDriver()
    {
        return WebDriverManager.get();
    }

    public void openURL(WebDriver driver, String url)
    {
        ReportUtil.logStep("Open URL: " + url);
        SelCmd.performAction(driver, SelCmd.SelCommand.GOTO,null, Setup.appURL);
    }

}
