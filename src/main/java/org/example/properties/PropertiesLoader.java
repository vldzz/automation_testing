package org.example.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class PropertiesLoader {
    private static String propertiesPath = "src/main/resources/app.properties";
    private static HashMap<String, String> webdriverCache = new HashMap<>();

    public static String getWebsiteURL() {
        return getProperty("demoqa.url");
    }

    public static String getOperaDriverPath() {
        return getProperty("webdriver.opera.driver");
    }

    public static String getFirefoxDriverPath() {
        return getProperty("webdriver.gecko.driver");
    }

    public static String getChromeDriverPath() {
        return getProperty("webdriver.chrome.driver");
    }


    public static String getProperty(String key) {
        //For optimization
        if (webdriverCache.containsKey(key))
            return webdriverCache.get(key);

        try {
            Properties properties = getProperties();
            webdriverCache.put(key, properties.getProperty(key));

            return webdriverCache.get(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static Properties getProperties() throws IOException {
        Properties props = new Properties();
        props.load(new FileInputStream(propertiesPath));
        return props;
    }
}
