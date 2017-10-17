package factory;

import org.openqa.selenium.WebDriver;

/**
 * Created by Katherine on 15.10.2017.
 */
public abstract class DriverManager {

    protected WebDriver driver;

    protected abstract void startService();
    protected abstract WebDriver createDriver();

    public void quit() {
        driver.quit();
    }

    public WebDriver getDriver() {
        if (this.driver == null) {
            startService();
            this.driver = createDriver();
        }
        return driver;
    }


}
