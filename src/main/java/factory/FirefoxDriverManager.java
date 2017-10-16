package factory;

import org.openqa.selenium.WebDriver;

/**
 * Created by Katherine on 15.10.2017.
 */
public class FirefoxDriverManager extends DriverManager {

    @Override
    protected WebDriver createDriver() {
        return null;
    }

    @Override
    protected void startService() {
    }

    @Override
    protected void stopService() {

    }
}
