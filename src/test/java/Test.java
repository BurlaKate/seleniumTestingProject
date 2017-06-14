import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Test {

    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();
        driver.get("http://192.168.17.59:8080/UMS/");

        driver.quit();
    }
}
