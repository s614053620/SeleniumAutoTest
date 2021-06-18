package sunkey.autotest.utils;

/**
 * @author Sunkey
 * @since 2021-05-27 6:16 下午
 **/
public class Assert {

    public static void notNull(Object value, String message) {
        if (value == null) {
            throw new NullPointerException(message);
        }
    }

}
