package org.example.pages;

import lombok.Getter;
import org.example.logger.LocalLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class RadioButtonPage extends WebPage {
    private static final LocalLogger logger = LocalLogger.getLogger(RadioButtonPage.class);
    private static String PAGE_PATH = "radio-button";

    @FindBy(xpath = "//label[@for='yesRadio']")
    private WebElement btnYes;

    @FindBy(xpath = "//label[@for='impressiveRadio']")
    private WebElement btnImpressive;

    @FindBy(xpath = "//label[@for='noRadio']")
    private WebElement btnNo;

    @FindBy(xpath = "//span[@class='text-success']")
    private WebElement successMessage;

    public RadioButtonPage(WebDriver webDriver) {
        super(PAGE_PATH, webDriver, RadioButtonPage.class);
    }

    public String getRadioBtnYesText() {
        return getBtnYes().getText();
    }

    public String getRadioBtnImpressiveText() {
        return getBtnImpressive().getText();
    }

    public String getTextSuccessText() {
        return getSuccessMessage().getText();
    }

    public boolean isRadioBtnNoEnabled() {
        return getBtnNo().getAttribute("class").contains("disabled");
    }

    public void clickYesBtn() {
        getBtnYes().click();
    }

    public void clickImpressiveBtn() {
        getBtnImpressive().click();
    }
}
