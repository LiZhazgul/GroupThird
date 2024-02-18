package uiTest;

import driver.Driver;
import helper.BrowserManager;
import helper.WebElementHelper;
import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AddUserTypePage;
import pages.LoginPage;
import tables.TableRolesReader;

import java.util.ArrayList;
import java.util.List;

public class BaseTest {

    protected WebDriver driver;
    protected WebElementHelper webElementHelper;
    protected LoginPage loginPage;
    protected BrowserManager browserManager;
    protected String domain;
    protected String userTypeName;
    protected AddUserTypePage addUserTypePage;
    protected List<TableRolesReader> roles;
    protected TableRolesReader role;

    @BeforeClass(alwaysRun = true, description = "Launch browser and log in on talentLMS website")
    public void setUp() {
        driver = Driver.getDriver();
        browserManager = new BrowserManager(driver);
        webElementHelper = new WebElementHelper();
        loginPage = new LoginPage();
        addUserTypePage = new AddUserTypePage();

        roles = new ArrayList<>();
        role = new TableRolesReader();

        domain = "timka";
        userTypeName = "prob";
        role.setNameTypes(userTypeName);

        browserManager.openByNavigate("https://app.talentlms.com/login");
        /*loginPage.enterDomain("fall2023")
                .enterUsername("nurik9816")
                .enterPassword("qwerty12345")
                .clickLoginButton();*/

        loginPage.enterDomain(domain)
                .enterUsername("timka555-player@mail.ru")
                .enterPassword("1Test8")
                .clickLoginButton();
    }

    @Test (priority = 1)
    @Feature("talentLMS Home Page")
    @Description("Checking authorization successful")
    @Owner("Timur")
    @Severity(SeverityLevel.CRITICAL)
    @Story("TL-014")
    @Tag("Smoke")
    public void websiteLoginTest(){
        String actualTitle = driver.findElement(By.xpath("//div[@class='tl-title tl-ellipsis']")).getText().trim();
        String expectedTitle = "Home";

        Assert.assertEquals(expectedTitle, actualTitle);
    }

    @Test (priority = 2)
    @Feature("talentLMS User Types")
    @Description("Checking that the link to the desired menu works")
    @Owner("Timur")
    @Severity(SeverityLevel.CRITICAL)
    @Story("TL-014")
    @Tag("Smoke")
    public void goToPageUserTypesTest(){
        WebElement textLinkUserTypes = driver.findElement(By.xpath("//div[@class='tl-bold-link']/a[@href='https://" + domain + ".talentlms.com/acl/index/gridPref:reset']"));
        webElementHelper.click(textLinkUserTypes);

        String actualTitle = driver.findElement(By.xpath("//div[@class='tl-title tl-ellipsis']")).getText().trim();
        String expectedTitle = "Home / User types";

        Assert.assertEquals(expectedTitle, actualTitle);
    }

    @Test(priority = 3)
    @Feature("talentLMS User Types")
    @Description("Checking the upload of data from the user roles table")
    @Owner("Timur")
    @Severity(SeverityLevel.CRITICAL)
    @Story("TL-014")
    @Tag("Smoke")
    public void outDataTable(){
        roles = TableRolesReader.getRolesFromTable(driver);

        Assert.assertEquals(roles.contains(role), false);
    }

    @Test(priority = 4)
    @Feature("talentLMS User Types")
    @Description("Adding a new role to a table")
    @Owner("Timur")
    @Severity(SeverityLevel.CRITICAL)
    @Story("TL-014")
    @Tag("Smoke")
    public void addRoleUserInTableTest(){
        int indexUserType = 2;

        webElementHelper.click(addUserTypePage.addUserTypeBtn)
                        .sendKeys(addUserTypePage.nameUserTypeField, userTypeName);

        addUserTypePage.randomChoiceUserTypes(indexUserType)
                       .choosePermission()
                       .saveBtn.click();

        roles = TableRolesReader.getRolesFromTable(driver);

        Assert.assertEquals(roles.contains(role), true);
    }

    @Test(priority = 9)
    @Feature("talentLMS User Types")
    @Description("Checking the table information search string")
    @Owner("Timur")
    @Severity(SeverityLevel.NORMAL)
    @Story("TL-014")
    @Tag("Smoke")
    public void searchFieldTest() throws InterruptedException {
        WebElement searchField = driver.findElement(By.xpath("//input[@class='tl-grid-search-input']"));
        int amountElements = 0;

        String searchWord = "tr";

        webElementHelper.sendKeys(searchField, searchWord);

        Thread.sleep(1000);

        roles = TableRolesReader.getRolesFromTable(driver);

        for(int i = 0; i < roles.size(); i++){
            if(roles.get(i).getNameTypes().toLowerCase().contains(searchWord.toLowerCase())){
                amountElements++;
            }
        }

        searchField.clear();
        searchField.sendKeys(Keys.ENTER);

        Assert.assertEquals(amountElements == roles.size(), true);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
      //  Driver.closeDriver();
    }
}
