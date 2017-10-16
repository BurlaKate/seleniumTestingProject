package factory;

/**
 * Created by Katherine on 15.10.2017.
 */
public class DriverManagerFactory {

    public static DriverManager getManager(DriverType type) {

        switch (type) {
            case CHROME:
                return new ChromeDriverManager();
            case FIREFOX:
                return new FirefoxDriverManager();
            default:
                return new ChromeDriverManager();
        }
    }

    public enum DriverType {
        CHROME,
        FIREFOX,
    }

}
