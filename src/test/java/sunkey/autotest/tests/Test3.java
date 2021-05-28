package sunkey.autotest.tests;

import sunkey.autotest.runner.AutoTest;
import sunkey.autotest.runner.Runner;
import sunkey.autotest.runner.TestContext;
import sunkey.autotest.testcase.SsoLogin;
import sunkey.autotest.testcase.SsoPortal;
import sunkey.autotest.utils.Order;
import sunkey.autotest.utils.OrderComparator;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * @author Sunkey
 * @since 2021-05-27 6:35 下午
 **/
public class Test3 {

    public static void main(String[] args) {
        Method[] methods = Test3.class.getDeclaredMethods();
        List<Method> methods1 = Arrays.asList(methods);
        methods1.sort(OrderComparator.INSTANCE);
        for (Method method : methods1) {
            System.out.println(method);
            System.out.println(OrderComparator.getOrder(method));
        }
    }

    public static void test() {
        Runner.CHROME
                .config("config-sunkey.properties")
                .open("http://pre-sso.27aichi.cn")
                .testcase(new SsoLogin())
                .testcase(new SsoPortal("路上直销平台"))
                .testcase(new Test3())
                .run();
    }

    @AutoTest(ref = "/czrzlist")
    public void testLs(TestContext test) {
        System.out.println("进入路上直销平台页面");
        System.out.println("测试通过");
        test.driver().navigate().to("");
        test.quit();
    }

    @Order(Order.MIN)
    @AutoTest
    public void test(TestContext test) {
        System.out.println("测试通过");
        test.quit();
    }

}
