package org.example.pages;

import lombok.Getter;
import org.example.logger.LocalLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class TextBoxPage extends WebPage {
    private static final LocalLogger logger = LocalLogger.getLogger(TextBoxPage.class);
    private static final String PAGE_PATH = "text-box";

    @FindBy(id = "userName")
    private WebElement nameTextbox;

    @FindBy(id = "userEmail")
    private WebElement emailTextbox;

    @FindBy(id = "currentAddress")
    private WebElement currentAddressTextbox;

    @FindBy(id = "permanentAddress")
    private WebElement permanentAddressTextbox;

    @FindBy(id = "submit")
    private WebElement submitBtn;

    @FindBy(id = "name")
    private WebElement nameParagraph;

    @FindBy(id = "email")
    private WebElement emailParagraph;

    @FindBy(id = "currentAddress")
    private WebElement currentAddressParagraph;

    @FindBy(id = "permanentAddress")
    private WebElement permanentAddressParagraph;

    public TextBoxPage(WebDriver webDriver) {
        super(PAGE_PATH, webDriver, TextBoxPage.class);
    }

    public void submitButton() {
        click(getSubmitBtn());
    }

    public String getName() {
        return getNameParagraph().getText();
    }

    public String getEmail() {
        return getEmailParagraph().getText();
    }

    public String getCurrentAddress() {
        return getCurrentAddressParagraph().getAttribute("value");
    }

    public String getPermanentAddress() {
        return getPermanentAddressParagraph().getAttribute("value");
    }

    public void fillNameTextbox(String name) {
        fillTextbox(getNameTextbox(), name);
    }

    public void fillEmailTextbox(String email) {
        fillTextbox(getEmailTextbox(), email);
    }

    public void fillCurrentAddressTextbox(String currentAddress) {
        fillTextbox(getCurrentAddressTextbox(), currentAddress);
    }

    public void fillPermanentAddressTextbox(String permanentAddress) {
        fillTextbox(getPermanentAddressTextbox(), permanentAddress);
    }
}
