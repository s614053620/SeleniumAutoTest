package sunkey.autotest.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import sunkey.autotest.Runner;

/**
 * @author Sunkey
 * @since 2021-05-27 3:39 下午
 **/
public class Test {

    public static void main(String[] args) {
        Runner.loadConfig("config-sunkey.properties");
        ChromeDriver driver = Runner.CHROME.get("http://pre-sso.27aichi.cn");
        WebElement element = driver.findElement(By.tagName("form"));
        System.out.println(element);
    }

}
