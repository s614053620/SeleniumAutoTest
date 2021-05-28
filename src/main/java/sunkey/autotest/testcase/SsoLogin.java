package sunkey.autotest.testcase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import sunkey.autotest.runner.AutoTest;
import sunkey.autotest.runner.TestContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sunkey
 * @since 2021-05-27 6:48 下午
 **/
@AutoTest(path = "/")
public class SsoLogin {

    @AutoTest(host = "pre-sso.27aichi.*")
    public void loginPre(TestContext context) {
        Map<String, String> keys = new HashMap<>();
        keys.put("username", "15776319821");
        keys.put("password", "123321");
        keys.put("captcha", "lsls");
        login(context, keys);
    }

    @AutoTest(host = "test-sso.27aichi.*")
    public void loginTest(TestContext context) {
        Map<String, String> keys = new HashMap<>();
        keys.put("username", "15776319821");
        keys.put("password", "123321");
        keys.put("captcha", "lsls");
        login(context, keys);
    }

    @AutoTest(host = "dev-sso.27aichi.*")
    public void login(TestContext context) {
        Map<String, String> keys = new HashMap<>();
        keys.put("username", "15776319821");
        keys.put("password", "123321");
        keys.put("captcha", "lsls");
        login(context, keys);
    }

    private void login(TestContext context, Map<String, String> params) {
        WebElement form = context.driver().findElement(By.tagName("form"));
        List<WebElement> inputs = form.findElements(By.tagName("input"));
        for (WebElement input : inputs) {
            String key = input.getAttribute("name");
            String putVal = params.get(key);
            if (putVal != null) {
                input.sendKeys(putVal);
            }
        }
        WebElement submit = form.findElement(By.className("pwd-btn"));
        submit.click();
    }

}
