package helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

public class DropdownHelper {

    private WebDriver driver;
    private Select select;
    private WebDriverWait wait;

    public DropdownHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public DropdownHelper selectByText(WebElement element, String text){
        select = new Select(element);
        select.selectByVisibleText(text);
        return this;
    }

    public DropdownHelper selectByIndex(WebElement element, int index){
        select = new Select(element);
        select.selectByIndex(index);
        return this;
    }

    public DropdownHelper selectByValue(WebElement element, String value){
        select = new Select(element);
        select.selectByValue(value);
        return this;
    }

    public List<String> getAllDropdownValues(WebElement element){
        select = new Select(element);
        List<WebElement> elementList = select.getOptions();
        List<String> valueList = new LinkedList<String>();
        for (WebElement e : elementList){
            valueList.add(e.getText());
        }
        return valueList;
    }
}
