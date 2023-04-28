package testconfig;

import org.openqa.selenium.WebDriver;
import page.LoginPage;
import page.OverallRatingPage;
import page.ProfilePage;
import page.Registerpage;
import stepdef.TestContext;

public class PageObjectManager {
    private WebDriver webDriver;
    private LoginPage loginPage;
    private Registerpage registerpage;
    private OverallRatingPage overallRatingPage;
    private ProfilePage profilePage;

    public PageObjectManager(WebDriver driver) {
        this.webDriver = driver;
    }

    public LoginPage getLoginPage() {
        return (loginPage == null) ? loginPage = new LoginPage(webDriver) : loginPage;
    }

    public Registerpage getRegisterPage() {
        return (registerpage == null) ? registerpage = new Registerpage(webDriver) : registerpage;
    }

    public OverallRatingPage getOverallRatingPage() {
        return (overallRatingPage == null) ? overallRatingPage = new OverallRatingPage(webDriver) : overallRatingPage;
    }

    public ProfilePage getProfilePage() {
        return (profilePage == null) ? profilePage = new ProfilePage(webDriver) : profilePage;
    }
}
