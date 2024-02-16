package pages;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

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

    public void randomChoiceUserTypes(List<WebElement> listSelectUserTypes){
        AddUserTypePage addUserTypePage = new AddUserTypePage();
        int choice = (int) (Math.random() * listSelectUserTypes.size());

        addUserTypePage.fieldSelectUserType.click();
        listSelectUserTypes.get(choice).click();
    }
}
