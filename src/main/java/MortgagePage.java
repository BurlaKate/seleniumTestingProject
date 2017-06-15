import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MortgagePage {

    WebDriver driver;
    @FindBy(css = "div#main div div div div a[href='/mortgage-payment-calculator']")
    private WebElement calculatePaymentsButton;

    public MortgagePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public MortgagePage calculatePaymentButtonPress() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);", calculatePaymentsButton);
        this.calculatePaymentsButton.click();
        return this;
    }

    public MortgagePage open(String productHref) {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPageAndChangeLanguage();
        mainPage.openLoansListIfNotOpened();
        mainPage.selectProduct(productHref);
        return this;
    }
}
