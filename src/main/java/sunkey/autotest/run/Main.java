package sunkey.autotest.run;

import sunkey.autotest.runner.Runner;
import sunkey.autotest.testcase.SsoLogin;
import sunkey.autotest.testcase.SsoPortal;

/**
 * @author Sunkey
 * @since 2021-05-28 11:22 上午
 **/
public class Main {

    public static void main(String[] args) {
        Runner.CHROME
                .config("config-sunkey.properties")
                .open("http://pre-sso.27aichi.cn")
                .testcase(new SsoLogin())
                .testcase(new SsoPortal("路上直销平台"))
                .run();
    }

}
