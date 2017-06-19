import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class PaymentCalculatorScreen {

    private static final int PURCHASE_SLIDER_X_OFFSET = 100;
    private static final int PURCHASE_SLIDER_Y_OFFSET = 0;
    private static final String DATA_VALUE_ATTRIBUTE = "data-value";
    private static final String VALUE_ATTRIBUTE = "value";

    WebDriver driver;

    @FindBy(id = "sliderPrixPropriete")
    private WebElement sliderPrixValue;

    @FindBy(id = "sliderMiseDeFond")
    private WebElement sliderMiseValue;

    @FindBy(id = "PrixProprietePlus")
    private WebElement purchasePricePlusButton;

    @FindBy(id = "MiseDeFondPlus")
    private WebElement downPricePlusButton;

    @FindBy(css = "div[class = 'slider-handle min-slider-handle custom']")
    private WebElement purchasePriceSlider;

    @FindBy(id = "btn_calculer")
    private WebElement calculateButton;

    @FindBy(id = "TauxInteret")
    private WebElement interestRateInput;

    @FindBy(id = "paiement-resultats")
    private WebElement paymentResultsLabel;

    @FindBy(css = "label[for='Amortissement'] + .selectric-wrapper>.selectric")
    private WebElement amortizationSelect;

    @FindBy(css = "label[for='Amortissement'] + .selectric-wrapper>.selectric-items>div>ul>li:nth-child(1)")
    private WebElement amortizationValueFifteen;

    @FindBy(css = "label[for='Amortissement'] + .selectric-wrapper>.selectric>p.label")
    private WebElement amortizationLabel;

    @FindBy(css = "label[for='FrequenceVersement'] + .selectric-wrapper>.selectric")
    private WebElement paymentFrequencySelect;

    @FindBy(css = "label[for='FrequenceVersement'] + .selectric-wrapper>.selectric>p.label")
    private WebElement paymentFrequencyLabel;

    @FindBy(css = "label[for='FrequenceVersement'] + .selectric-wrapper>.selectric-items>div>ul>li:nth-child(4)")
    private WebElement paymentFrequencyWeeklyValue;

    PaymentCalculatorScreen(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PaymentCalculatorScreen open(String productHref) {
        MainScreen mainScreen = new MainScreen(driver);
        mainScreen.openPageAndChangeLanguage();
        mainScreen.openLoansListIfNotOpened();
        mainScreen.selectProduct(productHref);
        MortgageScreen mortgageScreen = new MortgageScreen(driver);
        mortgageScreen.calculatePaymentButtonPress();
        return this;
    }

    public int getLocationOfPurchaseSlider() {
        Point point = purchasePriceSlider.getLocation();
        int xcord = point.getX();
        return xcord;
    }

    public PaymentCalculatorScreen movePurchaseSliderToTheRight() {
        new Actions(driver).dragAndDropBy(purchasePriceSlider, PURCHASE_SLIDER_X_OFFSET,
                PURCHASE_SLIDER_Y_OFFSET).perform();
        return this;
    }

    public PaymentCalculatorScreen scrollTo(String yOffsetValue) {
        ((JavascriptExecutor) driver).executeScript(
                "window.scrollBy(0, " + yOffsetValue + ");");
        return this;
    }

    public boolean checkIfPurchaseSliderWorks(int oldLocation, int newLocation) {
        return newLocation - oldLocation >= PURCHASE_SLIDER_X_OFFSET;
    }

    public int getValueOfPurchasePrice() {
        return Integer.parseInt(sliderPrixValue.getAttribute(DATA_VALUE_ATTRIBUTE));
    }

    public int getValueOfDownPrice() {
        return Integer.parseInt(sliderMiseValue.getAttribute(DATA_VALUE_ATTRIBUTE));
    }

    public PaymentCalculatorScreen movePurchaseSliverUsingPlusButton() {
        for (int i = getValueOfPurchasePrice(); i < 500000; i += 250000)
            purchasePricePlusButton.click();
        return this;
    }

    public PaymentCalculatorScreen moveDownSliverUsingPlusButton() {
        for (int i = getValueOfDownPrice(); i < 100000; i += 100000)
            downPricePlusButton.click();
        return this;
    }

    public String getValueFromInterestRateInput() {
        return interestRateInput.getAttribute(VALUE_ATTRIBUTE);
    }

    public PaymentCalculatorScreen enterValueIntoInterestRateInput(String interestValue) {
        interestRateInput.clear();
        interestRateInput.sendKeys(interestValue);
        return this;
    }

    public PaymentCalculatorScreen pressCalculateButton() {
        calculateButton.click();
        return this;
    }

    public String getValueFromPaymentResults() {
        (new WebDriverWait(driver, 10))
                .until((WebDriver driver) -> paymentResultsLabel.isDisplayed());
        Assert.assertTrue(paymentResultsLabel.isDisplayed());
        return paymentResultsLabel.getText();
    }

    public PaymentCalculatorScreen selectAmortizationValue() {
        amortizationSelect.click();
        if (amortizationSelect.isDisplayed()) {
            amortizationValueFifteen.click();
        }
        return this;
    }

    public String getValueFromAmortizationList() {
        return amortizationLabel.getText();
    }

    public PaymentCalculatorScreen selectPaymentFrequencyValue() {
        paymentFrequencySelect.click();
        if (paymentFrequencySelect.isDisplayed()) {
            paymentFrequencyWeeklyValue.click();
        }
        return this;
    }

    public String getValueFromPaymentFrequencyList() {
        return paymentFrequencyLabel.getText();
    }
}
