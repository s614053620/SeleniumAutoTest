package sunkey.autotest.runner.route;

import sunkey.autotest.runner.TestContext;

/**
 * @author Sunkey
 * @since 2021-05-28 10:37 上午
 **/
public interface MatchRule {

    boolean match(TestContext context);

}
