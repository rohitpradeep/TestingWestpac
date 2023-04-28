package stepdef.definitons;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import page.LoginPage;
import page.ProfilePage;
import pageHandler.LoginPageHandler;
import pageHandler.OverallRatingPageHandler;
import pageHandler.ProfilePageHandler;
import stepdef.TestContext;
import testconfig.PageObjectManager;
import static org.assertj.core.api.Java6Assertions.assertThat;


public class ProfileStepdefs {
    private static final String PROFILE_UPDATE_MESSAGE ="The profile has been saved successful" ;
    private  final WebDriver webDriver;
    private  final LoginPage loginPage;
    private final ProfilePage profilePage;
    private  final TestContext testContext;
    private  final LoginPageHandler loginPageHandler;
    private  final ProfilePageHandler profileHandler;
    private PageObjectManager pageObjectManager;
    private static final Logger logger = LogManager.getLogger(ProfileStepdefs.class);

    public ProfileStepdefs(TestContext testContext){
        this.webDriver = testContext.driver;
        this.loginPage = testContext.pageObjectManager.getLoginPage();
        this.profilePage = testContext.pageObjectManager.getProfilePage();
        this.testContext = testContext;
        this.loginPageHandler = new LoginPageHandler(testContext);
        this.profileHandler = new ProfilePageHandler(testContext);
        this.pageObjectManager = testContext.pageObjectManager;

    }

    @Then("Navigate to profile page")
    public void navigateToProfilePage() {
        logger.info("Navigate to profile page");
        profileHandler.navigateToProfilePage();
        profileHandler.waitForPageLoading();
    }

    @When("Below details are updated in user profile")
    public void belowDetailsAreUpdatedInUserProfile(DataTable dt) {
        logger.info("Updating profile details");
        profileHandler.updateProfile(dt);
        assertThat(profileHandler.profileUpdateSuccess()).as("profile update completed").isEqualTo(PROFILE_UPDATE_MESSAGE);

    }
}
