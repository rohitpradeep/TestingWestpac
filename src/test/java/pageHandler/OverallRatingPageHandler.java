package pageHandler;

import common.ScenarioContext;
import io.cucumber.datatable.DataTable;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.LoginPage;
import page.OverallRatingPage;
import stepdef.CucumberTestHook;
import stepdef.TestContext;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static stepdef.CucumberTestHook.OVERALL_RATING;


public class OverallRatingPageHandler {
    protected WebDriver driver;
    private LoginPage loginPage;
    private OverallRatingPage overallRatingPage;
    private WebDriverWait wait;
    private ScenarioContext scenarioContext;
    public Actions builder;
    private RegistrationPageHandler registrationPageHandler;
    private static final Logger logger = LogManager.getLogger(OverallRatingPageHandler.class);



    public OverallRatingPageHandler(TestContext testContext) {
        this.driver = testContext.driver;
        this.loginPage = testContext.pageObjectManager.getLoginPage();
        this.overallRatingPage = testContext.pageObjectManager.getOverallRatingPage();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        builder = new Actions(driver);
        this.scenarioContext = testContext.scenarioContext;
        this.registrationPageHandler = new RegistrationPageHandler(testContext);
    }


    public void navigateToOverallPage() {
        logger.info("Navigating to Overall Rating page");
        driver.get(OVERALL_RATING);
    }

    public void waitForPageLoading() {
        logger.info("Page Loading");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    }

    public void rateModelAndLeaveComments(DataTable dt) {
        int totalPages= returnTotalNumberPages();
        List<Map<String, String>> fields = dt.asMaps(String.class, String.class);
        for (Map<String, String> fieldSet : fields) {
            String model = fieldSet.get("Model");
            String comments = fieldSet.get("Comments");
            for (int i = 1; i <= totalPages; i++) {
                if (driver.findElement(By.xpath(overallRatingPage.getModelName(model))).getText().equalsIgnoreCase(model)){
                    logger.info("Found Model");
                    builder.click(driver.findElement(By.xpath(overallRatingPage.getModelName(model)))).perform();
                    logger.info("Placing Comments");
                    builder.sendKeys(overallRatingPage.getComment(),comments).perform();
                    builder.click(overallRatingPage.getVotebtn()).perform();
                    navigateToOverallPage();
                    i=6;
                } else {
                    logger.info("Incorrect Model please select the correct model");
                    builder.scrollToElement(overallRatingPage.getPageNavigator()).perform();
                    builder.click(overallRatingPage.getRightbtn()).perform();
                }

            }
        }
    }

    private int returnTotalNumberPages() {
        WebElement newt = driver.findElement(By.xpath("/html/body/my-app/div/main/my-overall/div/my-pager/div/div"));
        String original = newt.getText();
        int index = original.indexOf("f");
        int substring = Integer.parseInt(original.substring(index + "f".length()).replace(" ", ""));
        return substring;

    }
}
