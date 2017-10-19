package factory;

import org.openqa.selenium.WebDriver;

/**
 * Created by Katherine on 15.10.2017.
 */
public interface DriverManager {

    void startService();

    WebDriver getDriver();

    WebDriver createDriver();

}
