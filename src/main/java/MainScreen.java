import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainScreen {

    public static final String IA_CA_URL = "http://ia.ca";
    private static final String EN_LANGUAGE = "en";

    @FindBy(id = "topLangMenuItem")
    public WebElement languageButton;
    @FindBy(css = "a[data-utag-name='loans']")
    public WebElement loansButton;
    @FindBy(css = "section[aria-labelledby='Produits']")
    public WebElement dropDownList;

    private WebDriver driver;

    MainScreen(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step
    public String getLanguageToSwitchTo() {
        try {
            return languageButton.getAttribute("lang");
        } catch (StaleElementReferenceException e) {
            return driver.findElement(By.id("topLangMenuItem")).getAttribute("lang");
        }
    }

    @Step
    public MainScreen languageButtonPress() {
        try {
            languageButton.click();
        } catch (StaleElementReferenceException e) {
            driver.findElement(By.id("topLangMenuItem")).click();
        }
        return this;
    }

    @Step
    public MainScreen openPageAndChangeLanguage() {
        driver.get(IA_CA_URL);
        if (getLanguageToSwitchTo().equals(EN_LANGUAGE)) {
            languageButtonPress();
        }
        return this;
    }

    @Step
    public MainScreen openLoansListIfNotOpened() {
        if (!dropDownList.isDisplayed()) {
            loansButton.click();
        }
        return this;
    }

    @Step
    public MortgageScreen selectProduct(String productHref) {
        openLoansListIfNotOpened();
        driver.findElement(By.cssSelector("ul.mega-menu-content li a[data-utag-name='" + productHref + "']")).click();
        return new MortgageScreen(driver);
    }
}
