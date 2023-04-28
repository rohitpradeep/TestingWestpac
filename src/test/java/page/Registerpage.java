package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


public class Registerpage {
    private WebDriver driver;

    public Registerpage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "username")
    private WebElement userName;

    @FindBy(id = "firstName")
    private WebElement firstName;

    @FindBy(id = "lastName")
    private WebElement lastName;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "confirmPassword")
    private WebElement confirmPassword;

    @FindBy(xpath = "//button[text()='Register']")
    private WebElement registerButton;

    @FindBy(xpath = "//a[contains(text(),'Cancel')]")
    private WebElement cancelButton;


    public WebElement getLogin() {
        return userName;
    }

    public WebElement getFirstName() {
        return  firstName;
    }

    public WebElement getRegisterButton() {
        return  registerButton;
    }

    public WebElement getLastName() {
        return lastName;
    }

    public WebElement getPassword() {
        return  password;
    }

    public String getRegistrationMessage() {
        return  "//form/div[contains(text(),'Registration is successful')]";
    }

    public WebElement getConfirmPassword() {
        return confirmPassword;
    }
}
