package com.openweather.testsuites;

import com.myutils.automation.selenium.WebDriverManager;
import com.myutils.automation.tools.ReportUtil;
import com.myutils.automation.tools.Verification;
import com.openweather.TestBase;
import com.openweather.pages.CityDetailPage;
import com.openweather.pages.HeaderPage;
import com.openweather.pages.SearchResultPage;
import com.openweather.pages.StickFooterPage;
import com.pingone.automation.webportal.common.DataProviderCenter;
import com.pingone.automation.webportal.common.Setup;
import com.pingone.automation.webportal.common.TestData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class AssignmentSuite  extends TestBase {
    @Test(dataProviderClass = DataProviderCenter.class, dataProvider = "LoadData")
    public void OW_SearchCityWeather(Method method, TestData data) {
        ReportUtil.startCollapseHtml(method.getName());

        //Init driver and pages for testing
        WebDriver driver = getDriver();
        HeaderPage headerPage = PageFactory.initElements(driver, HeaderPage.class);
        SearchResultPage searchResultPage = PageFactory.initElements(driver, SearchResultPage.class);

        //Test data
        String searchStr = data.getData().get("SearchValue");
        String expectResult = data.getData().get("ExpectResult");

        //Test steps
        openURL(driver, Setup.appURL);
        headerPage.searchCity(searchStr);

        //Check points
        boolean isCorrect = searchResultPage.isCorrectResult(expectResult);
        Verification.verifyTrue(driver, isCorrect, expectResult, true);

        WebDriverManager.checkForVerificationErrors();
    }

    @Test(dataProviderClass = DataProviderCenter.class, dataProvider = "LoadData")
    public void OW_ViewForecastWeatherOfDay(Method method, TestData data) {
        ReportUtil.startCollapseHtml(method.getName());

        //Init driver and pages for testing
        WebDriver driver = getDriver();
        StickFooterPage stickFooterPage = PageFactory.initElements(driver, StickFooterPage.class);
        HeaderPage headerPage = PageFactory.initElements(driver, HeaderPage.class);
        SearchResultPage searchResultPage = PageFactory.initElements(driver, SearchResultPage.class);
        CityDetailPage cityDetailPage = PageFactory.initElements(driver, CityDetailPage.class);

        //Test data
        String city = data.getData().get("City");
        int selectResult = Integer.parseInt(data.getData().get("SelectResult"));
        int viewForecastOfDay = Integer.parseInt(data.getData().get("ViewForcastOfDay"));

        //Test steps
        openURL(driver, Setup.appURL);
        stickFooterPage.clickAllowAll();
        headerPage.searchCity(city);
        int numberOfResult = searchResultPage.countResult();
        Verification.verifyTrue(driver, (selectResult <= numberOfResult)
                , "The chosen data should be in the allowed range: " + numberOfResult
                , true);

        searchResultPage.clickSearchResultNumber(selectResult);
        Verification.verifyTrue(driver, cityDetailPage.is8dayForecastDisplay()
                , "8-day forecast panel is displayed"
                , true);
        Verification.verifyTrue(driver, cityDetailPage.isForecastOfDayDisplay(viewForecastOfDay)
                , "Forecast of expectation day is displayed"
                , true);

        cityDetailPage.expandForecastOfDate(viewForecastOfDay);
        Verification.verifyTrue(driver, cityDetailPage.isDailyDetailPanelDisplay()
                , "Daily Detail Panel is displayed"
                , true);

        WebDriverManager.checkForVerificationErrors();
    }
}
