package sunkey.autotest.runner.route;

import sunkey.autotest.runner.AutoTest;
import sunkey.autotest.runner.TestContext;
import sunkey.autotest.utils.Assert;

import java.lang.reflect.Method;
import java.util.LinkedList;

/**
 * @author Sunkey
 * @since 2021-05-27 6:00 下午
 **/
public class Router {

    private final LinkedList<Route> routes = new LinkedList<>();

    public Router addRoute(Route route) {
        routes.addLast(route);
        return this;
    }

    public Router addRoute(MatchRule rule, Handler handler) {
        return addRoute(new Route(rule, handler));
    }

    public Router addAutoTest(Object testcase) {
        Assert.notNull(testcase, "testcase");
        Class<?> type = testcase.getClass();
        AutoTest parentAnn = type.getAnnotation(AutoTest.class);
        for (Method method : type.getDeclaredMethods()) {
            AutoTest ann = method.getAnnotation(AutoTest.class);
            if (ann != null) {
                addRoute(new AutoTestMatchRule(parentAnn, ann), new MethodHandler(testcase, method));
            }
        }
        return this;
    }

    public void doRoute(TestContext context) throws RouteException {
        for (Route route : routes) {
            if (route.match(context)) {
                try {
                    route.handle(context);
                } catch (Throwable ex) {
                    throw new RouteException(ex.getMessage(), ex);
                }
                return;
            }
        }
        throw new RouteException("route not found: " + context.url());
    }

}
