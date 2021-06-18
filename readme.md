# 基于Selenium的自动化测试工具
### 1. 优势
> * API简洁
> * 快速编写测试用例
> * 快速构建测试流程
### 2. 用法
```java

public class TestFunction1 {

    public static void main(String[] args) {
        Runner.CHROME
                .config("config.properties")
                .open("http://sso.hostname.com")
                .with(new SsoLogin())
                .with(new SsoPortal("**直销平台"))
                .with(new TestFunction1())
                .with(new AutoQuit())
                .runTests();
    }

    @RequestMapping(ref = "/czrzlist")
    public void testLs(TestContext test) {
        System.out.println("进入**直销平台页面");
    }

}


@RequestMapping(path = "/login")
public class SsoLogin {

    @RequestMapping(host = "sso.hostname.com")
    public void loginPre(TestContext context) {
        Map<String, String> keys = new HashMap<>();
        keys.put("username", "UserName");
        keys.put("password", "123456");
        keys.put("captcha", "lsls");
        login(context, keys);
    }

    @RequestMapping(host = "test-sso.hostname.com")
    public void loginPre(TestContext context) {
        Map<String, String> keys = new HashMap<>();
        keys.put("username", "PreUserName");
        keys.put("password", "123456");
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

public class SsoPortal {

    private final String iconName;

    public SsoPortal(String iconName) {
        Assert.notNull(iconName, "iconName");
        this.iconName = iconName;
    }

    @RequestMapping(path = "/portal")
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

public class AutoQuit {

    @Order(Order.MIN)
    @RequestMapping
    public void test(TestContext test) {
        System.out.println("测试通过");
        test.quit();
    }

}


```
