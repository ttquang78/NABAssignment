package com.myutils.automation.tools;

import com.pingone.automation.webportal.common.Setup;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReportUtil
{
    public static String screenshotPath;  //Specified within Setup

    private ReportUtil() {}

    public static void initReportNG()
    {
        // Set SCREENSHOTS folder path (take/save capture when failure occurs):
        screenshotPath = "target/Screenshots/";

        // Set SYSTEM properties:
        // -TestNG properties
        System.setProperty("org.uncommons.reportng.stylesheet", "Config/reportng.css");
        System.setProperty("org.uncommons.reportng.escape-output", "false");
        System.setProperty("org.uncommons.reportng.title",
                "Automation Results" + " <font size=2 color=\"purple\">"
                        + "<br> Browser: " + Setup.browserName + "</font>");
    }

    /**
     * [STARTCOLLAPSEHTML]:
     * /*- Collapse function for html report (+/- to show hide the test case stestps)
     * /*-------------------------------------------------
     */
    public static void startCollapseHtml(String name)
    {
        Reporter.log("<details>");
        Reporter.log("<summary class='heading'>" + name + "</summary>");
    }

    /**
     * [ENDCOLLAPSEHTML]:
     * /*- Close the fnal DIV of the test case steps loging
     * /*-------------------------------------------------
     */
    public static void endCollapseHtml()
    {
        Reporter.log("<br></details>");
    }

    /**
     * [LOGSTEP]:
     * /*- Log the step of a test case
     * /*-------------------------------------------------
     */
    public static void logStep(String step)
    {
        Reporter.log("<br><h4>&raquo; " + step + "</h4>");
    }

    public static void logSubStep(String step)
    {
        Reporter.log("<br>- " + step + "<br>");
    }

    /**
     * [CUSTOM EXCEPTIONERROR]:
     * /*- This function is called by the WebDriverEventListener (errorListener),
     * /*- whenever an exception occurs (throwable) and is not caught by any of the SelUtil functions
     * //------------------------------------------------------------------------
     */
    public static void logExceptionError(WebDriver driver, String msg)
    {
        String screenshotLink =
                " - <font color='red'>Exception Failure:</font>  <a href=" + captureScreen(
                        driver) + " target='_blank'>" + "[View Screenshot] </a> -- " + msg + "<br>";
        Reporter.log(screenshotLink);
    }

    public static void logError(WebDriver driver, String msg)
    {
        Reporter.log(" - " + msg);
        String screenshotLink =
                " - <b><font color='red'>FAIL:</font></b>  <a href=" + captureScreen(
                        driver) + " target='_blank'>" + "[View Screenshot] </a><br>";
        Reporter.log(screenshotLink);
    }

    /**
     * [CAPTURE SCREENSHOT]
     * /*- file is copied to pre-defined path (see Private String screenshotPath)
     * /*- Filename as date and timestamp
     * /*---------------------------------------------------------------------
     */
    private static synchronized String captureScreen(WebDriver driver, String... appName)
    {
        String path;

        try
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMMdd(HH-mm-ss)");
            String dateString = dateFormat.format(Calendar.getInstance().getTime());
            if (appName.length > 0)
            {
                path = screenshotPath + appName[0] + ".png";
            }
            else
            {
                path = screenshotPath + dateString + ".png";
            }

            File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File(path));
        }
        catch (Exception e)
        {
            path = "Failed_To_Capture_Screenshot:_" + e.getMessage();
        }

        //Return the absolute path of the file for the html file to display correctly
        return "../../" + path;
    }

}
