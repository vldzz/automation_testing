package org.example.elements;

import org.example.pages.FormPage;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class FormTrial extends WebPageTest  {
    private static final String IMAGE_TO_UPLOAD_PATH = "/home/vladz/IdeaProjects/Automation_Testing/src/main/resources/sampleFile.jpeg";
    public FormTrial() {
        super(FormPage.class);
    }

    @Test
    public void positiveFormFillTest() {
        fillForm();
    }

    private void fillForm() {
        FormPage page = (FormPage) super.page;

        page.fillTexbox(FormPage.ELEMENTS.FIRST_NAME, RandomResources.getName());
        page.fillTexbox(FormPage.ELEMENTS.LAST_NAME, RandomResources.getLastName());
        page.fillTexbox(FormPage.ELEMENTS.EMAIL, RandomResources.getEmail());
        page.checkElement(
                RandomResources.getRandomWebElement(page.getGenders()), FormPage.ELEMENTS.GENDER);
        page.fillTexbox(FormPage.ELEMENTS.MOBILE, RandomResources.getPhone());
        page.setDate(RandomResources.getDate());

        page.selectDropDownOption("Maths");
        page.selectDropDownOption("Arts");
        page.selectDropDownOption("Social Studies");

        checkRandomHobbies();
        page.uploadImage(IMAGE_TO_UPLOAD_PATH);
        page.fillTexbox(FormPage.ELEMENTS.ADDRESS, RandomResources.getAddress());

        page.dropboxSelect(FormPage.ELEMENTS.STATE, RandomResources.nextInt(1, 10));
        page.dropboxSelect(FormPage.ELEMENTS.CITY, RandomResources.nextInt(1, 10));

        page.click(page.getSubmit());

        page.validate();
    }

    private void checkRandomHobbies() {
        ((FormPage) page).getHobbies().forEach(checkbox -> {
            if (RandomResources.nextInt(0, 10) % 2 == 0) {
                ((FormPage) page).checkElement(checkbox, FormPage.ELEMENTS.HOBBIES);
            }
        });
    }
}



class RandomResources {
    private static final Random random = new Random();
    private static final String[] names = {"Vlad", "Alex"};
    private static final String[] lastNames = {"Stici", "Bat"};
    private static final String[] emails = {"e@gmail.com"};
    private static final String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    private static final String[] addresses = {"address 1", "address 2"};

    public static String getName() {
        return getRandValue(names);
    }

    public static String getLastName() {
        return getRandValue(lastNames);
    }

    public static String getEmail() {
        return getRandValue(emails);
    }

    public static String getPhone() {
        return "" + nextInt(10_000, 99_999) + nextInt(10_000, 99_999);
    }

    public static String getAddress() {
        return getRandValue(addresses);
    }

    public static String getDate() {
        int day = nextInt(1, 28);
        return String.format("%s %s %d",
                (day > 9) ? day : "0" + day,
                getRandValue(months),
                nextInt(1960, 2010));
    }

    public static WebElement getRandomWebElement(List<WebElement> elements) {
        return elements.get(nextInt(0, elements.size() - 1));
    }

    private static String getRandValue(String[] values) {
        return values[nextInt(0, values.length - 1)];
    }

    public static int nextInt(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }
}
