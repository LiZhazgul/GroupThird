package pages;

import driver.Driver;
import helper.WebElementHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class UserTypePage {

    private WebElementHelper webElementHelper;

    public UserTypePage(){
        webElementHelper = new WebElementHelper();
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//input[@id='user_type_name']")
    public WebElement nameUserTypeField;

    @FindBy(xpath = "//div[@class='tl-header-tools']/a[@class='btn btn-primary']")
    public WebElement addUserTypeBtn;

    @FindBy(xpath = "//span[@class='select2-chosen']")
    public WebElement fieldSelectUserType;

    @FindBy(xpath = "//div[@class='select2-result-label']")
    public List<WebElement> listSelectUserTypes;

    @FindBy(xpath = "//span[@class='dynatree-node dynatree-folder dynatree-expanded dynatree-has-children dynatree-exp-e dynatree-ico-ef']/span[@class='dynatree-checkbox']")
    public List<WebElement> permissionList;

    @FindBy(xpath = "//span[@class='dynatree-node dynatree-folder dynatree-expanded dynatree-has-children dynatree-lastsib dynatree-exp-el dynatree-ico-ef']/span[@class='dynatree-checkbox']")
    public WebElement permissionGeneral;

    @FindBy(xpath = "//input[@id='user-type-save-button']")
    public WebElement saveBtn;

    @FindBy(xpath = "//input[@class='tl-grid-search-input']")
    public WebElement searchField;

    @FindBy(xpath = "//span[@id='user-type-name-help-block']/span[@class='help-inline']")
    public WebElement warningTypeNameRepeated;




    public UserTypePage addUserTypeBtnClick() {
        webElementHelper.click(addUserTypeBtn);
        return this;
    }

    public UserTypePage saveBtnClick() {
        webElementHelper.click(saveBtn);
        return this;
    }

    public UserTypePage searchFieldClear() {
        searchField.clear();
        searchField.sendKeys(Keys.ENTER);
        return this;
    }

    public int countingRowsInTable(List<String> listUserTypes, String searchWord) {
        int amountRows = 0;
        for(int i = 0; i < listUserTypes.size(); i++){
            if(listUserTypes.get(i).toLowerCase().contains(searchWord.toLowerCase())){
                amountRows++;
            }
        }
        return amountRows;
    }

    public UserTypePage fillUpNameUserType(String nameUserType) {
        webElementHelper.sendKeys(nameUserTypeField, nameUserType);
        return this;
    }

    public UserTypePage choiceUserTypes(int choice) {
        fieldSelectUserType.click();
        listSelectUserTypes.get(choice).click();
        return this;
    }

    public UserTypePage choosePermission(){
        switch (fieldSelectUserType.getText()){
            case "Administrator":
                permissionList.get(0).click();
                permissionGeneral.click();
                break;
            case "Instructor":
                permissionList.get(1).click();
                permissionGeneral.click();
                break;
            case "Learner":
                permissionList.get(2).click();
                permissionGeneral.click();
                break;

        }

        return this;
    }

    public static ArrayList<String> getRolesFromTable(WebDriver driver){
        List<WebElement> rows = driver.findElements(By.xpath("//tr[@role='row']"));
        String nameType;

        ArrayList<String> roles = new ArrayList<>();

        int step = 0;

        for(WebElement row : rows){
            if(step >= rows.size() - 1){
                break;
            }
            List<WebElement> cells = row.findElements(By.xpath("//td[@class=' tl-align-left']"));
            nameType = cells.get(step).getText();
            step++;
            roles.add(nameType);
        }

        return roles;
    }
}
