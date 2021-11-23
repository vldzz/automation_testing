package org.example.elements;

import org.example.pages.CheckBoxPage;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CheckBoxTrial extends WebPageTest {
    private final Random random = new Random();

    public CheckBoxTrial() {
        super(CheckBoxPage.class);
    }

    @Test
    public void checkTest1() {
        checkTest();
    }

    @Test
    public void checkTest2() {
        checkTest();
    }

    @Test
    public void checkTest3() {
        checkTest();
    }


    public void checkTest() {
        ArrayList<String> checkedOptions = new ArrayList<>();

        page.click(((CheckBoxPage) page).getExpandButton());

        //TODO: Optimize this method
        ((CheckBoxPage) page).getCheckBoxMap().forEach((checkbox, title) -> {
            if (getRandomBool() && checkbox.isEnabled()) {
                checkbox.click();
                checkedOptions.add(title.getText().toLowerCase()
                        .replace("excel file.doc", "excelFile")
                        .replace("word file.doc", "wordFile"));
            }
        });

        List<String> selectedOptions = ((CheckBoxPage) page).getSelectedOptionsString();
        for (String checkedOption : checkedOptions) {
            Assert.assertTrue(selectedOptions.contains(checkedOption));
        }
    }

    private boolean getRandomBool() {
        return random.nextInt() % 2 == 0;
    }
}
