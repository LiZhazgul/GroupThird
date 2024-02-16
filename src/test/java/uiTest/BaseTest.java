package uiTest;

import driver.Driver;
import helper.BrowserManager;
import helper.WebElementHelper;
import io.qameta.allure.Feature;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AddUserTypePage;
import pages.LoginPage;
import tables.TableRolesReader;

import java.util.List;

public class BaseTest {

    protected WebDriver driver;
    protected WebElementHelper webElementHelper;
    protected LoginPage loginPage;
    protected BrowserManager browserManager;



    @BeforeClass(alwaysRun = true, description = "Launch browser and log in on talentLMS website")
    public void setUp() {
        driver = Driver.getDriver();
        browserManager = new BrowserManager(driver);
        webElementHelper = new WebElementHelper();
        loginPage = new LoginPage();
        browserManager.openByNavigate("https://app.talentlms.com/login");
        loginPage.enterDomain("fall2023")
                .enterUsername("nurik9816")
                .enterPassword("qwerty12345")
                .clickLoginButton();


    }

    @Test (priority = 1, description = "Checking authorization successful")
    @Feature("Demoqa webTable")
    public void websiteLoginTest(){
        String actualElement = driver.findElement(By.xpath("//div[@class='tl-title tl-ellipsis']")).getText().trim();

        String expectedElement = "Home";

        Assert.assertEquals(expectedElement, actualElement);
    }

    @Test (priority = 2, description = "Checking that the link to the desired menu works")
    public void goToPageUserTypesTest(){
        WebElement textLinkUserTypes = driver.findElement(By.xpath("//div[@class='tl-bold-link']/a[@href='https://fall2023.talentlms.com/acl/index/gridPref:reset']"));

        webElementHelper.click(textLinkUserTypes);

        String actualElement = driver.findElement(By.xpath("//div[@class='tl-title tl-ellipsis']")).getText().trim();

        String expectedElement = "Home / User types";

        Assert.assertEquals(expectedElement, actualElement);
    }

    /*@Test(priority = 3, description = "Checking the upload of data from the user roles table")
    public void outDataTable(){
        List<TableRolesReader> roles = TableRolesReader.getRolesFromTable(driver);
        TableRolesReader role = new TableRolesReader();

        role.setNameTypes("test");

        //Assert.assertEquals(roles.contains(role), true);

        roles.forEach(System.out::println);
    }*/

    @Test(priority = 4, description = "Adding a new role to a table")
    public void addRoleUserInTableTest() throws InterruptedException {
        AddUserTypePage addUserTypePage = new AddUserTypePage();

        webElementHelper.click(addUserTypePage.addUserTypeBtn);

        webElementHelper.sendKeys(addUserTypePage.nameUserTypeField, "test");

        addUserTypePage.randomChoiceUserTypes(addUserTypePage.listSelectUserTypes);

        addUserTypePage.fieldSelectUserType.click();
    }


    /*@Test(priority = 9, description = "Checking the table information search string")
    public void searchFieldTest() throws InterruptedException {
        WebElement searchField = driver.findElement(By.xpath("//input[@class='tl-grid-search-input']"));

        String searchWord = "tr";

        webElementHelper.sendKeys(searchField, searchWord);

        Thread.sleep(4000);

        List<TableRolesReader> roles = TableRolesReader.getRolesFromTable(driver);

        int amountElements = 0;

        for(int i = 0; i < roles.size(); i++){
            if(roles.get(i).getNameTypes().toLowerCase().contains(searchWord.toLowerCase())){
                amountElements++;
            }
        }

        webElementHelper.sendKeys(searchField, "");

        Assert.assertEquals(amountElements == roles.size(), true);
    }*/


    @AfterClass(alwaysRun = true)
    public void tearDown() {
      //  Driver.closeDriver();

    }

}
