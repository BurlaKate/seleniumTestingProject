import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MainScreenManipulation {


    public static final String FR_LANGUAGE = "fr-CA";
    public static final String LOANS_LIST_ITEM = "/mortgage";

    WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterTest
    public void quit() {
        driver.quit();
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
        Assert.assertTrue(driver.getCurrentUrl().contains(LOANS_LIST_ITEM), "Not mortgage screen!");
    }


    @Test
    public void shouldPressButtonOnMortgageScreen() {
        MortgageScreen mortgageScreen = new MortgageScreen(driver);
        mortgageScreen.open(LOANS_LIST_ITEM);
        mortgageScreen.calculatePaymentButtonPress();
        Assert.assertTrue(driver.getCurrentUrl().contains("/mortgage-payment-calculator"),
                "Not payment calculator screen!");
    }

    @Test
    public void shouldMovePurchaseSliderToTheRight() throws InterruptedException {
        PaymentCalculatorScreen paymentScreen = new PaymentCalculatorScreen(driver);
        paymentScreen.open(LOANS_LIST_ITEM);
        Thread.sleep(2000);
        paymentScreen.movePurchaseSliderToTheRight();
        Thread.sleep(2000);
    }


}
