package org.example.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class ActionPage extends WebPage {
    private static String PATH_URL = "buttons";
    private Wait waiter;

    @FindBy(id = "doubleClickBtn")
    private WebElement doubleClickBtn;

    @FindBy(id = "doubleClickMessage")
    private WebElement doubleClickMessage;

    @FindBy(id = "rightClickBtn")
    private WebElement rightClickBtn;

    @FindBy(id = "rightClickMessage")
    private WebElement rightClickMessage;

    @FindBy(xpath = "//button[text()='Click Me']")
    private WebElement justClickBtn;

    @FindBy(id = "dynamicClickMessage")
    private WebElement justClickMessage;

    public ActionPage(WebDriver webDriver) {
        super(PATH_URL, webDriver, ActionPage.class);
        this.waiter = new WebDriverWait(webDriver, Duration.ofSeconds(5));
    }

    public void performDoubleClick() {
        performDoubleClick(getDoubleClickBtn());
        waiter.until(ExpectedConditions.visibilityOf(getDoubleClickMessage()));
    }

    public void performRightClick() {
        performRightClick(getRightClickBtn());
        waiter.until(ExpectedConditions.visibilityOf(getRightClickMessage()));
    }

    public void performClick() {
        performClick(getJustClickBtn());
        waiter.until(ExpectedConditions.visibilityOf(getJustClickMessage()));
    }
}
