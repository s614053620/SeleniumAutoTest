package sunkey.autotest.runner.handler;

import sunkey.autotest.runner.TestContext;
import sunkey.autotest.runner.event.Event;

import java.util.LinkedList;

/**
 * @author Sunkey
 * @since 2021-05-28 2:26 下午
 **/
public interface EventHandler<T extends Event> {

    void handle(T event, TestContext context);

    public static class Chain
            extends LinkedList<EventHandler>
            implements EventHandler {

        @Override
        public void handle(Event event, TestContext context) {
            for (EventHandler handler : this) {
                handler.handle(event, context);
            }
        }

    }

}
