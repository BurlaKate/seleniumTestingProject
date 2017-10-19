package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

/**
 * Created by Katherine on 15.10.2017.
 */
public class FirefoxDriverManager implements DriverManager {

    private GeckoDriverService firefoxDriver;
    private WebDriver driver;

    @Override
    public void startService() {
        if (firefoxDriver == null) {
            try {
                firefoxDriver = new GeckoDriverService.Builder()
                        .usingDriverExecutable(new File("src/test/resources/webdrivers/", "geckodriver.exe"))
                        .usingAnyFreePort()
                        .build();
                firefoxDriver.start();
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
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("test-type");
        options.addArguments("start-maximized");
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("browserName", "firefox");
        capabilities.setCapability("platformName", "Windows");
        capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
        return new FirefoxDriver();
    }
}

