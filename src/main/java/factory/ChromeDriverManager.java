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
public class ChromeDriverManager extends DriverManager {

    private ChromeDriverService chromeDriver;

    @Override
    protected void startService() {
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
    protected WebDriver createDriver() {
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
