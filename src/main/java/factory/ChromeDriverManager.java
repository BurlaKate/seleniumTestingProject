package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

/**
 * Created by Katherine on 15.10.2017.
 */
public class ChromeDriverManager implements DriverManager {

    private ChromeDriverService chromeDriver;
    private WebDriver driver;

    @Override
    public void startService() {
        if (chromeDriver == null) {
            try {
                chromeDriver = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File("src/test/resources/webdrivers/", "chromedriver.exe"))
                        .usingAnyFreePort()
                        .build();
                chromeDriver.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public WebDriver getDriver() {
        if (driver == null) {
            startService();
            this.driver = createDriver();
        }
        return driver;
    }

    @Override
    public WebDriver createDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("start-maximized");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("platformName", "Windows");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        return new ChromeDriver(capabilities);
    }

}
