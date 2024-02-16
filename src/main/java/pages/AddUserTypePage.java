package pages;

import driver.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SelectMenuPage {
    public SelectMenuPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "s2id_autogen1")
    public WebElement nameUserTypeField;

}
