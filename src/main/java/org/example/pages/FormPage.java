package org.example.pages;

import lombok.Getter;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

@Getter
public class FormPage extends WebPage {
    private static String PATH_URL = "automation-practice-form";

    private static Logger loggerTest = Logger.getLogger(FormPage.class);

    private final HashMap<ELEMENTS, String> selectedValues = new HashMap<>();

    @FindBy(id = "firstName")
    private WebElement firstName;

    @FindBy(id = "lastName")
    private WebElement lastName;

    @FindBy(id = "userEmail")
    private WebElement email;

    @FindAll({
            @FindBy(xpath = "//label[@for='gender-radio-1']"),
            @FindBy(xpath = "//label[@for='gender-radio-2']"),
            @FindBy(xpath = "//label[@for='gender-radio-3']")
    })
    private List<WebElement> genders;

    @FindBy(id = "userNumber")
    private WebElement phone;

    @FindBy(id = "dateOfBirthInput")
    private WebElement birth;

    @FindBy(xpath = "//div[contains(@class, 'subjects-auto-complete__control')]//div")
    private WebElement subjects;

    @FindAll({
            @FindBy(xpath = "//label[@for='hobbies-checkbox-1']"),
            @FindBy(xpath = "//label[@for='hobbies-checkbox-2']"),
            @FindBy(xpath = "//label[@for='hobbies-checkbox-3']")
    })
    private List<WebElement> hobbies;

    @FindBy(className = "form-control-file")
    private WebElement image;

    @FindBy(id = "currentAddress")
    private WebElement address;

    @FindBy(id = "state")
    private WebElement state;

    @FindBy(xpath = "//div[@id='state']//div//div//div")
    private WebElement stateText;

    @FindBy(id = "city")
    private WebElement city;

    @FindBy(xpath = "//div[@id='city']//div//div//div")
    private WebElement cityText;

    @FindBy(id = "submit")
    private WebElement submit;

    @FindAll(
            @FindBy(xpath = "//table[contains(@class,'table')]//tbody//tr//td"))
    private List<WebElement> values;

    public enum ELEMENTS {
        FIRST_NAME, LAST_NAME, NAME, EMAIL, GENDER, MOBILE, BIRTH,
        SUBJECTS, HOBBIES, PICTURE, ADDRESS, STATE, CITY;

        @Getter
        private WebElement element;
    }

    public FormPage(WebDriver webDriver) {
        super(PATH_URL, webDriver, FormPage.class);

        ELEMENTS.FIRST_NAME.element = firstName;
        ELEMENTS.LAST_NAME.element = lastName;
        ELEMENTS.EMAIL.element = email;
        ELEMENTS.MOBILE.element = phone;
        ELEMENTS.BIRTH.element = birth;
        ELEMENTS.SUBJECTS.element = subjects;
        ELEMENTS.PICTURE.element = image;
        ELEMENTS.ADDRESS.element = address;
        ELEMENTS.STATE.element = state;
        ELEMENTS.CITY.element = city;
    }


    //TODO: Move to test
    public void validate() {
        for (int i = 0; i < values.size(); i += 2) {
            String label = values.get(i).getText().toLowerCase();
            String value = values.get(i + 1).getText();
            String foundedValue = "";

            switch (label) {
                case "student name":
                    foundedValue = selectedValues.get(ELEMENTS.NAME);
                    break;
                case "state and city":
                    foundedValue = String.format("%s %s", selectedValues.get(ELEMENTS.STATE), selectedValues.get(ELEMENTS.CITY));
                    break;
                case "date of birth" :
                    value = "" + value.substring(0, 6) + " " + value.substring(value.length() - 4);
                    foundedValue = selectedValues.get(ELEMENTS.BIRTH);
                    break;
                case "picture":
                    String s = selectedValues.get(ELEMENTS.PICTURE);
                    foundedValue = s.substring(s.length() - value.length());
                    break;

                default:
                    ELEMENTS defaultElement = Arrays.stream(ELEMENTS.values())
                            .filter(e -> e.toString().toLowerCase().contains(label) || label.contains(e.toString().toLowerCase()))
                            .findFirst()
                            .get();
                    foundedValue = selectedValues.get(defaultElement);
            }

            loggerTest.debug(String.format("%s -> %s", value, foundedValue));
//            Logger.getLogger(FormPage.class).debug(String.format("%s -> %s", value, foundedValue));
            Assert.assertEquals(value, foundedValue);
        }
    }


    public void fillTexbox(ELEMENTS element, String text) {
        element.getElement().clear();
        element.getElement().sendKeys(text);
        selectedValues.put(element, text);

        if (element == ELEMENTS.FIRST_NAME)
            selectedValues.put(ELEMENTS.NAME, text);
        else if (element == ELEMENTS.LAST_NAME)
            if (selectedValues.containsKey(ELEMENTS.NAME))
                selectedValues.put(ELEMENTS.NAME, String.format("%s %s", selectedValues.get(ELEMENTS.NAME), text));
            else
                selectedValues.put(ELEMENTS.NAME, text);
    }

    public void setDate(String date) {
        new Actions(webDriver)
                .moveToElement(getBirth())
                .click()
                .keyDown(Keys.CONTROL)
                .keyDown(Keys.SHIFT)
                .sendKeys(Keys.chord(Keys.ARROW_LEFT, Keys.ARROW_LEFT, Keys.ARROW_LEFT))
                .keyUp(Keys.SHIFT)
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.chord(Keys.CONTROL, date, Keys.ENTER))
                .perform();
        selectedValues.put(ELEMENTS.BIRTH, date);
    }

    public void selectDropDownOption(String opt) {
//        getSubjects().sendKeys(opt);
        new Actions(webDriver)
                .sendKeys(getSubjects(), opt)
                .pause(Duration.ofMillis(100))
                .sendKeys(Keys.ENTER)
                .perform();

        if (selectedValues.containsKey(ELEMENTS.SUBJECTS)) {
            selectedValues.put(ELEMENTS.SUBJECTS,
                    String.format("%s, %s", selectedValues.get(ELEMENTS.SUBJECTS), opt));
        } else {
            selectedValues.put(ELEMENTS.SUBJECTS, opt);
        }
    }

    public void uploadImage(String imagePath) {
        getImage().sendKeys(imagePath);
        selectedValues.put(ELEMENTS.PICTURE, imagePath);
    }

    public void dropboxSelect(ELEMENTS element, int n) {
        element.getElement().click();
        Action arrowDown = new Actions(webDriver)
                .sendKeys(Keys.ARROW_DOWN)
                .build();

        IntStream.range(0, n).forEach(i -> arrowDown.perform());
        new Actions(webDriver)
                .sendKeys(Keys.ENTER)
                .perform();

        if (element == ELEMENTS.STATE) {
            selectedValues.put(ELEMENTS.STATE, stateText.getText());
        } else if (element == ELEMENTS.CITY) {
            selectedValues.put(ELEMENTS.CITY, cityText.getText());
        }
    }

    public void checkElement(WebElement element, ELEMENTS type) {
        element.click();

        //Check if exists already hobbies or gender
        if (type != ELEMENTS.GENDER && selectedValues.containsKey(type)) {
            selectedValues.put(ELEMENTS.HOBBIES, String.format("%s, %s", selectedValues.get(type), element.getText()));
        } else {
            selectedValues.put(type, element.getText());
        }
    }
}
