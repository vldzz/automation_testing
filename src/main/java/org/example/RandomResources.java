package org.example;

import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RandomResources {
    private static final Random random = new Random();
    private static final String[] names = {"Vlad", "Alex", "Stas"};
    private static final String[] lastNames = {"Stici", "Bat", "Crozu"};
    private static final String[] emails = {"e@gmail.com", "e2@gmail.com", "e3@gmail.com"};
    private static final String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    private static final String[] addresses = {"address 1", "address 2", "address 3"};
    private List<String> subjects = Arrays.asList("Accounting", "Arts", "Maths", "Social Studies", "Biology", "Chemistry", "Physics", "Computer Science", "Economics", "Civics", "English");



    public String getName() {
        return getRandValue(names);
    }

    public String getLastName() {
        return getRandValue(lastNames);
    }

    public String getEmail() {
        return getRandValue(emails);
    }

    public String getPhone() {
        return "" + nextInt(10_000, 99_999) + nextInt(10_000, 99_999);
    }

    public String getAddress() {
        return getRandValue(addresses);
    }

    public String getSubject() {
        String randValue = getRandValue(subjects.toArray());
        subjects = subjects.stream()
                .filter(subject -> !subject.equals(randValue))
                .collect(Collectors.toList());
        return randValue;
    }

    public String getDate() {
        int day = nextInt(1, 28);
        return String.format("%s %s %d",
                (day > 9) ? day : "0" + day,
                getRandValue(months),
                nextInt(1960, 2010));
    }

    public static WebElement getRandomWebElement(List<WebElement> elements) {
        return elements.get(nextInt(0, elements.size() - 1));
    }

    private static String getRandValue(Object[] values) {
        return (String) values[nextInt(0, values.length - 1)];
    }

    public static int nextInt(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }
}
