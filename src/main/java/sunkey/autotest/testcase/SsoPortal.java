package sunkey.autotest.testcase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import sunkey.autotest.runner.AutoTest;
import sunkey.autotest.runner.TestContext;
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

    @AutoTest(path = "/portal", query = "from=sso")
    public void portal(TestContext context) {
        List<WebElement> allIcons = context.driver().findElement(By.className("chooseList"))
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
