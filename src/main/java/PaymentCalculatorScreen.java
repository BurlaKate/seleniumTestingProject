import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PaymentCalculatorScreen {

    private static final int PURCHASE_SLIDER_X_OFFSET = 100;
    private static final int PURCHASE_SLIDER_Y_OFFSET = 0;
    private static final String DATA_VALUE_ATTRIBUTE = "data-value";
    private static final String VALUE_ATTRIBUTE = "value";

    private static final String AMORTIZATION_VALUE = "15";

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
        Point point = this.purchasePriceSlider.getLocation();
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
        return Integer.parseInt(this.sliderPrixValue.getAttribute(DATA_VALUE_ATTRIBUTE));
    }

    public int getValueOfDownPrice() {
        return Integer.parseInt(this.sliderMiseValue.getAttribute(DATA_VALUE_ATTRIBUTE));
    }

    public PaymentCalculatorScreen movePurchaseSliverUsingPlusButton() {
        for (int i = getValueOfPurchasePrice(); i < 500000; i += 250000)
            this.purchasePricePlusButton.click();
        return this;
    }

    public PaymentCalculatorScreen moveDownSliverUsingPlusButton() {
        for (int i = getValueOfDownPrice(); i < 100000; i += 100000)
            this.downPricePlusButton.click();
        return this;
    }

    public String getValueFromInterestRateInput() {
        return this.interestRateInput.getAttribute(VALUE_ATTRIBUTE);
    }

    public PaymentCalculatorScreen enterValueIntoInterestRateInput(String interestValue) {
        this.interestRateInput.clear();
        this.interestRateInput.sendKeys(interestValue);
        return this;
    }

    public PaymentCalculatorScreen pressCalculateButton() {
        this.calculateButton.click();
        return this;
    }

//    public Number getValueFromPaymentResults(){
//        return Double.parseDouble(this.paymentResultsLabel.getText().substring(0, 1));
//    }

    public String getValueFromPaymentResults(){
        return this.paymentResultsLabel.getText();
    }



    public PaymentCalculatorScreen selectAmortizationByValue(){
        amortizationSelect.click();
        Select amortSelect = new Select(amortizationSelect);
        amortSelect.selectByValue(AMORTIZATION_VALUE);
        return this;
    }


}
