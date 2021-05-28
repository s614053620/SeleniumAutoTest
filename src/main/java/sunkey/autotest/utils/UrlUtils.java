package sunkey.autotest.utils;

import java.net.URL;

/**
 * @author Sunkey
 * @since 2021-05-28 3:38 下午
 **/
public class UrlUtils {

    public static String getRef(URL url) {
        String ref = url.getRef();
        if (ref != null) {
            int idx = ref.indexOf('?');
            if (idx != -1) {
                return ref.substring(0, idx);
            }
        }
        return ref;
    }

}
