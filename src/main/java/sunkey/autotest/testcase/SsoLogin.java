package sunkey.autotest.testcase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import sunkey.autotest.runner.AutoTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sunkey
 * @since 2021-05-27 6:48 下午
 **/
public class SsoLogin {

    @AutoTest("http://pre-sso.27aichi.cn/")
    public void loginPre(RemoteWebDriver driver) {
        Map<String, String> keys = new HashMap<>();
        keys.put("username", "15776319821");
        keys.put("password", "123321");
        keys.put("captcha", "lsls");
        login(driver, keys);
    }

    @AutoTest("http://test-sso.27aichi.cn/")
    public void loginTest(RemoteWebDriver driver) {
        Map<String, String> keys = new HashMap<>();
        keys.put("username", "15776319821");
        keys.put("password", "123321");
        keys.put("captcha", "lsls");
        login(driver, keys);
    }

    @AutoTest("http://dev-sso.27aichi.cn/")
    public void login(RemoteWebDriver driver) {
        Map<String, String> keys = new HashMap<>();
        keys.put("username", "15776319821");
        keys.put("password", "123321");
        keys.put("captcha", "lsls");
        login(driver, keys);
    }

    private void login(RemoteWebDriver driver, Map<String, String> params) {
        WebElement form = driver.findElement(By.tagName("form"));
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
