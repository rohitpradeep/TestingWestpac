package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class OverallRatingPage {
    WebDriver driver;


    public OverallRatingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public String getModelName(String model){
        return String.format(("//a[contains(text(),'%s')]"),model);
    }
    @FindBy(xpath = "//h4[contains(text(),'Votes: ')]")
    private WebElement votes;

    @FindBy(xpath = "//input[@type='text']")
    private WebElement pageNavigator;

    @FindBy(id = "comment")
    private WebElement comment;

    @FindBy(xpath = "//button[contains(text(),'Vote!')]")
    private WebElement votebtn;

    @FindBy(xpath = "//*[@class='btn']")
    private WebElement rightbtn;

    @FindBy(xpath = "/html/body/my-app/div/main/my-overall/div/my-pager/div/div/text()")
    private WebElement totalPages;

    public WebElement getTotalPages(){
        return  totalPages;
    }

    public WebElement getRightbtn() {
        return rightbtn;
    }

    public WebElement getVotes() {
        return votes;
    }

    public WebElement getVotebtn() {
        return votebtn;
    }

    public WebElement getPageNavigator() {
        return pageNavigator;
    }

    public WebElement getComment() {
        return comment;
    }
}
