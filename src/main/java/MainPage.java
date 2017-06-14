import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Katherine on 12.06.2017.
 */
public class MainPage {

    @FindBy(id = "topLangMenuItem")
    private WebElement languageButton;

    public MainPage languageButtonPress(){
        this.languageButton.click();
        return this;
    }

}
