package uiTest;

import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static pages.TalentLMS_PAGES.USER_TYPES_PAGE;
import static pages.TalentLMS_PAGES.ADD_USER_TYPE_PAGE;

public class UserTypesTest extends BaseTest {

     @Test
     @Feature("talentLMS User Types")
     @Description("Checking when adding user type it must be unique")
     @Owner("Timur")
     @Severity(SeverityLevel.CRITICAL)
     @Story("TL-014")
     @Tag("Negative")
     public void addDuplicateUserTypeInTableTest(){
         browserManager.openByNavigate("USER_TYPES_PAGE.toString()");
         String duplicateUserTypeName = "duplicate";
         int indexUserType = 1;

         userTypePage.addUserTypeBtnClick()
                     .fillUpNameUserType(duplicateUserTypeName)
                     .choiceUserTypes(indexUserType)
                     .choosePermission(indexUserType)
                     .saveBtnClick();

         userTypePage.addUserTypeBtnClick()
                     .fillUpNameUserType(duplicateUserTypeName)
                     .choiceUserTypes(indexUserType)
                     .choosePermission(indexUserType)
                     .saveBtnClick();

         String actualResult = userTypePage.warningTypeNameRepeated.getText().trim();
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

        String userTypeName = "testUserTypeName";

        int indexUserType = 2;

        userTypePage.addUserTypeBtnClick()
                    .fillUpNameUserType(userTypeName)
                    .choiceUserTypes(indexUserType)
                    .choosePermission(indexUserType)
                    .saveBtnClick();

        Assert.assertEquals(userTypePage.checkUserInTable(driver, userTypeName), true);
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

        webElementHelper.sendKeys(userTypePage.searchField, searchWord);

        Thread.sleep(1000);

        listUserTypes = userTypePage.getRolesFromTable(driver);

        userTypePage.searchFieldClear();

        Assert.assertEquals(userTypePage.countingRowsInTable(listUserTypes, searchWord) == listUserTypes.size(), true);
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

        beforeSortUserTypesName = userTypePage.getRolesFromTable(driver);

        webElementHelper.click(userTypePage.filterUserTypeNameInTableUserType);

        afterSortUserTypesName = userTypePage.getRolesFromTable(driver);

        Assert.assertEquals(userTypePage.checkFilterUserTypeNameInTableUserType(beforeSortUserTypesName, afterSortUserTypesName), true);
    }

}
