package com.pingone.automation.webportal.common;

import java.util.HashMap;
import java.util.Map;

public class TestData
{
    private Map<String, String> data = new HashMap<>();

    public TestData(String[] header, String[] value)
    {
        for (int i = 0; i < header.length; i++)
        {
            data.put(header[i], value[i]);
        }
    }

    public Map<String, String> getData()
    {
        return data;
    }
}
