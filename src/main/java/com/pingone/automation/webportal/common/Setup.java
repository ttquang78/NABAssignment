package com.pingone.automation.webportal.common;

import com.myutils.automation.tools.ReportUtil;

import java.io.IOException;
import java.util.Properties;

public class Setup
{
    public static String runTime = "local";
    public static CustomData.OS os = CustomData.OS.valueOf("WINDOWS");
    public static CustomData.BrowserName browserName = CustomData.BrowserName.valueOf("CHROME");
    public static String appURL;

    public static String localWinChromeDriverPath;
    public static String localWinFirefoxDriverPath;
    public static String localIEDriverPath;
    public static String localMSEdgeDriverPath;

    public static String localMacChromeDriverPath;
    public static String localMacFirefoxDriverPath;

    public static String saucelabsURL;

    private Setup() {}

    public static void initTestEnv() throws IOException
    {
        Properties setupProps = new Properties();
        setupProps.load(Setup.class.getResourceAsStream("/setup.properties"));

        appURL = setupProps.getProperty("AppURL");
        if (!setupProps.getProperty("RunTime").equalsIgnoreCase("${webruntime}"))
        {
            runTime = setupProps.getProperty("RunTime");
        }
        if (!setupProps.getProperty("OS").equalsIgnoreCase("${os}"))
        {
            os = CustomData.OS.valueOf(setupProps.getProperty("OS").toUpperCase());
        }
        if (!setupProps.getProperty("BrowserName").equalsIgnoreCase("${browser}"))
        {
            browserName = CustomData.BrowserName.valueOf(setupProps.getProperty("BrowserName").toUpperCase());
        }

        String localDriverPath = "./seleniumdrivers";
        if (!setupProps.getProperty("LocalDriverPath").equalsIgnoreCase("${localDriverPath}"))
        {
            localDriverPath = setupProps.getProperty("LocalDriverPath");
        }

        localWinChromeDriverPath = localDriverPath + "/chromedriver.exe";
        localWinFirefoxDriverPath = localDriverPath + "/geckodriver.exe";
        localIEDriverPath = localDriverPath + "/IEDriverServer.exe";
        localMSEdgeDriverPath = localDriverPath + "/MicrosoftWebDriver.exe";

        localMacChromeDriverPath = localDriverPath + "/chromedriver";
        localMacFirefoxDriverPath = localDriverPath + "/geckodriver";

        saucelabsURL = "https://" + setupProps.getProperty("Saucelabs_Username") + ":" + setupProps
                .getProperty("Saucelabs_AccessKey") + "@ondemand.saucelabs.com:443/wd/hub";

        ReportUtil.initReportNG();
    }
}
