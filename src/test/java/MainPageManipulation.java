import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MainPageManipulation {


    public static final String FR_LANGUAGE = "fr-CA";

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
        MainPage mainPage = new MainPage(driver);
        mainPage.openPageAndChangeLanguage();
        Assert.assertEquals(mainPage.getLanguageToSwitchTo(), FR_LANGUAGE);

    }

    @Test
    public void shouldChooseRightItemInLoansList() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPageAndChangeLanguage();
        mainPage.selectProduct("/mortgage");
        Assert.assertTrue(driver.getCurrentUrl().contains("/mortgage"), "Wrong screen!");

    }

    @Test
    public void shouldPressButtonOnMortgageScreen() throws InterruptedException {
        MortgagePage mortgagePage = new MortgagePage(driver);
        mortgagePage.open("/mortgage");
        mortgagePage.calculatePaymentButtonPress();
    }

}
