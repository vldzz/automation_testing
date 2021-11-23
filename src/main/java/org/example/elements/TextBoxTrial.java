package org.example.elements;

import org.example.logger.LocalLogger;
import org.example.pages.TextBoxPage;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class TextBoxTrial extends WebPageTest {
    private static final LocalLogger logger = LocalLogger.getLogger(TextBoxTrial.class);
    private static WebDriver webDriver;

    private String testName = "Vladz";
    private String testEmail = "vladz@gmail.com";
    private String testCurrentAddress = "Orhei, ....";
    private String testPermanentAddress = "Chisinau, ....";

    public TextBoxTrial() {
        super(TextBoxPage.class);
    }

    @Test
    public void fillFullNameFieldTest() {
        ((TextBoxPage) page).fillNameTextbox(testName);
        ((TextBoxPage) page).submitButton();
        Assert.assertTrue(((TextBoxPage) page).getName().contains(testName));
    }

    @Test
    public void fillEmailFieldTest() {
        ((TextBoxPage) page).fillEmailTextbox(testEmail);
        ((TextBoxPage) page).submitButton();
        Assert.assertTrue(((TextBoxPage) page).getEmail().contains(testEmail));
    }

    @Test
    public void fillCurrentAddressFieldTest() {
        ((TextBoxPage) page).fillCurrentAddressTextbox(testCurrentAddress);
        ((TextBoxPage) page).submitButton();
        Assert.assertTrue(((TextBoxPage) page).getCurrentAddress().contains(testCurrentAddress));
    }

    @Test
    public void fillPermanentAddressFieldTest() {
        ((TextBoxPage) page).fillPermanentAddressTextbox(testPermanentAddress);
        ((TextBoxPage) page).submitButton();
        logger.error(((TextBoxPage) page).getPermanentAddress());
        Assert.assertTrue(((TextBoxPage) page).getPermanentAddress().contains(testPermanentAddress));
    }

}
