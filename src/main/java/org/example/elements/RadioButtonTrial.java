package org.example.elements;

import org.example.Browser;
import org.example.Driver;
import org.example.logger.LocalLogger;
import org.example.pages.RadioButtonPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class RadioButtonTrial  {
    private static final LocalLogger logger = LocalLogger.getLogger(RadioButtonTrial.class);
    private static WebDriver webDriver;
    private RadioButtonPage page;


    @Before
    public void initialize() {
        webDriver = Driver.getDriver(Browser.CHROME);
        page = new RadioButtonPage(webDriver);
        page.navigateTo();
    }

    @Test
    public void clickButtonYes() {
        page.clickYesBtn();
        Assert.assertEquals(page.getTextSuccessText(), page.getRadioBtnYesText());
    }

    @Test
    public void clickButtonImpressive() {
        page.clickImpressiveBtn();
        Assert.assertEquals(page.getTextSuccessText(), page.getRadioBtnImpressiveText());
    }

    @Test
    public void clickButtonNo() {
        Assert.assertTrue(page.isRadioBtnNoEnabled());
    }


    @After
    public void closeDriver() {
        webDriver.close();
    }


    private static void sleep(long milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
