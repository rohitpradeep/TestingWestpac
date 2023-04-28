package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {
    WebDriver driver;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@name='login']")
    private WebElement userName;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement password;

    @FindBy(xpath = "//button[text()='Login']")
    private WebElement loginBtn;

    @FindBy(xpath = "//a[@href='/register']")
    private WebElement registerBtn;

    @FindBy(xpath = "//a[@href='/profile']")
    private WebElement profile;

    @FindBy(xpath = "//a[text()='Logout']")
    private WebElement logout;


    public WebElement getProfile(){
        return  profile;
    }

    public String getInvalidUsernamePassword(){
        return  "//span[text()='Invalid username/password']";
    }

    public WebElement getLogout(){
        return  logout;
    }
    public WebElement getUserName(){
        return  userName;
    }

    public WebElement getPassword(){
        return  password;
    }

    public WebElement getLoginBtn(){
        return  loginBtn;
    }

    public WebElement getRegisterBtn(){
        return  registerBtn;
    }




}
