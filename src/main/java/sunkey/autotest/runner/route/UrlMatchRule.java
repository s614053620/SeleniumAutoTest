package sunkey.autotest.runner.route;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sunkey.autotest.utils.PatternMatchUtils;

import java.net.URL;

/**
 * @author Sunkey
 * @since 2021-05-28 11:12 上午
 **/

@Getter
@Setter
@ToString
public class UrlMatchRule extends AbstractURLMatchRule {

    private String protocol;
    private String host;
    private int port;
    private String path;
    private String query;
    private String ref;

    @Override
    protected boolean matchUrl(URL url) {
        return matchProtocol(url.getProtocol())
                && matchHost(url.getHost())
                && matchPort(url.getPort())
                && matchPath(url.getPath())
                && matchQuery(url.getQuery())
                && matchRef(url.getPath());
    }

    protected boolean matchProtocol(String protocol) {
        return matchString(this.protocol, protocol);
    }

    protected boolean matchHost(String host) {
        return matchString(this.host, host);
    }

    protected boolean matchPort(int port) {
        if (this.port < 1) {
            return true;
        }
        if (port < 1) {
            port = 80;
        }
        return this.port == port;
    }

    protected boolean matchPath(String path) {
        return matchString(this.path, path);
    }

    protected boolean matchQuery(String query) {
        return matchString(this.query, query);
    }

    protected boolean matchRef(String ref) {
        return matchString(this.ref, ref);
    }

    protected boolean matchString(String self, String other) {
        return self == null
                || PatternMatchUtils.simpleMatch(self, other);
    }

}
