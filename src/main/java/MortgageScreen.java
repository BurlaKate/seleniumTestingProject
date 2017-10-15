import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MortgageScreen {

    WebDriver driver;

    @FindBy(css = ".icone-calculateur a[data-utag-name='calculate_your_payments']")
    private WebElement calculatePaymentsButton;

    MortgageScreen(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step
    public PaymentCalculatorScreen calculatePaymentButtonPress() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);", calculatePaymentsButton);
        calculatePaymentsButton.click();
        return new PaymentCalculatorScreen(driver);
    }

    @Step
    public MortgageScreen open(String productHref) {
        new MainScreen(driver)
                .openPageAndChangeLanguage()
                .openLoansListIfNotOpened()
                .selectProduct(productHref);
        return this;
    }
}
