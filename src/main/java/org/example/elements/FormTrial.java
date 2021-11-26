package org.example.elements;

import org.apache.log4j.Logger;
import org.example.RandomResources;
import org.example.pages.FormPage;
import org.junit.Test;


public class FormTrial extends WebPageTest  {
    private static final String IMAGE_TO_UPLOAD_PATH = "/home/vladz/IdeaProjects/Automation_Testing/src/main/resources/sampleFile.jpeg";

    public FormTrial() {
        super(FormPage.class);
    }

    @Test
    public void positiveFormFillTest1() {
        fillForm();
    }

    @Test
    public void positiveFormFillTest2() {
        fillForm();
    }

    @Test
    public void positiveFormFillTest3() {
        fillForm();
    }

    private void fillForm() {
        FormPage page = (FormPage) super.page;
        RandomResources randomResources = new RandomResources();

        page.fillTexbox(FormPage.ELEMENTS.FIRST_NAME, randomResources.getName());
        page.fillTexbox(FormPage.ELEMENTS.LAST_NAME, randomResources.getLastName());
        page.fillTexbox(FormPage.ELEMENTS.EMAIL, randomResources.getEmail());
        page.checkElement(
                RandomResources.getRandomWebElement(page.getGenders()), FormPage.ELEMENTS.GENDER);
        page.fillTexbox(FormPage.ELEMENTS.MOBILE, randomResources.getPhone());
        page.setDate(randomResources.getDate());

        page.selectDropDownOption(randomResources.getSubject());
        page.selectDropDownOption(randomResources.getSubject());
        page.selectDropDownOption(randomResources.getSubject());

        checkRandomHobbies();
        page.uploadImage(IMAGE_TO_UPLOAD_PATH);
        page.fillTexbox(FormPage.ELEMENTS.ADDRESS, randomResources.getAddress());

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




