package stepdef;

import common.ScenarioContext;
import common.ScenarioContextImp;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.chrome.ChromeDriver;
import testconfig.PageObjectManager;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class CucumberTestHook {
    public WebDriver driver;
    public static String HOME_PAGE, OVERALL_RATING, REGISTRATION_PAGE, PROFILE_PAGE;
    public static String testScenarioName;
    Properties prop = new Properties();
    private TestContext testContext;
    private static final Logger logger = LogManager.getLogger(CucumberTestHook.class);


    public CucumberTestHook(TestContext testContext) {
        this.testContext = testContext;
        BasicConfigurator.configure();


    }

    @Before
    public void openBrowser(Scenario scenario) throws IOException {

        logger.info("Opening Browser");
        try (InputStream input = CucumberTestHook.class.getClassLoader().getResourceAsStream("config.properties")) {
            prop.load(input);
        } catch (FileNotFoundException ex) {
            logger.error("File  was not found.", ex);
        }

        System.setProperty("webdriver.chrome.driver", prop.getProperty("chromeDriver"));
        this.testContext.driver = new ChromeDriver();
        testContext.driver.manage().window().maximize();
        testContext.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        testScenarioName = scenario.getName();

        HOME_PAGE = getHomePageUrl(prop);
        OVERALL_RATING = getOverallRating(prop);
        REGISTRATION_PAGE = getRegistrationPage(prop);
        PROFILE_PAGE = getProfilePage(prop);
        testContext.pageObjectManager = new PageObjectManager(testContext.driver);
        testContext.scenarioContext = new ScenarioContextImp();
    }

    private String getRegistrationPage(Properties prop) {
        return this.prop.getProperty("buggyRegisration");
    }

    private String getOverallRating(Properties prop) {
        return this.prop.getProperty("buggyOverall");
    }

    public String getHomePageUrl(Properties prop) {
        return this.prop.getProperty("buggyCarsUrl");
    }

    public String getProfilePage(Properties prop) {
        return this.prop.getProperty("buggyprofile");
    }

    @After
    public void teardown(Scenario scenario) {
        testContext.driver.quit();
    }


}
