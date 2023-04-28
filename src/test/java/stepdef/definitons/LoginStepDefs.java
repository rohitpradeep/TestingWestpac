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
import pageHandler.LoginPageHandler;
import stepdef.TestContext;
import testconfig.PageObjectManager;
import static org.assertj.core.api.Java6Assertions.assertThat;


public class LoginStepDefs {
    private  final WebDriver webDriver;
    private  final LoginPage loginPage;
    private  final TestContext testContext;
    private  final LoginPageHandler loginPageHandler;
    private PageObjectManager pageObjectManager;
    private static final Logger logger = LogManager.getLogger(LoginStepDefs.class);


    public LoginStepDefs(TestContext testContext) {
        this.webDriver = testContext.driver;
        this.loginPage = testContext.pageObjectManager.getLoginPage();
        this.testContext = testContext;
        this.loginPageHandler = new LoginPageHandler(testContext);
        this.pageObjectManager = testContext.pageObjectManager;
    }

    @Given("Navigate to BuggyCars website")
    public void loginToBuggyCars() {
        logger.info("JJNDJKNDKJND");
        loginPageHandler.navigateToLoginPage();
        loginPageHandler.waitForPageLoading();
    }


    @Then("User is logged in successfully")
    public void userIsLoggedInSuccessfully() {
        assertThat(loginPageHandler.verifyLoginStatus("Login")).as("User not logged in successfully").isTrue();
    }

    @When("User logins with {string} credentials")
    public void userLoginsWithCredentials(String validOrInvalidLogin) throws InterruptedException {
        loginPageHandler.loginValidLoginDetails(validOrInvalidLogin);
    }


    @Then("Verify error message {string} is displayed")
    public void verifyErrorMessageIsDisplayed(String errorMessage) {
        assertThat(loginPageHandler.invalidUserNamePassword()).as("User not logged in successfully").isEqualTo(errorMessage);
    }

    @When("User enters the below data and clicks login")
    public void userEntersTheBelowDataAndClicksLogin(DataTable dt) {
        loginPageHandler.inValidData(dt);
    }

    @And("User is logged out successfully")
    public void userIsLoggedOutSuccessfully() {
        loginPageHandler.userLogout();
        assertThat(loginPageHandler.verifyLoginStatus("Logout")).as("User not logged in successfully").isTrue();
        loginPageHandler.waitForPageLoading();;
    }
}
