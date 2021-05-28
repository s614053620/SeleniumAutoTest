package sunkey.autotest.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import sunkey.autotest.runner.Runner;
import sunkey.autotest.runner.RunnerContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sunkey
 * @since 2021-05-27 3:39 下午
 **/
public class Test {

    public static void main(String[] args) throws InterruptedException {
        RunnerContext runner = Runner.CHROME
                .config("config-sunkey.properties")
                .open("http://pre-sso.27aichi.cn");

        WebElement form = runner.driver.findElement(By.tagName("form"));
        List<WebElement> inputs = form.findElements(By.tagName("input"));

        Map<String, String> keys = new HashMap<>();
        keys.put("username", "15776319821");
        keys.put("password", "123321");
        keys.put("captcha", "lsls");

        for (WebElement input : inputs) {
            String key = input.getAttribute("name");
            String putVal = keys.get(key);
            if (putVal != null) {
                input.sendKeys(putVal);
            }
        }

        WebElement submit = form.findElement(By.className("pwd-btn"));

        String url = runner.driver.getCurrentUrl();
        submit.click();

        while (true) {
            String currentUrl = runner.driver.getCurrentUrl();
            System.out.println("current url:" + currentUrl);
            if (!url.equals(currentUrl)) {
                // 页面发生跳转
                break;
            }
            Thread.sleep(100);
        }

        System.out.println("登陆成功");
        System.out.println(runner.driver.getTitle());

    }



}
