package sunkey.autotest.runner;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sunkey.autotest.runner.event.UrlChangedEvent;
import sunkey.autotest.runner.handler.EventHandler;
import sunkey.autotest.utils.ThreadUtils;

/**
 * @author Sunkey
 * @since 2021-05-28 2:27 下午
 **/

@Getter
@Setter
@ToString
public class EventLoop {

    public static final long DEFAULT_LOOP_INTERVAL = 500;

    private final OpenedTests runnerContext;

    private final EventHandler.Chain handler = new EventHandler.Chain();

    public EventLoop(OpenedTests runnerContext) {
        this.runnerContext = runnerContext;
    }

    public void startLoop() {
        TestContext ctx = new TestContext(runnerContext);
        try {
            while (ctx.isRunning()) {
                fireEvents(ctx);
                ThreadUtils.sleep(DEFAULT_LOOP_INTERVAL);
            }
            checkQuit(ctx);
        } catch (Throwable ex) {
            checkQuit(ctx);
            throw ex;
        }
    }

    private void checkQuit(TestContext ctx) {
        if (ctx.isQuit()) {
            runnerContext.driver.quit();
        }
    }

    private void fireEvents(TestContext ctx) {
        fireUrlChangedEvent(ctx);
    }

    private void fireUrlChangedEvent(TestContext ctx) {
        if (ctx.checkUrlChanged()) {
            handler.handle(new UrlChangedEvent(ctx.url()), ctx);
        }
    }

}
