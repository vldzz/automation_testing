package org.example.elements;

import org.example.Browser;
import org.example.Driver;
import org.example.logger.LocalLogger;
import org.example.pages.WebPage;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.InvocationTargetException;

public abstract class WebPageTest {
    private LocalLogger logger;
    private Class<?> clazz;
    protected WebDriver webDriver;
    protected WebPage page;

    public WebPageTest(Class<? extends WebPage> clazz) {
        this.clazz = clazz;
        this.logger = LocalLogger.getLogger(clazz);
        webDriver = Driver.getDriver(Browser.CHROME);
    }

    @Before
    public void initialize() {
        try {
            this.page = (WebPage) clazz.getDeclaredConstructor(WebDriver.class).newInstance(webDriver);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        webDriver.manage().window().maximize();
        page.navigateTo();
    }

    @After
    public void closeDriver() {
//        sleep(1000);
        webDriver.close();
    }

    public void log(String text) {
        logger.error(text);
    }

    public static void sleep(long milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
