import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MortgagePaymentManipulation {

    private static final String FR_LANGUAGE = "fr-CA";
    private static final String LOANS_LIST_ITEM = "mortgage_loan";
    private static final String MORTGAGE_SCREEN = "/mortgage";
    private static final String PAYMENT_CALCULATION_SCREEN = "/mortgage-payment-calculator";
    private static final int EXPECTED_PURCHASE_PRICE = 500000;
    private static final int EXPECTED_DOWN_PRICE = 100000;
    private static final String INTEREST_VALUE = "5";
    private static final String EXPECTED_INTEREST_VALUE = "5";
    private static final String EXPECTED_AMORTIZATION_VALUE = "15 years";
    private static final String EXPECTED_PAYMENT_FREQUENCY_VALUE = "weekly";
    private static final String EXPECTED_PAYMENT_RESULT = "$ 726.35";


    WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterTest
    public void close() {
        driver.close();
    }

    @Test
    public void shouldChangeLanguage() {
        MainScreen mainScreen = new MainScreen(driver);
        mainScreen.openPageAndChangeLanguage();
        Assert.assertEquals(mainScreen.getLanguageToSwitchTo(), FR_LANGUAGE);
    }

    @Test
    public void shouldChooseRightItemInLoansList() {
        MainScreen mainScreen = new MainScreen(driver);
        mainScreen.openPageAndChangeLanguage();
        mainScreen.selectProduct(LOANS_LIST_ITEM);
        Assert.assertTrue(driver.getCurrentUrl().contains(MORTGAGE_SCREEN), "Not mortgage screen!");
    }


    @Test
    public void shouldPressButtonOnMortgageScreen() {
        MortgageScreen mortgageScreen = new MortgageScreen(driver);
        mortgageScreen.open(LOANS_LIST_ITEM);
        mortgageScreen.calculatePaymentButtonPress();
        Assert.assertTrue(driver.getCurrentUrl().contains(PAYMENT_CALCULATION_SCREEN),
                "Not payment calculator screen!");
    }

    @Test
    public void shouldMovePurchaseSliderToTheRight() {
        PaymentCalculatorScreen paymentScreen = new PaymentCalculatorScreen(driver);
        paymentScreen.open(LOANS_LIST_ITEM);
        Integer oldLocation = paymentScreen.getLocationOfPurchaseSlider();
        paymentScreen.scrollTo("250");
        paymentScreen.movePurchaseSliderToTheRight();
        Integer newLocation = paymentScreen.getLocationOfPurchaseSlider();
        Assert.assertTrue(paymentScreen.checkIfPurchaseSliderWorks(oldLocation, newLocation),
                "Purchase Price Slider movement doesn't work!");
    }

    @Test
    public void shouldChangePurchasePriceUsingButton() {
        PaymentCalculatorScreen paymentScreen = new PaymentCalculatorScreen(driver);
        paymentScreen.open(LOANS_LIST_ITEM);
        paymentScreen.movePurchaseSliderToTheRight();
        paymentScreen.movePurchaseSliverUsingPlusButton();
        Assert.assertEquals(paymentScreen.getValueOfPurchasePrice(), EXPECTED_PURCHASE_PRICE,
                "Purchase Price Value is wrong!");
    }

    @Test
    public void shouldChangeDownPriceUsingButton() {
        PaymentCalculatorScreen paymentScreen = new PaymentCalculatorScreen(driver);
        paymentScreen.open(LOANS_LIST_ITEM);
        paymentScreen.scrollTo("500");
        paymentScreen.moveDownSliverUsingPlusButton();
        Assert.assertEquals(paymentScreen.getValueOfDownPrice(), EXPECTED_DOWN_PRICE,
                "Down Price Value is wrong!");
    }

    @Test
    public void shouldEnterValueIntoInterestRateInput() {
        PaymentCalculatorScreen paymentScreen = new PaymentCalculatorScreen(driver);
        paymentScreen.open(LOANS_LIST_ITEM);
        paymentScreen.scrollTo("750");
        paymentScreen.enterValueIntoInterestRateInput(INTEREST_VALUE);
        Assert.assertEquals(paymentScreen.getValueFromInterestRateInput(), EXPECTED_INTEREST_VALUE,
                "Interest Value is wrong!");

    }

    @Test
    public void shouldSelectAmortizationByValue() {
        PaymentCalculatorScreen paymentScreen = new PaymentCalculatorScreen(driver);
        paymentScreen.open(LOANS_LIST_ITEM);
        paymentScreen.scrollTo("750");
        paymentScreen.selectAmortizationValue();
        Assert.assertEquals(paymentScreen.getValueFromAmortizationList(), EXPECTED_AMORTIZATION_VALUE,
                "Amortization Value is wrong!");
    }

    @Test
    public void shouldSelectPaymentFrequencyByValue() {
        PaymentCalculatorScreen paymentScreen = new PaymentCalculatorScreen(driver);
        paymentScreen.open(LOANS_LIST_ITEM);
        paymentScreen.scrollTo("900");
        paymentScreen.selectPaymentFrequencyValue();
        Assert.assertEquals(paymentScreen.getValueFromPaymentFrequencyList(), EXPECTED_PAYMENT_FREQUENCY_VALUE,
                "Payment Frequency Value is wrong!");
    }

    @Test
    public void shouldMakeAllSteps() {
        PaymentCalculatorScreen paymentScreen = new PaymentCalculatorScreen(driver);
        paymentScreen.open(LOANS_LIST_ITEM);
        paymentScreen.scrollTo("300");
        paymentScreen.movePurchaseSliderToTheRight();
        paymentScreen.movePurchaseSliverUsingPlusButton();
        paymentScreen.moveDownSliverUsingPlusButton();
        paymentScreen.scrollTo("200");
        paymentScreen.enterValueIntoInterestRateInput(INTEREST_VALUE);
        paymentScreen.scrollTo("400");
        paymentScreen.selectAmortizationValue();
        paymentScreen.selectPaymentFrequencyValue();
        paymentScreen.pressCalculateButton();
        paymentScreen.scrollTo("-600");
        paymentScreen.getValueFromPaymentResults();
        Assert.assertEquals(paymentScreen.getValueFromPaymentResults(), EXPECTED_PAYMENT_RESULT,
                "Expected payment is wrong!");
    }
}
