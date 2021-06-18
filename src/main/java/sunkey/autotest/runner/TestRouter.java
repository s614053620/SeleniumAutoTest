package sunkey.autotest.runner;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.openqa.selenium.remote.RemoteWebDriver;
import sunkey.autotest.runner.handler.UrlChangedHandler;
import sunkey.autotest.runner.route.Router;

/**
 * @author Sunkey
 * @since 2021-05-27 6:00 下午
 **/
@Getter
@Setter
@ToString
public class TestRouter {

    public final RemoteWebDriver driver;
    private final Router router = new Router();

    TestRouter(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public TestRouter with(Object testcase) {
        router.routeObject(testcase);
        return this;
    }

    public void run() {
        EventLoop loop = new EventLoop(this);
        loop.getHandler().addLast(new UrlChangedHandler());
        loop.startLoop();
    }

}
