package stepdef.definitons;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import page.LoginPage;
import page.Registerpage;
import pageHandler.LoginPageHandler;
import pageHandler.OverallRatingPageHandler;
import pageHandler.RegistrationPageHandler;
import stepdef.TestContext;
import testconfig.PageObjectManager;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class RegistrationStepdefs {
    private static final String REGISTRATION_MESSAGE ="Registration is successful" ;
    private final WebDriver webDriver;
    private final LoginPage loginPage;
    private final Registerpage registerPage;
    private final TestContext testContext;
    private final RegistrationPageHandler registrationPageHandler;
    private final LoginPageHandler loginPageHandler;
    private PageObjectManager pageObjectManager;
    private static final Logger logger = LogManager.getLogger(RegistrationStepdefs.class);



    public RegistrationStepdefs(TestContext testContext) {
        this.webDriver = testContext.driver;
        this.loginPage = testContext.pageObjectManager.getLoginPage();
        this.registerPage = testContext.pageObjectManager.getRegisterPage();
        this.testContext = testContext;
        this.registrationPageHandler = new RegistrationPageHandler(testContext);
        this.loginPageHandler = new LoginPageHandler(testContext);
        this.pageObjectManager = testContext.pageObjectManager;
    }


    @Given("Navigate to registration page")
    public void navigateToRegistrationPage() {
        logger.info("Navigate to registration page");
        registrationPageHandler.navigateToRegistrationPage();
        registrationPageHandler.waitForPageLoading();
    }

    @Then("User can with register with all valid details")
    public void userCanWithRegisterWithAllValidDetails() {
        logger.info("Register page with details");
        registrationPageHandler.registerwithdetails();
        assertThat(registrationPageHandler.registrationSucess()).as("Registration not completed").isEqualTo(REGISTRATION_MESSAGE);

    }

    @And("User can login with registered details")
    public void userCanLoginWithRegisteredDetails() throws InterruptedException {
        loginPageHandler.navigateToLoginPage();
        loginPageHandler.loginValidLoginDetails("");
    }

    @When("User enters the below registration data and clicks login")
    public void userEntersTheBelowRegistrationDataAndClicksLogin(DataTable dt) {
        registrationPageHandler.registerWithoutMandatoryFields(dt);
    }
}

