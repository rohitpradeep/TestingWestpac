package pageHandler;

import common.ScenarioContext;
import io.cucumber.datatable.DataTable;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import static stepdef.CucumberTestHook.HOME_PAGE;
import static stepdef.CucumberTestHook.REGISTRATION_PAGE;

import page.LoginPage;
import page.Registerpage;
import stepdef.TestContext;
import stepdef.definitons.RegistrationStepdefs;
import testData.RegistrationTestData;
import testData.TestData;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public class RegistrationPageHandler {
    protected WebDriver driver;
    private LoginPage loginPage;
    private Registerpage registerpage;
    private WebDriverWait wait;
    private Actions builder;
    private ScenarioContext scenarioContext;
    private static final Logger logger = LogManager.getLogger(RegistrationPageHandler.class);



    public RegistrationPageHandler(TestContext testContext) {
        this.driver = testContext.driver;
        this.loginPage = testContext.pageObjectManager.getLoginPage();
        this.registerpage = testContext.pageObjectManager.getRegisterPage();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        builder = new Actions(driver);
        scenarioContext = testContext.scenarioContext;
    }

    public void navigateToRegistrationPage() {
        driver.get(REGISTRATION_PAGE);
    }

    public void waitForPageLoading() {
        wait.until(visibilityOf(registerpage.getLogin()));
        wait.until(visibilityOf(registerpage.getFirstName()));
        wait.until(visibilityOf(registerpage.getLastName()));
        wait.until(visibilityOf(registerpage.getPassword()));
        wait.until(visibilityOf(registerpage.getConfirmPassword()));
    }

    public void registerwithdetails() {
        logger.info("Entering details");
        builder.sendKeys(registerpage.getLogin(), RegistrationTestData.LOGIN.getValue()).perform();
        builder.sendKeys(registerpage.getFirstName(), RegistrationTestData.FIRSTNAME.getValue()).perform();
        builder.sendKeys(registerpage.getLastName(), RegistrationTestData.LASTNAME.getValue()).perform();
        builder.sendKeys(registerpage.getPassword(), RegistrationTestData.PASSWORD.getValue()).perform();
        builder.sendKeys(registerpage.getConfirmPassword(), RegistrationTestData.CONFIRMPASSWORD.getValue()).perform();
        builder.click(registerpage.getRegisterButton()).perform();
        scenarioContext.putContext("password",RegistrationTestData.CONFIRMPASSWORD.getValue());
        scenarioContext.putContext("username",RegistrationTestData.LOGIN.getValue());

    }

    public String registrationSucess() {
        logger.info("Verifying message");
        return driver.findElement(By.xpath(registerpage.getRegistrationMessage())).getText();
    }

    public void registerWithoutMandatoryFields(DataTable dt) {
        List<Map<String, String>> fields = dt.asMaps(String.class, String.class);
        for (Map<String, String> fieldSet : fields) {
            String login = fieldSet.get("login");
            String firstname = fieldSet.get("firstname");
            String lastname = fieldSet.get("lastname");
            String password = fieldSet.get("password");
            String confirmPassword = fieldSet.get("confirmPassword");
            builder.sendKeys(registerpage.getLogin(),login== null ? "" : login).perform();
            builder.sendKeys(registerpage.getFirstName(),firstname== null ? "" : firstname).perform();
            builder.sendKeys(registerpage.getLastName(),lastname== null ? "" : lastname).perform();
            builder.sendKeys(registerpage.getPassword(),password== null ? "" : password).perform();
            builder.sendKeys(registerpage.getConfirmPassword(),confirmPassword== null ? "" : confirmPassword).perform();
            assertThat(registerpage.getRegisterButton().isEnabled()).isFalse();
            clearAllFields();
        }

    }

    public void clearAllFields() {
        List<WebElement> inputFields = driver.findElements(By.tagName("input"));
        for(WebElement inputField: inputFields){
            inputField.clear();
        }
    }
}
