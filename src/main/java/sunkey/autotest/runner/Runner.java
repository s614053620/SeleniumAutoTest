package sunkey.autotest.runner;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * @author Sunkey
 * @since 2021-05-27 3:52 下午
 **/
public class Runner<T extends RemoteWebDriver> {

    public static final String DEFAULT_CONFIG = "config.properties";

    public static final Runner<ChromeDriver> CHROME = new Runner<>(ChromeDriver.class);
    public static final Runner<FirefoxDriver> FIREFOX = new Runner<>(FirefoxDriver.class);

    private final Class<T> driverClass;

    private Runner(Class<T> driverClass) {
        if (driverClass == null) {
            throw new NullPointerException();
        }
        this.driverClass = driverClass;
    }

    public Runner config(String location) {
        Config.loadOnce(location);
        return this;
    }

    public TestRouter open(String url) {
        if (!Config.loaded()) {
            Config.loadOnce(DEFAULT_CONFIG);
        }
        RemoteWebDriver instance = createInstance();
        instance.get(url);
        return new TestRouter(instance);
    }

    protected T createInstance() {
        try {
            return driverClass.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

}
