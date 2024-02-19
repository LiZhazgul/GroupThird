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
import pages.UserTypePage;
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
    protected UserTypePage userTypePage;
    protected List<TableRolesReader> roles;
    protected TableRolesReader role;

    @BeforeClass(alwaysRun = true, description = "Launch browser and log in on talentLMS website")
    public void setUp() {
        driver = Driver.getDriver();
        browserManager = new BrowserManager(driver);
        webElementHelper = new WebElementHelper();
        loginPage = new LoginPage();
        userTypePage = new UserTypePage();

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

        loginPage.enterDomain("timka")
                .enterUsername("timka555-player@mail.ru")
                .enterPassword("1Test8")
                .clickLoginButton();
    }

    @Feature("talentLMS User Types")
    @Description("Adding a new role to a table")
    @Owner("Timur")
    @Severity(SeverityLevel.CRITICAL)
    @Story("TL-014")
    @Tag("Smoke")
    public void addRoleUserInTableTest(){
        int indexUserType = 2;

        webElementHelper.click(userTypePage.addUserTypeBtn)
                        .sendKeys(userTypePage.nameUserTypeField, userTypeName);

        userTypePage.randomChoiceUserTypes(indexUserType)
                       .choosePermission()
                       .saveBtn.click();

        roles = TableRolesReader.getRolesFromTable(driver);

        Assert.assertEquals(roles.contains(role), true);
    }

    @Feature("talentLMS User Types")
    @Description("Checking the table information search string")
    @Owner("Timur")
    @Severity(SeverityLevel.NORMAL)
    @Story("TL-014")
    @Tag("Smoke")
    public void searchFieldTest() throws InterruptedException {
        int amountElements = 0;

        String searchWord = "tr";

        webElementHelper.sendKeys(userTypePage.searchField, searchWord);

        Thread.sleep(1000);

        roles = TableRolesReader.getRolesFromTable(driver);

        for(int i = 0; i < roles.size(); i++){
            if(roles.get(i).getNameTypes().toLowerCase().contains(searchWord.toLowerCase())){
                amountElements++;
            }
        }

        userTypePage.searchField.clear();
        userTypePage.searchField.sendKeys(Keys.ENTER);

        Assert.assertEquals(amountElements == roles.size(), true);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
      //  Driver.closeDriver();
    }
}
