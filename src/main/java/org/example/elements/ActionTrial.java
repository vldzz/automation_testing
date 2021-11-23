package org.example.elements;

import org.example.pages.ActionPage;
import org.junit.Assert;
import org.junit.Test;

public class ActionTrial extends WebPageTest{
    public ActionTrial() {
        super(ActionPage.class);
    }

    @Test
    public void performClicks() {
        ((ActionPage) page).performClick();
        Assert.assertEquals(((ActionPage) page).getJustClickMessage().getText(), "You have done a dynamic click");
    }

    @Test
    public void performRightClick() {
        ((ActionPage) page).performRightClick();
        Assert.assertEquals(((ActionPage) page).getRightClickMessage().getText(), "You have done a right click");
    }

    @Test
    public void performDoubleClick() {
        ((ActionPage) page).performDoubleClick();
        Assert.assertEquals(((ActionPage) page).getDoubleClickMessage().getText(), "You have done a double click");
    }
}
