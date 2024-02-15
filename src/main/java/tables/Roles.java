package tables;

import lombok.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Roles {

    private String nameTypes;

    public static ArrayList<Roles> getRolesFromTable(WebDriver driver){
        List<WebElement> rows = driver.findElements(By.xpath("//tr[@role='row']"));

        ArrayList<Roles> roles = new ArrayList<>();

        int step = 0;

        for(WebElement row : rows){
            if(step >= rows.size() - 1){
                break;
            }
            List<WebElement> cells = row.findElements(By.xpath("//td[@class=' tl-align-left']"));
            String nameType = cells.get(step).getText();
            step++;
            roles.add(new Roles(nameType));
        }

        return roles;
    }
}