package sunkey.autotest.runner;

import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * @author Sunkey
 * @since 2021-05-27 6:04 下午
 **/
public interface TestCase {

    void handle(RemoteWebDriver driver) throws Throwable;

}
