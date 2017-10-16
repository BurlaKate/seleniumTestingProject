package factory;

/**
 * Created by Katherine on 15.10.2017.
 */
public class DriverManagerFactory {

    public enum DriverType {
        CHROME,
        FIREFOX,
    }

    public static DriverManager getManager(DriverType type){

        DriverManager driverManager;

        switch (type) {
            case CHROME:
                driverManager = new ChromeDriverManager();
                break;
            case FIREFOX:
                driverManager = new FirefoxDriverManager();
                break;
            default:
                driverManager = new ChromeDriverManager();
                break;
        }

        return  driverManager;
    }
}
