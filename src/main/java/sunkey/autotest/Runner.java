package sunkey.autotest;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import sunkey.autotest.runner.Config;

/**
 * @author Sunkey
 * @since 2021-05-27 3:52 下午
 **/
public class Runner<T extends RemoteWebDriver> {

    public static final Runner<ChromeDriver> CHROME = new Runner<>(ChromeDriver.class);
    public static final Runner<FirefoxDriver> FIREFOX = new Runner<>(FirefoxDriver.class);

    private final Class<T> driverClass;

    public static void loadConfig(String location) {
        Config.load(location);
    }

    private Runner(Class<T> driverClass) {
        if (driverClass == null) {
            throw new NullPointerException();
        }
        this.driverClass = driverClass;
    }

    public T get(String url) {
        T instance = createInstance();
        instance.get(url);
        return instance;
    }

    protected T createInstance() {
        try {
            return driverClass.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

}
