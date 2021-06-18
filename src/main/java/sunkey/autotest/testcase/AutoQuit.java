package sunkey.autotest.testcase;

import sunkey.autotest.runner.RequestMapping;
import sunkey.autotest.runner.TestContext;
import sunkey.autotest.utils.Order;

/**
 * @author Sunkey
 * @since 2021-06-18 4:22 下午
 **/
public class AutoQuit {

    @Order(Order.MIN)
    @RequestMapping
    public void test(TestContext test) {
        System.out.println("测试通过");
        test.quit();
    }

}
