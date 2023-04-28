package stepdef;

import common.ScenarioContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import testconfig.PageObjectManager;

public class TestContext {
    public WebDriver driver;
    public PageObjectManager pageObjectManager;
    public ScenarioContext scenarioContext;
}
