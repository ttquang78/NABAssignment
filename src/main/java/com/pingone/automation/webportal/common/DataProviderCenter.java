package com.pingone.automation.webportal.common;

import com.myutils.automation.tools.CsvUtils;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Properties;

public class DataProviderCenter
{
    private final String DATA_LOCATION = "Data/";

    @DataProvider(name = "LoadData")
    public Object[][] loadData(Method method) throws IOException
    {
        List<String[]> csvImport = CsvUtils.importCsv(getDataFilePath(method.getName(), false));

        return initTestData(csvImport);
    }

    @DataProvider(name = "LoadSharedData")
    public Object[][] loadSharedData(Method method) throws IOException
    {
        List<String[]> csvImport = CsvUtils.importCsv(getDataFilePath(method.getName(), true));

        return initTestData(csvImport);
    }

    private TestData[][] initTestData(List<String[]> csvImport)
    {
        TestData[][] testData = new TestData[csvImport.size() - 1][1];
        for (int i = 0; i < csvImport.size() - 1; i++)
        {
            testData[i][0] = new TestData(csvImport.get(0), csvImport.get(i + 1));
        }

        return testData;
    }

    private String getDataFilePath(String methodName, boolean isUseSharedData) throws IOException
    {
        String filePath;
        if (isUseSharedData)
        {
            Properties dataMap = new Properties();
            dataMap.load(Setup.class.getResourceAsStream("/datamap.properties"));
            String replaceMethodName = dataMap.getProperty(methodName);

            filePath = DATA_LOCATION + replaceMethodName + "_Data.csv";
        }
        else
        {
            filePath = DATA_LOCATION + methodName + "_Data.csv";
        }

        return filePath;
    }
}
