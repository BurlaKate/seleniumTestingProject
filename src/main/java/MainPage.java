import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Katherine on 12.06.2017.
 */
public class MainPage {

    @FindBy(id = "topLangMenuItem")
    public WebElement languageButton;

    @FindBy(css = "a[href*='/hypotheque']")
    public WebElement loansDropDownList;

    WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        driver.get("http://ia.ca");
        PageFactory.initElements(driver, this);
    }

    public MainPage languageButtonPress(){
        this.languageButton.click();
        return this;
    }

    public MainPage selectItemByVisibleText(String itemTitle){
        this.loansDropDownList.click();
        return this;
    }

}
