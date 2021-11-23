package org.example.pages;

import com.google.common.collect.Maps;
import com.google.common.collect.Streams;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
public class CheckBoxPage extends WebPage {
    private static String PATH_URL = "checkbox";

    @FindAll(@FindBy(xpath = "//li[@class='rct-node rct-node-leaf']//label"))
    private List<WebElement> checkboxes;

    @FindAll(@FindBy(className = "text-success"))
    private List<WebElement> selectedOptions;

    @FindBy(xpath = "//button[@aria-label='Expand all']")
    private WebElement expandButton;

    @FindBy(xpath = "//button[@aria-label='Collapse all']")
    private WebElement collapseButton;

    @FindAll(@FindBy(xpath = "//li[@class='rct-node rct-node-leaf']//label//span[@class='rct-title']"))
    private List<WebElement> checkboxTitles;

    public CheckBoxPage(WebDriver webDriver) {
        super(PATH_URL, webDriver, CheckBoxPage.class);
    }

    public List<String> getSelectedOptionsString() {
        List<String> selectedOptions = getSelectedOptions()
                .stream().map(WebElement::getText)
                .collect(Collectors.toList());
        log(String.format("Got %d elements", selectedOptions.size()));
        return selectedOptions;
    }

    public Map<WebElement, WebElement> getCheckBoxMap() {
        return Streams.zip(
                        getCheckboxes().stream(), getCheckboxTitles().stream(), Maps::immutableEntry)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
