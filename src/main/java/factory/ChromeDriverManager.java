package factory;

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
        if (null == chromeDriver) {
            try {
                chromeDriver = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File("D:/work/SeleniumTestProjectIA/seleniumTestProjectIA/webdrivers/", "chromedriver.exe"))
                        .usingAnyFreePort()
                        .build();
                chromeDriver.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void stopService() {
        if (chromeDriver.isRunning())
            chromeDriver.stop();
    }

    @Override
    protected void createDriver() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(capabilities);

    }
}
