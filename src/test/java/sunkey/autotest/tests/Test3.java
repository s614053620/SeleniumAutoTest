package sunkey.autotest.tests;

import sunkey.autotest.runner.AutoTest;
import sunkey.autotest.runner.Runner;
import sunkey.autotest.runner.TestContext;
import sunkey.autotest.testcase.SsoLogin;
import sunkey.autotest.testcase.SsoPortal;

/**
 * @author Sunkey
 * @since 2021-05-27 6:35 下午
 **/
public class Test3 {

    public static void main(String[] args) {
        Runner.CHROME
                .config("config-sunkey.properties")
                .open("http://pre-sso.27aichi.cn")
                .testcase(new SsoLogin())
                .testcase(new SsoPortal("路上直销平台"))
                .testcase(new Test3())
                .run();
    }

    @AutoTest
    public void test(TestContext test) {
        System.out.println("测试通过");
        //test.stop();
        test.quit();
    }

}
