package sunkey.autotest.runner.route;

import lombok.extern.slf4j.Slf4j;
import sunkey.autotest.runner.TestContext;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Sunkey
 * @since 2021-05-28 11:00 上午
 **/
@Slf4j
public abstract class AbstractURLMatchRule implements MatchRule {

    protected abstract boolean matchUrl(URL url);

    @Override
    public boolean match(TestContext context) {
        try {
            return matchUrl(new URL(context.url()));
        } catch (MalformedURLException e) {
            log.error("cannot parse url {}", context.url());
            return false;
        }
    }

}
