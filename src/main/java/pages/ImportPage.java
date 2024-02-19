package com.fall23.ui.homeworks.talentLms;

import com.fall23.ui.drivers.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

public class ImportPage extends BasePage {


    @FindBy(xpath = "//div[@class='xc ym tl-main-signup-input']//input[@id='587881655' and @name='domain']")
    public WebElement domainInput;

    @FindBy(xpath = "//div[@class='controls controls-demo tl-main-signup-input']//input[@id='1105878299' and @name='login']")
    public WebElement nameInput;

    @FindBy(xpath = "//div[@class='controls controls-demo tl-main-signup-input']//input[@id='863740078' and @name='password']")
    public WebElement passwordInput;
    @FindBy(xpath = "//input[@id='1719397324' and @name='submit']")
    public WebElement loginBtn;

    @FindBy(xpath = "//a[contains(text(), 'import')]")
    public WebElement importLink;

    @FindBy(xpath = "//a[contains(text(), 'export')]")
    public WebElement exportLink;

    @FindBy(xpath = "//i[@class='icon-refresh tl-icon-stack-content']")
    public WebElement importExportIcon;

    @FindBy(xpath = "//a[@class='tl-tool-tip inputbtn']/i[@class='icon-upload-cloud icon-grid']")
    public WebElement dropCSVExcelIcon;

    @FindBy(xpath = "//div[@class='CodeMirror-lines']")
    public WebElement importDescriptionInput;

    @FindBy(xpath = "//input[@value='Import']")
    public WebElement importButton;

    @FindBy(xpath = "//i[@class='icon-download tl-icon-stack-content']")
    public WebElement sampleExcelFileIcon;

    @FindBy(xpath = "//i[@class='icon-book-spells tl-icon-stack-content']")
    public WebElement viewCheatsheetIcon;

    @FindBy(xpath = "//*[@id='tl-toggle-sample-data']/div/div/a")
    public WebElement viewCheatsheetLink;


    public WebDriver driver;


    public ImportPage enterImportPageByIcon(){
        webElementHelper.click(importExportIcon);
        return this;

    }

    public ImportPage enterImportPageByRef(){
        importLink.click();
        return this;
    }
    public ImportPage clickDropCSVExcelIcon(String filePath) {
        dropCSVExcelIcon.click();
        // Ожидание появления элемента для выбора файла
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement fileInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='tl-tool-tip inputbtn'  and @id='fileupload_input']")));
        // Отправка пути к файлу в элемент ввода
        fileInput.sendKeys(filePath);
        // Ожидание окончания загрузки
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='uploading-indicator']")));
        return this;
    }






}
