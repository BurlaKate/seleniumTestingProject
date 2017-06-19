import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class MainScreen {

    private static final String EN_LANGUAGE = "en";

    private static WebDriver driver;

    @FindBy(id = "topLangMenuItem")
    private WebElement languageButton;

    @FindBy(css = "a[data-utag-name='loans']")
    private WebElement loansButton;

    @FindBy(css = "section[aria-labelledby='Produits']")
    private WebElement dropDownList;

    MainScreen(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getLanguageToSwitchTo() throws StaleElementReferenceException {
        try {
            return this.languageButton.getAttribute("lang");
        } catch (StaleElementReferenceException e) {
            return driver.findElement(By.id("topLangMenuItem")).getAttribute("lang");
        }
    }

    public MainScreen languageButtonPress() {
        try {
            this.languageButton.click();
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
        Assert.assertTrue(this.dropDownList.isDisplayed());
        return this;
    }

    public MortgageScreen selectProduct(String productHref) {
        this.openLoansListIfNotOpened();
        this.driver.findElement(By.cssSelector("ul.mega-menu-content li a[data-utag-name='" + productHref + "']")).click();
        return new MortgageScreen(driver);
    }
}
