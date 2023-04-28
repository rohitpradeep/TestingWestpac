package page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


public class ProfilePage {
    WebDriver driver;


    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "firstName")
    private WebElement firstName;

    @FindBy(id = "lastName")
    private WebElement lastName;

    @FindBy(id = "age")
    private WebElement age;

    @FindBy(id = "address")
    private WebElement address;

    @FindBy(id = "phone")
    private WebElement phone;

    @FindBy(id = "hobby")
    private WebElement hobby;

    @FindBy(id = "gender")
    private WebElement gender;

    @FindBy(xpath = "//button[text()='Save']")
    private WebElement savebtn;

    @FindBy(id = "currentPassword")
    private WebElement currentPassword;

    @FindBy(id = "newPassword")
    private WebElement newPassword;

    @FindBy(id = "newPasswordConfirmation")
    private WebElement confirmPassword;

    @FindBy(xpath = "//a[@href='/profile']")
    private WebElement profile;

    public WebElement getFirstName() {
        return firstName;
    }

    public WebElement getLastName() {
        return lastName;
    }

    public WebElement getAge() {
        return age;
    }

    public WebElement getAddress() {
        return address;
    }

    public WebElement getPhone() {
        return phone;
    }

    public String getHobby() {
        return "hobby";
    }

    public WebElement getNewPassword() {
        return newPassword;
    }

    public WebElement getConfirmPassword() {
        return confirmPassword;
    }

    public WebElement getCurrentPassword() {
        return currentPassword;
    }

    public WebElement getGender() {
        return gender;
    }

    public WebElement getProfileLink() {
        return  profile;
    }

    public WebElement getSave() {
        return savebtn;
    }

    public String profileUpdateMessage() {
        return "//div[2][contains(text(),'The profile has been saved successful')]";
    }
}
