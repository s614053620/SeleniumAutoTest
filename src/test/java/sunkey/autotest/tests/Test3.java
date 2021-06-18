package sunkey.autotest.tests;

import sunkey.autotest.runner.RequestMapping;
import sunkey.autotest.runner.Runner;
import sunkey.autotest.runner.TestContext;
import sunkey.autotest.testcase.AutoQuit;
import sunkey.autotest.testcase.SsoLogin;
import sunkey.autotest.testcase.SsoPortal;

/**
 * @author Sunkey
 * @since 2021-05-27 6:35 下午
 **/
public class Test3 {

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        Runner.CHROME
                .config("config-sunkey.properties")
                .open("http://pre-sso.27aichi.cn")
                .with(new SsoLogin())
                .with(new SsoPortal("路上直销平台"))
                .with(new Test3())
                .with(new AutoQuit())
                .run();
    }

    @RequestMapping(ref = "/czrzlist")
    public void testLs(TestContext test) {
        System.out.println("进入路上直销平台页面");
    }

}
