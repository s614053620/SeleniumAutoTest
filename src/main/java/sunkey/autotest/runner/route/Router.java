package sunkey.autotest.runner.route;

import sunkey.autotest.runner.AutoTest;
import sunkey.autotest.runner.TestContext;
import sunkey.autotest.utils.Assert;
import sunkey.autotest.utils.OrderComparator;
import sunkey.autotest.utils.Ordered;

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
        // 每次添加完重新排序
        routes.sort(OrderComparator.INSTANCE);
        return this;
    }

    public Router addRoute(MatchRule rule, Handler handler, int order) {
        return addRoute(new Route(rule, handler, order));
    }

    public Router addRoute(MatchRule rule, Handler handler) {
        return addRoute(rule, handler, Ordered.DEFAULT_ORDER);
    }

    public Router addAutoTest(Object testcase) {
        Assert.notNull(testcase, "testcase");
        Class<?> type = testcase.getClass();
        AutoTest parentAnn = type.getAnnotation(AutoTest.class);
        for (Method method : type.getDeclaredMethods()) {
            AutoTest ann = method.getAnnotation(AutoTest.class);
            if (ann != null) {
                addRoute(new AutoTestMatchRule(parentAnn, ann),
                        new MethodHandler(testcase, method),
                        getOrder(type, method));
            }
        }
        return this;
    }

    private int getOrder(Class type, Method method) {
        if (OrderComparator.supportOrder(method)) {
            return OrderComparator.getOrder(method);
        }
        return OrderComparator.getOrder(type);
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
