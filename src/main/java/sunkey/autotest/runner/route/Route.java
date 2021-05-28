package sunkey.autotest.runner.route;

import sunkey.autotest.runner.TestContext;

/**
 * @author Sunkey
 * @since 2021-05-28 11:42 上午
 **/
public class Route implements MatchRule, Handler {

    private final MatchRule rule;
    private final Handler handler;

    public Route(MatchRule rule, Handler handler) {
        this.rule = rule;
        this.handler = handler;
    }

    @Override
    public boolean match(TestContext context) {
        return this.rule.match(context);
    }

    @Override
    public void handle(TestContext context) throws Throwable {
        handler.handle(context);
    }
}
