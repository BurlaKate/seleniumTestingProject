import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MainPageManipulation {

    public static final String LOANS_LABEL = "\n" +
            "                                                Loans\n" +
            "                                                ";

    WebDriver driver ;

    @BeforeTest
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterTest
    public void quit(){
        driver.quit();
    }

    @Test
    public void test() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        if(!mainPage.loansDropDownList.getText().equals(LOANS_LABEL))
        mainPage.languageButtonPress();
//        Thread.sleep(5000);

    }
}
