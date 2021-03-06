package sunkey.autotest.runner;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author Sunkey
 * @since 2021-05-27 4:03 下午
 **/
public class Config {

    private static volatile boolean loaded = false;

    public static boolean loaded() {
        return loaded;
    }

    public static synchronized void loadOnce(String location) {
        if (loaded) {
            return;
        }
        load(location);
        loaded = true;
    }

    private static void load(String location) {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(location);
        if (in == null) {
            throw new IllegalStateException("config file not exists!");
        }
        Properties props = new Properties();
        try {
            props.load(in);
            props.forEach((k, v) -> {
                System.setProperty(k.toString(), v.toString());
            });
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

}
