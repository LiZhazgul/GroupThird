package pages;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;

public class AddUserTypePage {
    public AddUserTypePage(){
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



    public AddUserTypePage randomChoiceUserTypes(int choice) {
        fieldSelectUserType.click();
        listSelectUserTypes.get(choice).click();
        return this;
    }

    public AddUserTypePage choosePermission(){
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
}
