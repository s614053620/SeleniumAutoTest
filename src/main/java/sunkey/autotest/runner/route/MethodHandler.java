package sunkey.autotest.runner.route;

import sunkey.autotest.runner.TestContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Sunkey
 * @since 2021-05-27 6:16 下午
 **/
public class MethodHandler implements Handler {

    private final Object target;
    private final Method method;

    MethodHandler(Object target, Method method) {
        this.target = target;
        this.method = method;
        validateMethod();
    }

    private void validateMethod() {
        if (method.getParameterCount() != 1) {
            throw new IllegalStateException("@AutoTest格式错误:" + method.toString());
        }
        Class<?> type = method.getParameterTypes()[0];
        if (type != TestContext.class) {
            throw new IllegalStateException("@AutoTest格式错误:" + method.toString());
        }
    }

    @Override
    public void handle(TestContext context) throws Throwable {
        try {
            method.invoke(target, context);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
    }

}
