import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainScreen {

    private static final String EN_LANGUAGE = "en";

    WebDriver driver;

    @FindBy(id = "topLangMenuItem")
    public WebElement languageButton;

    @FindBy(css = "a[data-utag-name='loans']")
    public WebElement loansButton;

    @FindBy(css = "section[aria-labelledby='Produits']")
    public WebElement dropDownList;

    MainScreen(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getLanguageToSwitchTo() throws StaleElementReferenceException {
        try {
            return languageButton.getAttribute("lang");
        } catch (StaleElementReferenceException e) {
            return driver.findElement(By.id("topLangMenuItem")).getAttribute("lang");
        }
    }

    public MainScreen languageButtonPress() {
        try {
            languageButton.click();
        } catch (StaleElementReferenceException e) {
            driver.findElement(By.id("topLangMenuItem")).click();
        }
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
        }
        return this;
    }

    public MortgageScreen selectProduct(String productHref) {
        openLoansListIfNotOpened();
        driver.findElement(By.cssSelector("ul.mega-menu-content li a[data-utag-name='" + productHref + "']")).click();
        return new MortgageScreen(driver);
    }
}
