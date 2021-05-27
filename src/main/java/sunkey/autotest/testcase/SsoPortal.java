package sunkey.autotest.testcase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import sunkey.autotest.runner.AutoTest;
import sunkey.autotest.utils.Assert;

import java.util.List;

/**
 * @author Sunkey
 * @since 2021-05-27 6:57 下午
 **/
public class SsoPortal {

    private final String iconName;

    public SsoPortal(String iconName) {
        Assert.notNull(iconName, "iconName");
        this.iconName = iconName;
    }

    @AutoTest("http://pre-sso.27aichi.cn/portal?from=sso")
    public void portalPre(RemoteWebDriver driver) {
        portal(driver);
    }

    @AutoTest("http://test-sso.27aichi.cn/portal?from=sso")
    public void portalTest(RemoteWebDriver driver) {
        portal(driver);
    }

    @AutoTest("http://dev-sso.27aichi.cn/portal?from=sso")
    public void portalDev(RemoteWebDriver driver) {
        portal(driver);
    }

    private void portal(RemoteWebDriver driver) {
        List<WebElement> allIcons = driver.findElement(By.className("chooseList"))
                .findElements(By.tagName("a"));
        for (WebElement icon : allIcons) {
            WebElement h5 = icon.findElement(By.tagName("h5"));
            if (iconName.equals(h5.getText())) {
                icon.click();
                break;
            }
        }
    }

}
