import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
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

    private static WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void quit() {
        driver.quit();
    }

    @Test
    public void shouldChangeLanguage() {
        new MainScreen(driver)
                .openPageAndChangeLanguage();
        Assert.assertEquals(new MainScreen(driver).getLanguageToSwitchTo(), FR_LANGUAGE);
    }

    @Test
    public void shouldChooseRightItemInLoansList() {
        new MainScreen(driver)
                .openPageAndChangeLanguage()
                .selectProduct(LOANS_LIST_ITEM);
        Assert.assertTrue(driver.getCurrentUrl().contains(MORTGAGE_SCREEN), "Not mortgage screen!");
    }

    @Test
    public void shouldPressButtonOnMortgageScreen() {
        new MortgageScreen(driver)
                .open(LOANS_LIST_ITEM).calculatePaymentButtonPress();
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
        new PaymentCalculatorScreen(driver)
                .open(LOANS_LIST_ITEM)
                .movePurchaseSliderToTheRight()
                .movePurchaseSliverUsingPlusButton();
        Assert.assertEquals(new PaymentCalculatorScreen(driver).getValueOfPurchasePrice(), EXPECTED_PURCHASE_PRICE,
                "Purchase Price Value is wrong!");
    }

    @Test
    public void shouldChangeDownPriceUsingButton() {
        new PaymentCalculatorScreen(driver)
                .open(LOANS_LIST_ITEM)
                .scrollTo("500")
                .moveDownSliverUsingPlusButton();
        Assert.assertEquals(new PaymentCalculatorScreen(driver).getValueOfDownPrice(), EXPECTED_DOWN_PRICE,
                "Down Price Value is wrong!");
    }

    @Test
    public void shouldEnterValueIntoInterestRateInput() {
        new PaymentCalculatorScreen(driver)
                .open(LOANS_LIST_ITEM)
                .scrollTo("750")
                .enterValueIntoInterestRateInput(INTEREST_VALUE);
        Assert.assertEquals(new PaymentCalculatorScreen(driver).getValueFromInterestRateInput(), EXPECTED_INTEREST_VALUE,
                "Interest Value is wrong!");
    }

    @Test
    public void shouldSelectAmortizationByValue() {
        new PaymentCalculatorScreen(driver)
                .open(LOANS_LIST_ITEM)
                .scrollTo("750")
                .selectAmortizationValue();
        Assert.assertEquals(new PaymentCalculatorScreen(driver).getValueFromAmortizationList(),
                EXPECTED_AMORTIZATION_VALUE, "Amortization Value is wrong!");
    }

    @Test
    public void shouldSelectPaymentFrequencyByValue() {
        new PaymentCalculatorScreen(driver)
                .open(LOANS_LIST_ITEM)
                .scrollTo("900")
                .selectPaymentFrequencyValue();
        Assert.assertEquals(new PaymentCalculatorScreen(driver).getValueFromPaymentFrequencyList(),
                EXPECTED_PAYMENT_FREQUENCY_VALUE,
                "Payment Frequency Value is wrong!");
    }

    @Test
    public void shouldMakeAllSteps() {
        new PaymentCalculatorScreen(driver)
                .open(LOANS_LIST_ITEM)
                .scrollTo("300")
                .movePurchaseSliderToTheRight()
                .movePurchaseSliverUsingPlusButton()
                .moveDownSliverUsingPlusButton()
                .scrollTo("200")
                .enterValueIntoInterestRateInput(INTEREST_VALUE)
                .scrollTo("400")
                .selectAmortizationValue()
                .selectPaymentFrequencyValue()
                .pressCalculateButton()
                .scrollTo("-600")
                .getValueFromPaymentResults();
        Assert.assertEquals(new PaymentCalculatorScreen(driver).getValueFromPaymentResults(), EXPECTED_PAYMENT_RESULT,
                "Expected payment is wrong!");
    }
}
