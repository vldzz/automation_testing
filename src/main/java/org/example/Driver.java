package org.example;

import lombok.Getter;
import lombok.Setter;
import org.example.logger.LocalLogger;
import org.example.properties.PropertiesLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

import java.io.File;

public class Driver {
    private static final LocalLogger logger = LocalLogger.getLogger(Driver.class);

    public static WebDriver getDriver(Browser browser) {
        switch (browser) {
            case CHROME:
                System.setProperty("webdriver.chrome.driver", PropertiesLoader.getChromeDriverPath());

                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox"); // Bypass OS security model

                return new ChromeDriver(chromeOptions);

            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", PropertiesLoader.getFirefoxDriverPath());

                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setHeadless(true);
                firefoxOptions.setProfile(new FirefoxProfile());
                firefoxOptions.setBinary(new FirefoxBinary(new File("/usr/bin/firefox")));

//                File file = new File("/usr/bin/firefox");
//                if (file.exists()) {
//                    logger.error("exists");
//                } else {
//                    logger.error("doesnt exists");
//                }


                return new FirefoxDriver(firefoxOptions);

            case OPERA:
                System.setProperty("webdriver.opera.driver", PropertiesLoader.getOperaDriverPath());

                OperaOptions operaOptions = new OperaOptions();
                operaOptions.setBinary("/usr/bin/opera");

                return new OperaDriver(operaOptions);

        }
        return null;
    }

}
