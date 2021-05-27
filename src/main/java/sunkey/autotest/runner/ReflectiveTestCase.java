package sunkey.autotest.runner;

import org.openqa.selenium.remote.RemoteWebDriver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Sunkey
 * @since 2021-05-27 6:16 下午
 **/
public class ReflectiveTestCase implements TestCase {

    private final Object target;
    private final Method method;

    ReflectiveTestCase(Object target, Method method) {
        this.target = target;
        this.method = method;
        validateMethod();
    }

    private void validateMethod() {
        if (method.getParameterCount() != 1) {
            throw new IllegalStateException("@AutoTest格式错误");
        }
        Class<?> type = method.getParameterTypes()[0];
        if (type != RemoteWebDriver.class) {
            throw new IllegalStateException("@AutoTest格式错误");
        }
    }

    @Override
    public void handle(RemoteWebDriver driver) throws Throwable {
        try {
            method.invoke(target, driver);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
    }

}
