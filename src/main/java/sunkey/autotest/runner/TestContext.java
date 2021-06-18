package sunkey.autotest.runner;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.remote.RemoteWebDriver;
import sunkey.autotest.runner.route.Router;

import java.util.Objects;

/**
 * @author Sunkey
 * @since 2021-05-28 2:57 下午
 **/
@RequiredArgsConstructor
public class TestContext {

    public static final int DEFAULT_MAX_EMPTY_LOOP = 100;

    public final TestRouter runnerContext;
    @Getter
    private volatile boolean running = true;
    @Getter
    private volatile boolean quit = false;
    private volatile String prevUrl;
    private volatile int emptyLoopCount = 0;

    public void stop() {
        running = false;
    }

    public void quit() {
        quit = true;
        stop();
    }

    protected boolean checkUrlChanged() {
        String currentUrl = url();
        if (Objects.equals(prevUrl, currentUrl)) {
            emptyLoopCount++;
            if (emptyLoopCount > DEFAULT_MAX_EMPTY_LOOP) {
                stop();
            }
            return false;
        } else {
            prevUrl = currentUrl;
            emptyLoopCount = 0;
            return true;
        }
    }

    public RemoteWebDriver driver() {
        return runnerContext.getDriver();
    }

    public Router router() {
        return runnerContext.getRouter();
    }

    public String url() {
        return driver().getCurrentUrl();
    }

}
