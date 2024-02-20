package uiTest;

import driver.Driver;
import helper.BrowserManager;
import helper.WebElementHelper;
import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.UserTypePage;
import pages.LoginPage;

import java.util.ArrayList;
import java.util.List;

public class UserTypePageTest {

    protected WebDriver driver;
    protected WebElementHelper webElementHelper;
    protected LoginPage loginPage;
    protected BrowserManager browserManager;
    protected UserTypePage userTypePage;
    protected List<String> listUserTypes;

    @BeforeClass(alwaysRun = true, description = "Launch browser and log in on talentLMS website")
    public void setUp() throws InterruptedException {
        driver = Driver.getDriver();
        browserManager = new BrowserManager(driver);
        webElementHelper = new WebElementHelper();
        loginPage = new LoginPage();
        userTypePage = new UserTypePage();
        listUserTypes = new ArrayList<>();

        browserManager.openByNavigate("https://app.talentlms.com/login");
        /*loginPage.enterDomain("fall2023")
                .enterUsername("nurik9816")
                .enterPassword("qwerty12345")
                .clickLoginButton();*/

        loginPage.enterDomain("timka")
                .enterUsername("timka555-player@mail.ru")
                .enterPassword("1Test8")
                .clickLoginButton();

        Thread.sleep(2000);
    }

    @Test
    @Feature("talentLMS User Types")
    @Description("Adding a new role to a table")
    @Owner("Timur")
    @Severity(SeverityLevel.CRITICAL)
    @Story("TL-014")
    @Tag("Smoke")
    public void addUserTypeInTableTest(){
        browserManager.openByNavigate("https://timka.talentlms.com/acl/index");

        String userTypeName = "testUserTypeName";

        int indexUserType = 2;

        listUserTypes.clear();

        userTypePage.addUserTypeBtnClick()
                    .fillUpNameUserType(userTypeName)
                    .choiceUserTypes(indexUserType)
                    .choosePermission()
                    .saveBtnClick();

        listUserTypes = userTypePage.getRolesFromTable(driver);

        Assert.assertEquals(listUserTypes.contains(userTypeName), true);
    }

    @Test
    @Feature("talentLMS User Types")
    @Description("Checking the table information search string")
    @Owner("Timur")
    @Severity(SeverityLevel.NORMAL)
    @Story("TL-014")
    @Tag("Smoke")
    public void searchFieldTest() throws InterruptedException {
        browserManager.openByNavigate("https://timka.talentlms.com/acl/index");

        String searchWord = "admin";

        webElementHelper.sendKeys(userTypePage.searchField, searchWord);

        Thread.sleep(1000);

        listUserTypes = userTypePage.getRolesFromTable(driver);

        userTypePage.searchFieldClear();

        Assert.assertEquals(userTypePage.countingRowsInTable(listUserTypes, searchWord) == listUserTypes.size(), true);
    }

    @Test
    @Feature("talentLMS User Types")
    @Description("Checking when adding user type it must be unique")
    @Owner("Timur")
    @Severity(SeverityLevel.CRITICAL)
    @Story("TL-014")
    @Tag("Negative")
    public void addDuplicateUserTypeInTableTest(){
        browserManager.openByNavigate("https://timka.talentlms.com/acl/index");
        String duplicateUserTypeName = "duplicate";

        int indexUserType = 1;

        userTypePage.addUserTypeBtnClick()
                .fillUpNameUserType(duplicateUserTypeName)
                .choiceUserTypes(indexUserType)
                .choosePermission()
                .saveBtnClick();

        userTypePage.addUserTypeBtnClick()
                .fillUpNameUserType(duplicateUserTypeName)
                .choiceUserTypes(indexUserType)
                .choosePermission()
                .saveBtnClick();

        String actualResult = userTypePage.warningTypeNameRepeated.getText().trim();
        String expectedResult = "A user type with this name already exists";

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    @Feature("talentLMS User Types")
    @Description("Filter check user type name table user type")
    @Owner("Timur")
    @Severity(SeverityLevel.MINOR)
    @Story("TL-014")
    @Tag("Smoke")
    public void filterUserTypeNameInTableUserTypeTest(){
        browserManager.openByNavigate("https://timka.talentlms.com/acl/index");

        List<String> beforeSortUserTypesName = new ArrayList<>();
        List<String> afterSortUserTypesName = new ArrayList<>();

        beforeSortUserTypesName = userTypePage.getRolesFromTable(driver);

        webElementHelper.click(userTypePage.filterUserTypeNameInTableUserType);

        afterSortUserTypesName = userTypePage.getRolesFromTable(driver);

        Assert.assertEquals(userTypePage.checkFilterUserTypeNameInTableUserType(beforeSortUserTypesName, afterSortUserTypesName), true);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
      //  Driver.closeDriver();
    }
}
