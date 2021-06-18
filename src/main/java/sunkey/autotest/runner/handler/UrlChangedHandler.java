package sunkey.autotest.runner.handler;

import sunkey.autotest.runner.TestContext;
import sunkey.autotest.runner.event.UrlChangedEvent;

/**
 * @author Sunkey
 * @since 2021-05-28 2:51 下午
 **/
public class UrlChangedHandler implements EventHandler<UrlChangedEvent> {

    @Override
    public void handle(UrlChangedEvent event, TestContext context) {
        context.router().doRoute(context);
    }

}
