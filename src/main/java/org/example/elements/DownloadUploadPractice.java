package org.example.elements;

import org.example.pages.DownloadUploadPage;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class DownloadUploadPractice extends WebPageTest {
    private final String FILE_LOCATOR = "/home/vladz/Downloads/sampleFile.jpeg";

    //TODO: Move to Page
    private FluentWait<String> waitForFile = new FluentWait(FILE_LOCATOR)
            .withTimeout(Duration.ofSeconds(1))
            .pollingEvery(Duration.ofMillis(100));

    public DownloadUploadPractice() {
        super(DownloadUploadPage.class);
    }

    @Test
    public void downloadFileTest() {
        File file = new File(FILE_LOCATOR);
        if (file.exists()) file.delete();

        ((DownloadUploadPage) page).downloadFile();

        File downloadedFile = waitForFile.until((o) -> {
            File fileToFind = new File(FILE_LOCATOR);
            log("waiting....");
            return fileToFind.exists() ? fileToFind : null;
        });
        Assert.assertTrue(downloadedFile.exists());
    }

    @Test
    public void uploadFileTest() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(((DownloadUploadPage) page).getUploadLink()));

        ((DownloadUploadPage) page).uploadFile(FILE_LOCATOR);
        Assert.assertNotNull(((DownloadUploadPage) page).getUploadMessageText());
    }

}
