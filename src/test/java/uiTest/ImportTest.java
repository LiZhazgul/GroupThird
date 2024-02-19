package uiTest;

import helper.WebElementHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ImportPage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*Thread.sleep added to see visually how tests work.*/
public class ImportTest extends BaseTest {
    ImportPage importPage = new ImportPage();

    /* in driver.get("https://ezelfall2023.talentlms.com/dashboard/welcome");  use your parameters */

    @Test
    void enterImportByImportReff() throws InterruptedException {
        driver.get("https://ezelfall2023.talentlms.com/dashboard/welcome");
        driver.findElement(By.xpath("//div[@class='tl-onboarding-end-item_content'][contains(., 'Administrator dashboard')]")).click();
        // driver.get("https://www.talentlms.com/dashboard");
        Thread.sleep(5000);
        importPage.enterImportPageByRef();
        Thread.sleep(3000);
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("talentlms.com/import"), "URL не содержит talentlms.com/import");
    }

    @Test
    void SampleFileDownload() throws InterruptedException {
        driver.get("https://ezelfall2023.talentlms.com/dashboard/welcome");
        driver.findElement(By.xpath("//div[@class='tl-onboarding-end-item_content'][contains(., 'Administrator dashboard')]")).click();
        driver.get("https://ezelfall2023.talentlms.com/import");
        importPage.clickSampleExcelFile(driver);
        Thread.sleep(5000);
        String downloadsPath = System.getProperty("user.home") + "/Downloads";
        String filePath = downloadsPath + "/Import-Samples.xls";
        Path path = Paths.get(filePath);
        boolean fileExists = Files.exists(path);
        Assert.assertTrue(fileExists, "Файл не был скачан успешно");
    }

    /*Use your own filePath of the file downloaded from site.(SampleFileDownload()) */
    @Test
    void testFileUploadPositive() throws InterruptedException {

        driver.get("https://ezelfall2023.talentlms.com/dashboard/welcome");
        driver.findElement(By.xpath("//div[@class='tl-onboarding-end-item_content'][contains(., 'Administrator dashboard')]")).click();
        driver.get("https://ezelfall2023.talentlms.com/import");
        importPage.downloadFile(driver, "C:\\Users\\Hp\\Import-Samples (11).xls");
        Thread.sleep(5000);
        WebElement successMessage = driver.findElement(By.xpath("//div[@class='note success text-success' and contains(text(), 'Upload complete. Click the import button to proceed.')]"));
        Assert.assertNotNull(successMessage);
    }

    /*Use your own filePath of the file downloaded from site.(SampleFileDownload()) */
    @Test
    void testFileUploadAndSubmitPositive() throws InterruptedException {

        driver.get("https://ezelfall2023.talentlms.com/dashboard/welcome");
        driver.findElement(By.xpath("//div[@class='tl-onboarding-end-item_content'][contains(., 'Administrator dashboard')]")).click();
        driver.get("https://ezelfall2023.talentlms.com/import");
        importPage.downloadFile(driver, "C:\\Users\\Hp\\Import-Samples (11).xls");
        Thread.sleep(5000);
        WebElement successMessage = driver.findElement(By.xpath("//div[@class='note success text-success' and contains(text(), 'Upload complete. Click the import button to proceed.')]"));
        Assert.assertNotNull(successMessage);
        System.out.println(importPage.importDescriptionInput.getText());
        WebElement modifyData = importPage.importDescriptionInput;
        Assert.assertFalse(modifyData.getText().isEmpty());
        importPage.importButton.click();
        WebElementHelper importResults = new WebElementHelper().waitForElementToBeDisplayed(importPage.importResults);
        Assert.assertNotNull(importResults);

    }

    @Test
    void testViewCheatsheet() throws InterruptedException {
        driver.get("https://ezelfall2023.talentlms.com/dashboard/welcome");
        driver.findElement(By.xpath("//div[@class='tl-onboarding-end-item_content'][contains(., 'Administrator dashboard')]")).click();
        //driver.get("https://ezelfall2023.talentlms.com/import");
        importPage.enterImportPageByRef();
        Thread.sleep(3000);
        importPage.clickViewCheatsheetButton();
        Thread.sleep(3000);
        webElementHelper.waitForElementToBeDisplayed(importPage.useThisExample);
        Assert.assertTrue(importPage.useThisExample.isDisplayed());
        Thread.sleep(5000);
        System.out.println(importPage.importDescriptionInput.getText());
        WebElement modifyData = importPage.importDescriptionInput;
        Assert.assertTrue(modifyData.getText().contains("Login; Firstname; Lastname; Email"));
        Thread.sleep(5000);
    }
}

