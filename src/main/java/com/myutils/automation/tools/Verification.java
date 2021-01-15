package com.myutils.automation.tools;

import com.myutils.automation.selenium.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

public class Verification
{
    private Verification() {}

    public static void logErrorAndGo(boolean isStop, String error)
    {
        StringBuilder verificationErrorString = WebDriverManager.getErrorBuffer().append(error);
        WebDriverManager.setErrorBuffer(verificationErrorString.toString());

        //Stop Test if optionalContinueTest is set to False
        if (isStop)
        {
            WebDriverManager.checkForVerificationErrors();
        }
    }

    public static void verifyTrue(WebDriver driver, Boolean b, String msg, boolean isStop)
    {
        try
        {
            Assert.assertTrue(b);
            Reporter.log(" <b><font color='purple'>CHECK POINT</font></b>: " + msg + ": <b><font color='green'>Pass</font></b><br>");
        }
        catch (Throwable e) //Throwable to bypass the TestNG listener
        {
            ReportUtil.logError(driver, msg);
            logErrorAndGo(isStop, e.toString());
        }
    }

    /**
     * [CUSTOM VERIFYFALSE]:
     * /*- Checks a boolean expression, and will log a defined message with screenshot
     * /*  if result is true (else do nothing)
     * /*---------------------------------------------------------------------------
     */
    public static void verifyFalse(WebDriver driver, Boolean b, String msg, boolean isStop)
    {
        try
        {
            Assert.assertFalse(b);
            Reporter.log(" <b><font color='purple'>CHECK POINT</font></b>: " + msg + ": <b><font color='green'>Pass</font></b><br>");
        }
        catch (Throwable e)//Throwable to bypass the TestNG listener
        {
            ReportUtil.logError(driver, msg);
            logErrorAndGo(isStop, e.toString());
        }
    }
}
