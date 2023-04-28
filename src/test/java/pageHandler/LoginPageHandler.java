package pageHandler;

import common.ScenarioContext;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import page.LoginPage;
import stepdef.TestContext;
import testData.TestData;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import static stepdef.CucumberTestHook.HOME_PAGE;
import pageHandler.RegistrationPageHandler;

public class LoginPageHandler {
    protected WebDriver driver;
    private LoginPage loginPage;
    private WebDriverWait wait;
    private ScenarioContext scenarioContext;
    public Actions builder;
    private RegistrationPageHandler registrationPageHandler;


    public LoginPageHandler(TestContext testContext) {
        this.driver = testContext.driver;
        this.loginPage = testContext.pageObjectManager.getLoginPage();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        builder = new Actions(driver);
        this.scenarioContext = testContext.scenarioContext;
        this.registrationPageHandler = new RegistrationPageHandler(testContext);
    }

    public void navigateToLoginPage() {
        driver.get(HOME_PAGE);
        waitForPageLoading();
    }

    public void loginValidLoginDetails(String validOrInvalidLogin) throws InterruptedException {

        switch (validOrInvalidLogin) {
            case "valid":
                builder.sendKeys(loginPage.getUserName(), TestData.VALIDLOGIN.userName()).perform();
                builder.sendKeys(loginPage.getPassword(), TestData.VALIDLOGIN.password()).perform();
                builder.click(loginPage.getLoginBtn()).perform();
                break;
            case "invalid":
                builder.sendKeys(loginPage.getUserName(), TestData.INVALIDLOGIN.userName()).perform();
                builder.sendKeys(loginPage.getPassword(), TestData.INVALIDLOGIN.password()).perform();
                builder.click(loginPage.getLoginBtn()).perform();
                break;
            default:
                builder.sendKeys( loginPage.getUserName(),scenarioContext.getContext("username").toString()).perform();
                builder.sendKeys(loginPage.getPassword(), scenarioContext.getContext("password").toString()).perform();
                builder.click(loginPage.getLoginBtn()).perform();



        }
    }

    public void waitForPageLoading() {
        wait.until(visibilityOf(loginPage.getPassword()));
        wait.until(visibilityOf(loginPage.getUserName()));
        wait.until(visibilityOf(loginPage.getLoginBtn()));
    }


    public boolean verifyLoginStatus(String status) {
        boolean loginStatus = false;

        switch (status.toLowerCase()) {
            case "login":
                loginStatus = loginPage.getProfile().isDisplayed() && loginPage.getLogout().isDisplayed();
                break;
            case "logout":
                loginStatus = loginPage.getLoginBtn().isEnabled();
                break;

        }

        return loginStatus;
    }

    public String invalidUserNamePassword() {
        return driver.findElement(By.xpath(loginPage.getInvalidUsernamePassword())).getText();
    }

    public void inValidData(DataTable dt) {
        List<Map<String, String>> fields = dt.asMaps(String.class, String.class);
        for (Map<String,String> fieldSet: fields){
            String userName= fieldSet.get("username");
            String password= fieldSet.get("password");
            builder.sendKeys(loginPage.getUserName(), userName== null ? "" : userName ).perform();
            builder.sendKeys(loginPage.getPassword(), password==null ? "" : password).perform();
            builder.click(loginPage.getLoginBtn()).perform();
        }
            registrationPageHandler.clearAllFields();
    }

    public void userLogout() {
        builder.click(loginPage.getLogout()).perform();
    }

}
