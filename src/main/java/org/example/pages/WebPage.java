package org.example.pages;

import org.example.logger.LocalLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;


public abstract class WebPage {
    private LocalLogger logger;
    private String GLOBAL_PAGE_URL = "https://demoqa.com/";
    protected WebDriver webDriver;

    public WebPage(String PATH_URL, WebDriver webDriver, Class<? extends WebPage> clazz) {
        this.GLOBAL_PAGE_URL += PATH_URL;
        this.webDriver = webDriver;
        logger = LocalLogger.getLogger(clazz);

        navigateTo();
        PageFactory.initElements(webDriver, this);
    }

    public void navigateTo() {
        webDriver.get(GLOBAL_PAGE_URL);
    }

    protected void fillTextbox(WebElement textbox, String text) {
        textbox.sendKeys(text);
    }

    protected void log(String text) {
        logger.error(text);
    }

    public void click(WebElement element) {
        element.click();
    }

    protected void performDoubleClick(WebElement element) {
        new Actions(webDriver)
                .doubleClick(element)
                .perform();
    }

    protected void performRightClick(WebElement element) {
        new Actions(webDriver)
                .contextClick(element)
                .perform();
    }

    protected void performClick(WebElement element) {
        new Actions(webDriver)
                .click(element)
                .perform();
    }
}
