package com.pullapart.common;

import com.pullapart.dataprovider.JsonReader;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;


public class TestBase {

    protected static String enterpriseBaseURL = "";
    protected static String inventoryBaseURL = "";
    private final String dataPath = "src/main/java/com/pullapart/dataprovider/";

    @BeforeSuite(alwaysRun = true)
    public void beforSuite() throws IOException {
        Properties testConfig = new Properties();
        InputStream input = TestBase.class.getClassLoader().getResourceAsStream("apitestconfig.properties");
        testConfig.load(input);

        if (System.getProperty("enterpriseURL") != null) {
            enterpriseBaseURL = System.getProperty("enterpriseURL");
        } else {
            enterpriseBaseURL = testConfig.getProperty("enterpriseBaseURL");
        }
        if (System.getProperty("inventoryURL") != null ) {
            inventoryBaseURL = System.getProperty("inventoryURL");
        } else {
            inventoryBaseURL = testConfig.getProperty("inventoryBaseURL");
        }
    }

    @DataProvider(name = "dataProvider")
    public Object[][] passData(Method method) throws IOException
    {
        String name = getClass().getName();
        String fileName = name.substring(name.lastIndexOf(".") + 1).trim();
        return JsonReader.getdata(dataPath.concat(fileName).concat(".json"), method.getName());
    }


}
