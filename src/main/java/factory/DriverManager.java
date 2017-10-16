package factory;

import org.openqa.selenium.WebDriver;

/**
 * Created by Katherine on 15.10.2017.
 */
public abstract class DriverManager {

    protected WebDriver driver;

    protected abstract void startService();
    protected abstract void stopService();
    protected abstract void createDriver();

    public void quit() {
        stopService();
        driver.quit();
    }

    public WebDriver getDriver() {
        if (null == driver) {
            startService();
            createDriver();
        }
        return driver;
    }


}
