package sunkey.autotest.tests;

import sunkey.autotest.runner.AutoTest;
import sunkey.autotest.runner.Runner;
import sunkey.autotest.runner.TestContext;
import sunkey.autotest.testcase.SsoLogin;
import sunkey.autotest.testcase.SsoPortal;

import java.net.MalformedURLException;
import java.net.URL;

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

    @AutoTest(ref = "/czrzlist")
    public void testLs(TestContext test) {
        System.out.println("进入路上直销平台页面");
        System.out.println("测试通过");
        test.quit();
    }

    @AutoTest
    public void test(TestContext test) throws MalformedURLException {
        System.out.println("测试通过");
        URL url = new URL(test.url());
        System.out.println(url.getRef());
        test.quit();
    }

}
