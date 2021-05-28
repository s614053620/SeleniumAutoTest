package sunkey.autotest.runner.route;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sunkey.autotest.runner.TestContext;
import sunkey.autotest.utils.Ordered;

/**
 * @author Sunkey
 * @since 2021-05-28 11:42 上午
 **/
@Getter
@RequiredArgsConstructor
public class Route implements MatchRule, Handler, Ordered {

    private final MatchRule rule;
    private final Handler handler;
    private final int order;

    @Override
    public boolean match(TestContext context) {
        return this.rule.match(context);
    }

    @Override
    public void handle(TestContext context) throws Throwable {
        handler.handle(context);
    }
}
