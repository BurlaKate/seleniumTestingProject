import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentCalculatorScreen {

    WebDriver driver;

    @FindBy(id = "par_valeur")
    private WebElement purchasePriceRadioButton;

    @FindBy(id = "PrixPropriete")
    private WebElement purchasePriceInput;

    @FindBy(id = "PrixProprietePlus")
    private WebElement purchasePricePlusButton;

    @FindBy(id = "PrixProprieteMinus")
    private WebElement purchasePriceMinusButton;

    @FindBy(css = ".slider-handle min-slider-handle custom")
    private WebElement purchasePriceSlider;

    public PaymentCalculatorScreen(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PaymentCalculatorScreen open(String productHref){
        MainScreen mainScreen = new MainScreen(driver);
        mainScreen.openPageAndChangeLanguage();
        mainScreen.openLoansListIfNotOpened();
        mainScreen.selectProduct(productHref);
        MortgageScreen mortgageScreen = new MortgageScreen(driver);
        mortgageScreen.calculatePaymentButtonPress();
        return this;
    }

    public PaymentCalculatorScreen movePurchaseSliderToTheRight(){
        WebElement source = driver.findElement(By.cssSelector("div[style='left: 0%;']"));
        WebElement target = driver.findElement(By.cssSelector("div[style='left: 25%;']"));

        Actions builder = new Actions(driver);
        builder.dragAndDrop(source, target).perform();

        return this;
    }
}
