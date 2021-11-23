package org.example.pages;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
@Setter
public class DownloadUploadPage extends WebPage {
    private static String PAGE_URL = "upload-download";

    @FindBy(id = "downloadButton")
    private WebElement downloadLink;

    @FindBy(id = "uploadFile")
    private WebElement uploadLink;

    @FindBy(id = "uploadedFilePath")
    private WebElement uploadMessage;

    public DownloadUploadPage(WebDriver webDriver) {
        super(PAGE_URL, webDriver, DownloadUploadPage.class);
    }

    public void downloadFile() {
        getDownloadLink().click();
        log("Download button clicked");
    }

    public void uploadFile(String location) {
        getUploadLink().sendKeys(location);
        log("File uploaded to location " + location);
    }

    public String getUploadMessageText() {
        String uploadMessage = getUploadMessage().getText();
        log("Got file path: " + uploadMessage);
        return uploadMessage;
    }
}
