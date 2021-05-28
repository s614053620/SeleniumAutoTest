package sunkey.autotest.runner.route;

import sunkey.autotest.runner.TestContext;

/**
 * @author Sunkey
 * @since 2021-05-27 6:04 下午
 **/
public interface Handler {

    void handle(TestContext context) throws Throwable;

}
