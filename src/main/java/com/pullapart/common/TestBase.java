package com.pullapart.common;

import com.pullapart.utils.JsonReader;
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

        if (System.getProperty("enterprise.env") != null) {
            enterpriseBaseURL = System.getProperty("enterprise.env");
            System.out.println("using " + enterpriseBaseURL);
        } else {
            enterpriseBaseURL = testConfig.getProperty("enterpriseBaseURL");
        }
        if (System.getProperty("inventory.env") != null ) {
            inventoryBaseURL = System.getProperty("inventory.env");
            System.out.println("using " + inventoryBaseURL);
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
