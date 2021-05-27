package sunkey.autotest.runner;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Sunkey
 * @since 2021-05-27 6:00 下午
 **/
public class Router {

    private final Map<String, TestCase> routes = new ConcurrentHashMap<>();
    private TestCase fallback;

    public Router route(String uri, TestCase handler) {
        if (AutoTest.MATCH_ALL.equals(uri)) {
            fallback = handler;
        } else {
            routes.put(uri, handler);
        }
        return this;
    }

    public TestCase route(String uri) {
        TestCase handler = routes.get(uri);
        if (handler == null) {
            if (fallback != null) {
                return fallback;
            } else {
                throw new IllegalStateException("TestCase for '" + uri + "' not exists!");
            }
        }
        return handler;
    }

}
