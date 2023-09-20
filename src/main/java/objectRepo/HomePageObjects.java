package objectRepo;

import basePackage.Base;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageObjects extends Base {


    public HomePageObjects(){

        PageFactory.initElements(driver,this);
    }

    @FindBy(name = "disabled button")
    public WebElement tbutton;
    @FindBy(id = "Btn_Welcome_Login")
    public WebElement login_btn;

    public WebElement getTbutton() {
        return tbutton;
    }

    public WebElement getLogin_btn() {
        return login_btn;
    }
}
