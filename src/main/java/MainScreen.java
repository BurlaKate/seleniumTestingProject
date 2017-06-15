import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class MainScreen {

    public static final String EN_LANGUAGE = "en";

    WebDriver driver;

    @FindBy(id = "topLangMenuItem")
    private WebElement languageButton;

    @FindBy(css = "a[href='/mortgage']")
    private WebElement loansButton;

    @FindBy(css = "section[aria-labelledby='Produits']")
    private WebElement dropDownList;

    public MainScreen(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getLanguageToSwitchTo() {
        return this.languageButton.getAttribute("lang");
    }

    public MainScreen languageButtonPress() {
        this.languageButton.click();
        return this;
    }

    public MainScreen openPageAndChangeLanguage() {
        driver.get("http://ia.ca");
        if (getLanguageToSwitchTo().equals(EN_LANGUAGE)) {
            languageButtonPress();
        }
        return this;
    }

    public MainScreen openLoansListIfNotOpened() {
        if (!dropDownList.isDisplayed()) {
            loansButton.click();
            (new WebDriverWait(driver, 5))
                    .until((WebDriver driver) -> this.dropDownList.isDisplayed());
        }
        Assert.assertTrue(this.dropDownList.isDisplayed());
        return this;
    }

    public MortgageScreen selectProduct(String productHref) {
        this.openLoansListIfNotOpened();
        this.driver.findElement(By.cssSelector("ul.mega-menu-content li a[href='" + productHref + "']")).click();
        return new MortgageScreen(driver);
    }


}
