package uiTest;

import driver.Driver;
import helper.BrowserManager;
import helper.WebElementHelper;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.ImportPage;
import pages.LoginPage;

public class BaseTest {

    protected WebDriver driver;
    protected WebElementHelper webElementHelper;
    protected LoginPage loginPage;
    protected BrowserManager browserManager;
    protected ImportPage importPage;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        driver = Driver.getDriver();
        browserManager = new BrowserManager(driver);
        webElementHelper = new WebElementHelper();
        loginPage = new LoginPage();
        importPage=new ImportPage();
        browserManager.openByNavigate("https://app.talentlms.com/login");
        loginPage.enterDomain("ezelfall2023")
                .enterUsername("akezel1711 ")
                .enterPassword("qwerty12345")
                .clickLoginButton();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        Driver.closeDriver();

    }

}
