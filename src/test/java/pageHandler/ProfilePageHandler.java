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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.LoginPage;


import page.OverallRatingPage;
import page.ProfilePage;
import stepdef.CucumberTestHook;
import stepdef.TestContext;
import testData.RegistrationTestData;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class ProfilePageHandler {
    protected WebDriver driver;
    private LoginPage loginPage;
    private ProfilePage profilePage;
    private OverallRatingPage overallRatingPage;
    private WebDriverWait wait;
    private ScenarioContext scenarioContext;
    public Actions builder;
    private RegistrationPageHandler registrationPageHandler;
    private static final Logger logger = LogManager.getLogger(OverallRatingPageHandler.class);

    public ProfilePageHandler(TestContext testContext) {
        this.driver = testContext.driver;
        this.loginPage = testContext.pageObjectManager.getLoginPage();
        this.profilePage = testContext.pageObjectManager.getProfilePage();
        this.overallRatingPage = testContext.pageObjectManager.getOverallRatingPage();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        builder = new Actions(driver);
        this.scenarioContext = testContext.scenarioContext;
        this.registrationPageHandler = new RegistrationPageHandler(testContext);
    }

    public void navigateToProfilePage() {
        builder.click(profilePage.getProfileLink()).perform();
    }

    public void waitForPageLoading() {
        wait.until(ExpectedConditions.visibilityOf(profilePage.getFirstName()));
        wait.until(ExpectedConditions.visibilityOf(profilePage.getLastName()));
        wait.until(ExpectedConditions.visibilityOf(profilePage.getCurrentPassword()));
    }

    public void updateProfile(DataTable dt) {
        List < Map < String, String >> fields = dt.asMaps(String.class, String.class);
        for (Map<String, String> fieldSet : fields) {
            String age = fieldSet.get("age");
            String firstname = fieldSet.get("firstname");
            String lastname = fieldSet.get("lastname");
            String address = fieldSet.get("address");
            String phone = fieldSet.get("phone");
            String hobby = fieldSet.get("hobby");
            String gender = fieldSet.get("gender");
            String passwordchange = fieldSet.get("passwordchange");
            builder.sendKeys(profilePage.getFirstName(), firstname).perform();
            builder.sendKeys(profilePage.getLastName(), lastname).perform();
            builder.sendKeys(profilePage.getAge(), age).perform();
            builder.sendKeys(profilePage.getAddress(), address).perform();
            builder.sendKeys(profilePage.getPhone(), phone).perform();
            builder.moveToElement(driver.findElement(By.id(profilePage.getHobby()))).perform();
            if (passwordchange.equalsIgnoreCase("yes")) {
                builder.sendKeys(profilePage.getCurrentPassword(), scenarioContext.getContext("password").toString()).perform();
                builder.sendKeys(profilePage.getNewPassword(), RegistrationTestData.PASSWORD.getValue()).perform();
                scenarioContext.putContext("newPassword",RegistrationTestData.PASSWORD.getValue());
                builder.sendKeys(profilePage.getConfirmPassword(), RegistrationTestData.PASSWORD.getValue()).perform();
                builder.click(profilePage.getSave()).perform();
            }
        }

    }

    public String profileUpdateSuccess() {
        logger.info("Profile details updated");
        return driver.findElement(By.xpath(profilePage.profileUpdateMessage())).getText();
    }
}
