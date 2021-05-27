package sunkey.autotest.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import sunkey.autotest.Runner;

/**
 * @author Sunkey
 * @since 2021-05-27 4:32 下午
 **/
public class Test2 {

    public static void main(String[] args) {
        Runner.loadConfig("config.properties");
        FirefoxDriver driver = Runner.FIREFOX.get("http://pre-sso.27aichi.cn");
        WebElement element = driver.findElement(By.tagName("form"));
        System.out.println(element);
    }

}
