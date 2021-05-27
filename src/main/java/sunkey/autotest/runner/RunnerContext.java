package sunkey.autotest.runner;

import org.openqa.selenium.remote.RemoteWebDriver;
import sunkey.autotest.utils.Assert;
import sunkey.autotest.utils.ThreadUtils;

import java.lang.reflect.Method;

/**
 * @author Sunkey
 * @since 2021-05-27 6:00 下午
 **/
public class RunnerContext {

    public static final long MAX_SLEEP = 5000;
    public static int MAX_EMPTY_LOOP = 10;

    public final RemoteWebDriver driver;
    private final Router router = new Router();

    protected RunnerContext(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public RunnerContext testcase(Object testcase) {
        Assert.notNull(testcase, "testcase");
        for (Method method : testcase.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(AutoTest.class)) {
                AutoTest ann = method.getAnnotation(AutoTest.class);
                router.route(ann.value(), new ReflectiveTestCase(testcase, method));
            }
        }
        return this;
    }

    public void run() {
        String prevUrl = null;
        String currentUrl;
        int emptyLoop = 0;
        try {
            while (true) {
                currentUrl = driver.getCurrentUrl();
                if (currentUrl.equals(prevUrl)) {
                    emptyLoop++;
                    if (emptyLoop > MAX_EMPTY_LOOP) {
                        break;
                    }
                } else {
                    prevUrl = currentUrl;
                    TestCase handler = router.route(currentUrl);
                    handler.handle(driver);
                    ThreadUtils.sleepRandom(MAX_SLEEP);
                }
            }
        } catch (Throwable ex) {
            driver.close();
            throw new RuntimeException(ex.getMessage(), ex);
        }
        driver.close();
    }

}
