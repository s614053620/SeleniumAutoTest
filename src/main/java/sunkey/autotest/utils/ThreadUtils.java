package sunkey.autotest.utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Sunkey
 * @since 2021-05-27 6:33 下午
 **/
public class ThreadUtils {

    public static void sleepRandom(long max) {
        long millis = ThreadLocalRandom.current().nextLong(max);
        sleep(millis);
    }

    public static void sleep(long millis) {
        if (millis < 1) {
            return;
        }
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
        }
    }

}
