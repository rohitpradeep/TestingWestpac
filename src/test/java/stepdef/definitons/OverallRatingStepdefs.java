package stepdef.definitons;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import page.LoginPage;
import pageHandler.LoginPageHandler;
import pageHandler.OverallRatingPageHandler;
import stepdef.TestContext;
import testconfig.PageObjectManager;

public class OverallRatingStepdefs {
    private  final WebDriver webDriver;
    private  final LoginPage loginPage;
    private  final TestContext testContext;
    private  final LoginPageHandler loginPageHandler;
    private  final OverallRatingPageHandler overallRatingPageHandler;
    private PageObjectManager pageObjectManager;
    private static final Logger logger = LogManager.getLogger(OverallRatingStepdefs.class);

    public OverallRatingStepdefs(TestContext testContext) {
        this.webDriver = testContext.driver;
        this.loginPage = testContext.pageObjectManager.getLoginPage();
        this.testContext = testContext;
        this.loginPageHandler = new LoginPageHandler(testContext);
        this.overallRatingPageHandler = new OverallRatingPageHandler(testContext);
        this.pageObjectManager = testContext.pageObjectManager;
        BasicConfigurator.configure();
    }

    @Given("Navigate to overall rating page")
    public void navigateToOverallRatingPage() {
        overallRatingPageHandler.navigateToOverallPage();
        overallRatingPageHandler.waitForPageLoading();
    }

    @Then("User is able to vote on model below and place comments")
    public void userIsAbleToVoteOnModelBelowAndPlaceComments(DataTable dt) {
        overallRatingPageHandler.rateModelAndLeaveComments(dt);
    }
}
