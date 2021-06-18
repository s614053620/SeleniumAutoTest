package sunkey.autotest.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import sunkey.autotest.runner.Runner;

import java.util.List;

/**
 * @author 赵明磊
 * @since 2021-05-27 4:32 下午
 **/
public class Test2 {

    public static void main(String[] args) throws InterruptedException {
        // 加载配置
        // 请求网页
        RemoteWebDriver driver = Runner.FIREFOX.open("http://pre-sso.27aichi.cn").driver;
        // 获得form节点
        WebElement form = driver.findElement(By.tagName("form"));
        // 查询所有form节点内的input
        List<WebElement> inputs = form.findElements(By.tagName("input"));
        // 遍历所有input组件
        for (WebElement input : inputs) {
            // 获得input的name
            String name = input.getAttribute("name");
            switch (name) {
                // 如果input的name=username
                case "username":
                    // 写入用户名
                    input.sendKeys("15776319821");
                    break;
                // 如果input的name=password
                case "password":
                    // 写入密码
                    input.sendKeys("123321");
                    break;
                // 如果input的name=验证码
                case "captcha":
                    // 写入通用验证码lsls
                    input.sendKeys("lsls");
                    break;
            }
        }

        // 查询form组件内的class="pwd-btn"的登录按钮
        WebElement submit = form.findElement(By.className("pwd-btn"));
        // 获得当前页面的url地址
        String url = driver.getCurrentUrl();
        // 点击登录按钮
        submit.click();

        // 或者调用form的submit方法直接提交表单，效果相同。
        // form.submit();

        // 无限循环判断页面是否跳转，如果页面已跳转则退出无限循环
        while (true) {
            // 获得页面当前url
            String currentUrl = driver.getCurrentUrl();
            System.out.println("current url:" + currentUrl);
            // 如果和之前url相比有变化
            if (!url.equals(currentUrl)) {
                // 页面发生跳转
                // 退出无限循环
                break;
            }
            // 否则500毫秒之后继续判断url是否发生变化
            Thread.sleep(100);
        }

        // 页面已跳转
        System.out.println("登陆成功");
        // 打印新页面标题
        System.out.println(driver.getTitle());

    }

}
