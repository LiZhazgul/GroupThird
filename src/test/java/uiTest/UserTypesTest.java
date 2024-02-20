package uiTest;

import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static pages.TalentLMS_PAGES.USER_TYPES_PAGE;
import static pages.TalentLMS_PAGES.ADD_USER_TYPE_PAGE;

public class UserTypesTest extends BaseTest {
    private String nameUserType;
    private int indexUserType;

     @Test
     @Feature("talentLMS User Types")
     @Description("Checking when adding user type it must be unique")
     @Owner("Timur")
     @Severity(SeverityLevel.CRITICAL)
     @Story("TL-014")
     @Tag("Negative")
     public void addDuplicateUserTypeInTableTest(){
         browserManager.openByNavigate(ADD_USER_TYPE_PAGE.toString());

         nameUserType = "duplicate";
         indexUserType = 1;

         userTypesPage.addNotUniqueUserType(nameUserType, indexUserType);

         String actualResult = userTypesPage.warningTextTypeNameRepeated.getText().trim();
         String expectedResult = "A user type with this name already exists";

         Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    @Feature("talentLMS User Types")
    @Description("Adding a new role to a table")
    @Owner("Timur")
    @Severity(SeverityLevel.CRITICAL)
    @Story("TL-014")
    @Tag("Smoke")
    public void addUserTypeInTableTest(){
        browserManager.openByNavigate(USER_TYPES_PAGE.toString());

        nameUserType = "testUserTypeName";
        indexUserType = 2;

        userTypesPage.addUserType(nameUserType, indexUserType);

        Assert.assertEquals(userTypesPage.checkUserInTable(driver, nameUserType), true);
    }

    @Test
    @Feature("talentLMS User Types")
    @Description("Checking the table information search string")
    @Owner("Timur")
    @Severity(SeverityLevel.NORMAL)
    @Story("TL-014")
    @Tag("Smoke")
    public void searchFieldTest() throws InterruptedException {
        browserManager.openByNavigate(USER_TYPES_PAGE.toString());

        String searchWord = "admin";

        webElementHelper.sendKeys(userTypesPage.searchField, searchWord);

        Thread.sleep(1000);

        listUserTypes = userTypesPage.getRolesFromTable(driver);

        userTypesPage.searchFieldClear();

        Assert.assertEquals(userTypesPage.countingRowsInTable(listUserTypes, searchWord) == listUserTypes.size(), true);
    }

    @Test
    @Feature("talentLMS User Types")
    @Description("Filter check user type name table user type")
    @Owner("Timur")
    @Severity(SeverityLevel.MINOR)
    @Story("TL-014")
    @Tag("Smoke")
    public void filterUserTypeNameInTableUserTypeTest(){
        browserManager.openByNavigate(USER_TYPES_PAGE.toString());

        List<String> beforeSortUserTypesName;
        List<String> afterSortUserTypesName;

        beforeSortUserTypesName = userTypesPage.getRolesFromTable(driver);

        webElementHelper.click(userTypesPage.filterUserTypeNameInTableUserType);

        afterSortUserTypesName = userTypesPage.getRolesFromTable(driver);

        Assert.assertEquals(userTypesPage.checkFilterUserTypeNameInTableUserType(beforeSortUserTypesName, afterSortUserTypesName), true);
    }
}
