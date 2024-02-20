package uiTest;

import driver.Driver;
import helper.BrowserManager;
import helper.WebElementHelper;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.LoginPage;
import pages.AccountAndSettings.AccountUsersPage;
import pages.AccountAndSettings.BasicSettingsPage;
import pages.AccountAndSettings.ThemesPage;
import pages.AccountAndSettings.CertificatesPage;
import pages.AccountAndSettings.EcommercePage;
import pages.AccountAndSettings.DomainPage;
import pages.AccountAndSettings.SubscriptionPage;
import pages.AccountAndSettings.Gamification.GamificationPage;
import pages.AccountAndSettings.Gamification.PointsBox;
import pages.AccountAndSettings.Gamification.BadgesBox;
import pages.AccountAndSettings.Gamification.LevelsBox;
import pages.AccountAndSettings.Gamification.RewardsBox;
import pages.AccountAndSettings.Gamification.LeaderboardBox;
import pages.UserTypesPage;
import pages.Users.UsersPage;

import java.util.ArrayList;
import java.util.List;

import static pages.TalentLMS_PAGES.LOGIN;
import static config.ConfigReader.getValue;

public class BaseTest {

    protected WebDriver driver;
    protected WebElementHelper webElementHelper;
    protected BrowserManager browserManager;
    protected BrowserManager.WindowHelper windowHelper;
    protected BrowserManager.FrameHelper frameHelper;
    protected LoginPage loginPage;
    protected BasicSettingsPage basicSettingsPage;
    protected AccountUsersPage accountUsersPage;
    protected ThemesPage themesPage;
    protected CertificatesPage certificatesPage;
    protected EcommercePage ecommercePage;
    protected DomainPage domainPage;
    protected SubscriptionPage subscriptionPage;
    protected GamificationPage gamificationPage;
    protected PointsBox pointsBox;
    protected BadgesBox badgesBox;
    protected LevelsBox levelsBox;
    protected RewardsBox rewardsBox;
    protected LeaderboardBox leaderboardBox;
    protected UsersPage usersPage;
    protected UserTypesPage userTypePage;
    protected List<String> listUserTypes;

    @BeforeClass(alwaysRun = true)
    public void setUp(){
        driver = Driver.getDriver();
        webElementHelper = new WebElementHelper();
        browserManager = new BrowserManager(driver);
        windowHelper = new BrowserManager.WindowHelper(driver);
        frameHelper = new BrowserManager.FrameHelper(driver);
        loginPage = new LoginPage();
        usersPage = new UsersPage();
        basicSettingsPage = new BasicSettingsPage();
        accountUsersPage = new AccountUsersPage();
        themesPage = new ThemesPage();
        certificatesPage = new CertificatesPage();
        ecommercePage = new EcommercePage();
        domainPage = new DomainPage();
        subscriptionPage = new SubscriptionPage();
        gamificationPage = new GamificationPage();
        pointsBox =  new PointsBox();
        badgesBox = new BadgesBox();
        levelsBox = new LevelsBox();
        rewardsBox = new RewardsBox();
        leaderboardBox = new LeaderboardBox();
        userTypePage = new UserTypesPage();
        listUserTypes = new ArrayList<>();
        browserManager.openByNavigate(LOGIN.toString());
        loginPage.enterDomain(getValue("domain"))
                .enterUsername(getValue("username"))
                .enterPassword(getValue("password"))
                .clickLoginButton();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        //Driver.closeDriver();
    }
}
