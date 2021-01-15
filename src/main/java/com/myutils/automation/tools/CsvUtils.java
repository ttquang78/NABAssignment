package com.myutils.automation.tools;

import com.opencsv.CSVReader;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class CsvUtils
{
    private static final Logger log = Logger.getLogger(CsvUtils.class.getName());

    private CsvUtils()
    {}

    public static List<String[]> importCsv(String accFilePath) throws IOException
    {
        List<String[]> csvImport;

        try (InputStream csvFilePath = new FileInputStream(accFilePath))
        {
            CSVReader reader;
            reader = new CSVReader(new InputStreamReader(csvFilePath));
            csvImport = reader.readAll();
            reader.close();
        }
        catch (IOException e)
        {
            log.error(e.getMessage());
            throw e;
        }

        return csvImport;
    }
}
